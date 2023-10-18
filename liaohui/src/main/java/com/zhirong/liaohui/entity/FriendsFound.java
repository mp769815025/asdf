package com.zhirong.liaohui.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (FriendsFound)实体类
 *
 * @author makejava
 * @since 2023-10-10 11:44:23
 */
public class FriendsFound  implements Serializable {
    private static final long serialVersionUID = -78684418293748467L;
    
    private Integer id;

    @ApiModelProperty(value = "userId")
    private Integer userId;
    /**
     * 搭子标题
     */
    @ApiModelProperty(value = "搭子标题")
    private String title;
    /**
     * 搭子类型
     */
    @ApiModelProperty(value = "搭子类型")
    private String type;
    /**
     * 选择地点
     */
    @ApiModelProperty(value = "选择地点")
    private String address;
    /**
     * 选择时间
     */
    @ApiModelProperty(value = "选择时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date times;
    /**
     * 性别要求
     */
    @ApiModelProperty(value = "性别要求")
    private String genderNeed;

    @ApiModelProperty(value = "图片地址")
    private String photoUrl;


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public String getGenderNeed() {
        return genderNeed;
    }

    public void setGenderNeed(String genderNeed) {
        this.genderNeed = genderNeed;
    }

}

