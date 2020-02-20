package com.akfd.generalduty.tool.udf;

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname UDFClassLoader
 * @Description TODO
 * @Date 2020/2/19 23:59
 * @Created by wangwen7
 */

public class UDFClassLoader extends ClassLoader {
    private final Logger LOG = Logger.getLogger("UDFClassLoader");
    /**
     * lib:表示加载的文件在jar包中
     */
    private String lib;
    /**
     * classes:表示加载的文件是单纯的class文件
     */
    private String classes;
    /**
     * 采取将所有的jar包中的class读取到内存中
     * 然后如果需要读取的时候，再从map中查找
     */
    private Map<String, byte[]> map;

    private UDFClassLoader(String applicationRootPath) throws NoSuchMethodException, SecurityException, MalformedURLException {
        lib = applicationRootPath + File.separator + "lib";
        classes = applicationRootPath + File.separator + "classes";
        map = new HashMap<String, byte[]>(256);

        preReadJarFile();
    }

    private UDFClassLoader(String applicationRootPath, Level loggerLever) throws NoSuchMethodException, SecurityException, MalformedURLException {
        this(applicationRootPath);
        LOG.setLevel(loggerLever);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        /**
         * @Author wangwen
         * @Description //类名，全限定名
         * @Date 10:14 2020/2/20
         * @param name
         * @return
         **/
        try {
            byte[] result = getClassFromFileOrMap(name);
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从指定的classes文件夹下找到文件
     *
     * @param name
     * @return
     */
    private byte[] getClassFromFileOrMap(String name) {
        String classPath = classes + name.replace('.', File.separatorChar) + ".class";
        File file = new File(classPath);
        if (file.exists()) {
            InputStream input = null;
            try {
                input = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];
                int bytesNumRead = 0;
                while ((bytesNumRead = input.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesNumRead);
                }
                return baos.toByteArray();
            } catch (FileNotFoundException e) {
                LOG.severe(e.toString());
            } catch (IOException e) {
                LOG.severe(e.toString());
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        LOG.severe(e.toString());
                    }
                }
            }

        } else {
            if (map.containsKey(name)) {
                //去除map中的引用，避免GC无法回收无用的class文件
                return map.remove(name);
            }
        }
        return null;
    }

    /**
     * 预读lib下面的包
     */
    private void preReadJarFile() {
        List<File> list = scanDir();
        for (File f : list) {
            JarFile jar;
            try {
                jar = new JarFile(f);
                readJAR(jar);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 读取一个jar包内的class文件，并存在当前加载器的map中
     *
     * @param jar
     * @throws IOException
     */
    private void readJAR(JarFile jar) throws IOException {
        Enumeration<JarEntry> en = jar.entries();
        while (en.hasMoreElements()) {
            JarEntry je = en.nextElement();
            String name = je.getName();
            if (name.endsWith(".class")) {
                String clss = name.replace(".class", "").replaceAll("/", ".");
                if (this.findLoadedClass(clss) != null) {
                    continue;
                }

                InputStream input = jar.getInputStream(je);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];
                int bytesNumRead = 0;
                while ((bytesNumRead = input.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesNumRead);
                }
                byte[] cc = baos.toByteArray();
                input.close();
                map.put(clss, cc);//暂时保存下来
            }
        }
    }

    /**
     * 扫描lib下面的所有jar包
     *
     * @return
     */
    private List<File> scanDir() {
        List<File> list = new ArrayList<File>();
        File[] files = new File(lib).listFiles();
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".jar")) {
                list.add(f);
            }

        }
        return list;
    }

    /**
     * 添加一个jar包到加载器中去。
     *
     * @param jarPath
     * @throws IOException
     */
    public void addJar(String jarPath) throws IOException {
        File file = new File(jarPath);
        if (file.exists()) {
            JarFile jar = new JarFile(file);
            readJAR(jar);
        }
    }
}
