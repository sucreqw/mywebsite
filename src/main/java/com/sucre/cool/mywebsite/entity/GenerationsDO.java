package com.sucre.cool.mywebsite.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
@TableName("generations")
public class GenerationsDO implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("nickname")
    private String nickname;

    @TableField("wife")
    private String wife;

    @TableField("gender")
    private Integer gender;

    @TableField("father")
    private String father;

    @TableField("sons")
    private String sons;

    @TableField("details")
    private String details;

    @TableField("generation")
    private Integer generation;

    @TableField("birthday")
    private String birthday;

    @TableField("avater")
    private String avater;

    @TableField("cookie")
    private String cookie;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getSons() {
        return sons;
    }

    public void setSons(String sons) {
        this.sons = sons;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public String toString() {
        return "GenerationsDO{" +
        "id=" + id +
        ", nickname=" + nickname +
        ", wife=" + wife +
        ", gender=" + gender +
        ", father=" + father +
        ", sons=" + sons +
        ", details=" + details +
        ", generation=" + generation +
        ", birthday=" + birthday +
        ", avater=" + avater +
        ", cookie=" + cookie +
        "}";
    }
}
