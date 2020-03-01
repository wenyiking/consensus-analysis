package com.shzz.consensus.analysis.entity;/*
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * FileName：DecisionControlController
 * Description：http://www.hikvision.com
 *
 * History：
 * @author wangwen7
 * @date 2019/7/18
 * @update 新建文件
 */


import com.shzz.consensus.analysis.anno.FieldAtExcel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author wangwen7
 * @version v1.0
 * @date 2019/7/18 16:10
 */
public class VolumeForStatic {

    @NotNull(message = "starttime不能为空")
    @FieldAtExcel(excleFieldName = "startTime")
    private String startTime;

    @FieldAtExcel(excleFieldName = "crossId")
    private Integer crossingId;

    @FieldAtExcel(excleFieldName = "crossCode")
    @NotNull(message = "crossing_code不能为空")
    private String crossingCode;

    @NotEmpty
    @FieldAtExcel(excleFieldName = "crossName")
    private String crossingName;

    @FieldAtExcel(excleFieldName = "laneId")
    private Integer laneNo;

    @FieldAtExcel(excleFieldName = "direction")
    private Integer direction;

    @FieldAtExcel(excleFieldName = "turn")
    private Integer turn;

    @FieldAtExcel(excleFieldName = "laneName")
    private String laneName;

    @NotNull(message = "flow不能为空")
    @NotEmpty
    @Min(value = 0, message = "最小值为1") // 最小值为1
    @Max(value = 1000, message = "最大值为1800") // 最大值1000,单车道最大车流量
    @FieldAtExcel(excleFieldName = "flow")
    private Integer flow;

    @NotNull
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getCrossingId() {
        return crossingId;
    }

    public void setCrossingId(Integer crossingId) {
        this.crossingId = crossingId;
    }

    @NotNull
    public String getCrossingCode() {
        return crossingCode;
    }

    public void setCrossingCode(String crossingCode) {
        this.crossingCode = crossingCode;
    }

    public String getCrossingName() {
        return crossingName;
    }

    public void setCrossingName(String crossingName) {
        this.crossingName = crossingName;
    }

    public Integer getLaneNo() {
        return laneNo;
    }

    public void setLaneNo(Integer laneNo) {
        this.laneNo = laneNo;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }


    public String getLaneName() {
        return laneName;
    }

    public void setLaneName(String laneName) {
        this.laneName = laneName;
    }

    @NotNull
    @NotEmpty
    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return "VolumeForStatic{" +
                "startTime='" + startTime + '\'' +
                ", crossingId=" + crossingId +
                ", crossingCode='" + crossingCode + '\'' +
                ", crossingName='" + crossingName + '\'' +
                ", laneNo=" + laneNo +
                ", direction=" + direction +
                ", turn=" + turn +
                ", laneName='" + laneName + '\'' +
                ", flow=" + flow +
                '}';
    }
}
