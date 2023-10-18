package com.zhirong.liaohui.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhirong.liaohui.entity.UserActivitySelection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ActivityRequest {

    private Integer id;

    @ApiModelProperty("活动内容")
    private String content;

    @ApiModelProperty("活动名称")
    private String name;
    /**
     * 活动类型(羽毛球等)
     */
    @ApiModelProperty("活动类型(羽毛球等)")
    private String type;

    private String url;

    @ApiModelProperty("活动地址")
    private String address;

    @ApiModelProperty("活动时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("活动限定人数")
    private Integer userNum;

    @ApiModelProperty("费用类型")
    private String moneyType;
    /**
     * 费用金额
     */
    @ApiModelProperty("费用金额")
    private String money;

    @ApiModelProperty("报名截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date reportStopTime;

    @ApiModelProperty("自定义选项")
    private UserActivitySelection activitySelection;

}
