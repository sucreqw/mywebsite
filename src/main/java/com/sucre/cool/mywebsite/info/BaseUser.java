package com.sucre.cool.mywebsite.info;

public abstract class BaseUser {

    public BaseUser(Integer type){
        this.type = type;
    }

    private Integer id;

    private Integer type;

    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
