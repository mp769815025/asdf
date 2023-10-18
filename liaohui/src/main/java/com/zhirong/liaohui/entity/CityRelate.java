package com.zhirong.liaohui.entity;

import java.io.Serializable;

/**
 * (CityRelate)实体类
 *
 * @author makejava
 * @since 2023-10-12 12:00:31
 */
public class CityRelate implements Serializable {
    private static final long serialVersionUID = 197177700585130782L;
    
    private Integer id;
    
    private Integer pid;
    
    private String cityname;
    
    private Integer type;
    
    private Integer num;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}

