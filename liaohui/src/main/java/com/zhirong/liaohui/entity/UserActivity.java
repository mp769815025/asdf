package com.zhirong.liaohui.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (UserActivity)实体类
 *
 * @author makejava
 * @since 2023-10-13 11:07:51
 */
public class UserActivity implements Serializable {
    private static final long serialVersionUID = 595738982426716755L;
         
    @ApiModelProperty("${column.comment}")
    private Integer id;
    /**
     * 用户openId(唯一)
     */     
    @ApiModelProperty("用户openId(唯一)")
    private String openId;
    /**
     * 活动id
     */     
    @ApiModelProperty("活动id")
    private Integer activityId;
    /**
     * 类型1参加2发布
     */     
    @ApiModelProperty("类型1参加2发布")
    private Integer type;
    /**
     * 是否报名成功
     */     
    @ApiModelProperty("是否报名成功")
    private Integer auditStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

}

