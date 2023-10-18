package com.zhirong.liaohui.model;

import com.zhirong.liaohui.entity.CityRelate;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class CityModel {


    @ApiModelProperty(value = "省下面的所有市")
    private List<CityRelate> list;
    @ApiModelProperty(value = "省下面的直辖市")
    private List<String> zhixiaList;

    public List<CityRelate> getList() {

        return list;
    }

    public void setList(List<CityRelate> list) {
        this.list = list;
    }

    public List<String> getZhixiaList() {
        List<String> list = new ArrayList<>();
        list.add("成都");
        list.add("北京");
        list.add("上海");
        list.add("天津");
        list.add("重庆");
        list.add("广州");
        return list;
    }

    public void setZhixiaList(List<String> zhixiaList) {
        this.zhixiaList = zhixiaList;
    }
}
