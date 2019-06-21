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
@TableName("dronecomment")
public class DronecommentDO implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mid")
    private String mid;

    @TableField("token")
    private String token;

    @TableField("avater")
    private String avater;

    @TableField("nickname")
    private String nickname;

    @TableField("content")
    private String content;

    @TableField("postip")
    private String postip;

    @TableField("postday")
    private String postday;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "DronecommentDO{" +
        "id=" + id +
        ", mid=" + mid +
        ", token=" + token +
        ", avater=" + avater +
        ", nickname=" + nickname +
        ", content=" + content +
        ", postip=" + postip +
        ", postday=" + postday +
        "}";
    }
}
