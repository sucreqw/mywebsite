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
@TableName("comments")
public class CommentsDO implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("postip")
    private String postip;

    @TableField("postday")
    private String postday;

    @TableField("nickname")
    private String nickname;

    @TableField("token")
    private String token;

    @TableField("avater")
    private String avater;

    @TableField("content")
    private String content;

    @TableField("wid")
    private Integer wid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostip() {
        return postip;
    }

    public void setPostip(String postip) {
        this.postip = postip;
    }

    public String getPostday() {
        return postday;
    }

    public void setPostday(String postday) {
        this.postday = postday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    @Override
    public String toString() {
        return "CommentsDO{" +
        "id=" + id +
        ", postip=" + postip +
        ", postday=" + postday +
        ", nickname=" + nickname +
        ", token=" + token +
        ", avater=" + avater +
        ", content=" + content +
        ", wid=" + wid +
        "}";
    }
}
