package com.zhirong.liaohui.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动(Activity)实体类
 *
 * @author makejava
 * @since 2023-10-13 11:07:16
 */
@Data
public class Activity implements Serializable {
    private static final long serialVersionUID = 703858782088198989L;
         
    @ApiModelProperty("${column.comment}")
    private Integer id;
    /**
     * 活动名称
     */

    @ApiModelProperty("活动内容")
    private String content;
    @ApiModelProperty("活动名称")
    private String name;
    /**
     * 活动类型(羽毛球等)
     */     
    @ApiModelProperty("活动类型(羽毛球等)")
    private String type;
    /**
     * 创建时间
     */     
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 开始时间
     */     
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 活动简介
     */     
    @ApiModelProperty("活动简介")
    private String introduct;
    /**
     * 活动地址备注
     */     
    @ApiModelProperty("活动地址备注")
    private String addressDetail;
    /**
     * 活动地址
     */     
    @ApiModelProperty("活动地址")
    private String address;
    /**
     * 活动限定人数
     */     
    @ApiModelProperty("活动限定人数")
    private Integer userNum;
    /**
     * 已参加人数
     */     
    @ApiModelProperty("已参加人数")
    private Integer alreadyJoinNum;
    /**
     * 活动详情
     */     
    @ApiModelProperty("活动详情")
    private String detailText;
    /**
     * 精彩回顾图片地址
     */     
    @ApiModelProperty("精彩回顾图片地址")
    private String photoUrl;
    /**
     * 评论
     */     
    @ApiModelProperty("评论")
    private String messages;
    /**
     * 活动状态(1.正在进行2.已结束)
     */     
    @ApiModelProperty("活动状态(1.正在进行2.已结束)")
    private String status;
    /**
     * 二维码链接
     */     
    @ApiModelProperty("二维码链接")
    private String qrCodeUrl;
    /**
     * 退款政策
     */     
    @ApiModelProperty("退款政策")
    private String backMoneyIdea;
    /**
     * 圈子名称(多个)
     */     
    @ApiModelProperty("圈子名称(多个)")
    private String groupNames;
    /**
     * 活动标签
     */     
    @ApiModelProperty("活动标签")
    private String activityLabel;
    /**
     * 是否首页展示
     */     
    @ApiModelProperty("是否首页展示")
    private Integer isShowIndex;
    /**
     * 用户填写信息
     */     
    @ApiModelProperty("用户填写信息")
    private String userInfoText;
    /**
     * 是否关联到俱乐部
     */     
    @ApiModelProperty("是否关联到俱乐部")
    private Integer isRealationGroup;
    /**
     * 满座后可候补等位
     */     
    @ApiModelProperty("满座后可候补等位")
    private Integer isWaiting;
    /**
     * 报名后是否通过
     */     
    @ApiModelProperty("报名后是否通过")
    private Integer isPass;
    /**
     * 是否可以挂人
     */     
    @ApiModelProperty("是否可以挂人")
    private Integer isRelationUser;
    /**
     * 费用类型
     */     
    @ApiModelProperty("费用类型")
    private String moneyType;
    /**
     * 费用金额
     */     
    @ApiModelProperty("费用金额")
    private String money;
    /**
     * 意见反馈
     */     
    @ApiModelProperty("意见反馈")
    private String feedback;
    /**
     * 报名截止时间
     */     
    @ApiModelProperty("报名截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date reportStopTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getIntroduct() {
        return introduct;
    }

    public void setIntroduct(String introduct) {
        this.introduct = introduct;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Integer getAlreadyJoinNum() {
        return alreadyJoinNum;
    }

    public void setAlreadyJoinNum(Integer alreadyJoinNum) {
        this.alreadyJoinNum = alreadyJoinNum;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getBackMoneyIdea() {
        return backMoneyIdea;
    }

    public void setBackMoneyIdea(String backMoneyIdea) {
        this.backMoneyIdea = backMoneyIdea;
    }

    public String getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(String groupNames) {
        this.groupNames = groupNames;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }

    public Integer getIsShowIndex() {
        return isShowIndex;
    }

    public void setIsShowIndex(Integer isShowIndex) {
        this.isShowIndex = isShowIndex;
    }

    public String getUserInfoText() {
        return userInfoText;
    }

    public void setUserInfoText(String userInfoText) {
        this.userInfoText = userInfoText;
    }

    public Integer getIsRealationGroup() {
        return isRealationGroup;
    }

    public void setIsRealationGroup(Integer isRealationGroup) {
        this.isRealationGroup = isRealationGroup;
    }

    public Integer getIsWaiting() {
        return isWaiting;
    }

    public void setIsWaiting(Integer isWaiting) {
        this.isWaiting = isWaiting;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public Integer getIsRelationUser() {
        return isRelationUser;
    }

    public void setIsRelationUser(Integer isRelationUser) {
        this.isRelationUser = isRelationUser;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }



}

