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
@TableName("user")
public class UserDO implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("token")
    private String token;

    @TableField("regday")
    private String regday;

    @TableField("regip")
    private String regip;

    @TableField("uid")
    private String uid;

    @TableField("nickname")
    private String nickname;

    @TableField("psd")
    private String psd;

    @TableField("refresh_token")
    private String refreshToken;

    @TableField("isblock")
    private Integer isblock;

    @TableField("cookie")
    private String cookie;

    @TableField("avater")
    private String avater;

    @TableField("ismanager")
    private Integer ismanager;


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

    public String getRegday() {
        return regday;
    }

    public void setRegday(String regday) {
        this.regday = regday;
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getIsblock() {
        return isblock;
    }

    public void setIsblock(Integer isblock) {
        this.isblock = isblock;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public Integer getIsmanager() {
        return ismanager;
    }

    public void setIsmanager(Integer ismanager) {
        this.ismanager = ismanager;
    }

    @Override
    public String toString() {
        return "UserDO{" +
        "id=" + id +
        ", token=" + token +
        ", regday=" + regday +
        ", regip=" + regip +
        ", uid=" + uid +
        ", nickname=" + nickname +
        ", psd=" + psd +
        ", refreshToken=" + refreshToken +
        ", isblock=" + isblock +
        ", cookie=" + cookie +
        ", avater=" + avater +
        ", ismanager=" + ismanager +
        "}";
    }
}
