package com.zhirong.liaohui.qo;

import io.swagger.annotations.ApiModelProperty;

public class WechatLoginRequest {

    //登录时获取的 code，可通过wx.login获取
    @ApiModelProperty(value = "微信登录code")
    private String code;
    @ApiModelProperty(value = "微信电话code")
    private String phoneCode;


    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
