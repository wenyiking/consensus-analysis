package com.shzz.consensus.analysis.controller;/*
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * FileName：DecisionControlController
 * Description：http://www.hikvision.com
 *
 * History：
 * @author wangwen7
 * @date 2019/8/30
 * @update 新建文件
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangwen7
 * @version v1.0
 * @date 2019/8/30 9:47
 */
@Controller
public class ThymeleafDataViewController {
    @RequestMapping(value = "show2", method = RequestMethod.GET)
    public String show(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("uid", "123456789");
        model.addAttribute("name", "wangwen7");
        model.addAttribute("url", "www.baidu.com");
        System.out.println(model.toString());
        System.out.println(httpServletRequest.getParameter("para1"));
        return "show2";
    }

}
