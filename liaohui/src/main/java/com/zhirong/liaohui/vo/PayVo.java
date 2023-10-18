package com.zhirong.liaohui.vo;

import lombok.Data;

@Data
public class PayVo {

    private Long timestamp;

    private String nonceStr;

    private String packages;

    private String signType;

    private String paySign;
}
