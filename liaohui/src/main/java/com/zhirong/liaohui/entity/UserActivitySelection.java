package com.zhirong.liaohui.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户活动报名自定义选项(UserActivitySelection)实体类
 *
 * @author makejava
 * @since 2023-10-13 11:10:53
 */
public class UserActivitySelection implements Serializable {
    private static final long serialVersionUID = -14111656215755451L;
    /**
     * 活动id
     */     
    @ApiModelProperty("活动id")
    private Integer activityId;
         
    @ApiModelProperty("${column.comment}")
    private Integer id;
    /**
     * 选项类型(下拉选,文本)
     */     
    @ApiModelProperty("选项类型(下拉选,文本)")
    private String type;
    /**
     * 选项名称
     */     
    @ApiModelProperty("选项名称")
    private String filedName;
    /**
     * 选项值
     */     
    @ApiModelProperty("选项值")
    private String filedValue;


    private Integer isAdmin;


    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFiledValue() {
        return filedValue;
    }

    public void setFiledValue(String filedValue) {
        this.filedValue = filedValue;
    }

}

