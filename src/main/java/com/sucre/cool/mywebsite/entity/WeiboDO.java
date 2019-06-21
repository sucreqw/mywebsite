package com.sucre.cool.mywebsite.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("weibo")
public class WeiboDO implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("token")
    private String token;

    @TableField("postday")
    private String postday;

    @TableField("postip")
    private String postip;

    @TableField("mid")
    private String mid;

    @TableField("nickname")
    private String nickname;

    @TableField("avater")
    private String avater;

    @TableField("content")
    private String content;

    @TableField("picurl")
    private String picurl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPostday() {
        return postday;
    }

    public void setPostday(String postday) {
        this.postday = postday;
    }

    public String getPostip() {
        return postip;
    }

    public void setPostip(String postip) {
        this.postip = postip;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @Override
    public String toString() {
        return "WeiboDO{" +
        "id=" + id +
        ", token=" + token +
        ", postday=" + postday +
        ", postip=" + postip +
        ", mid=" + mid +
        ", nickname=" + nickname +
        ", avater=" + avater +
        ", content=" + content +
        ", picurl=" + picurl +
        "}";
    }
}
