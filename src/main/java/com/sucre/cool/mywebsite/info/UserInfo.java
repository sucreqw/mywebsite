package com.sucre.cool.mywebsite.info;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInfo extends BaseUser{
	private Integer id;	/*	len: 10*/
	private String token;	/*	len: 32*/
	private String regday;	/*	len: 19*/
	private String regip;	/*	len: 32*/
	private String uid;	/*	len: 10*/
	private String nickname;	/*	len: 32*/
	@JsonIgnore
	private String psd;	/*	len: 32*/
	private String refreshToken;	/*	len: 32*/
	private Integer isblock;	/*	len: 3*/
	private String cookie;	/*	len: 32*/
	private String avater;	/*	len: 140*/
	private Integer ismanager;	/*	len: 3*/

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

	public UserInfo(Integer type) {
		super(type);
	}
	public UserInfo() {
		super(0);
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}

	public void setToken(String token){
		this.token=token;
	}
	public String getToken(){
		return token;
	}


	public void setUid(String uid){
		this.uid=uid;
	}
	public String getUid(){
		return uid;
	}

	public void setNickname(String nickname){
		this.nickname=nickname;
	}
	public String getNickname(){
		return nickname;
	}



	public void setRefreshToken(String refreshToken){
		this.refreshToken=refreshToken;
	}
	public String getRefreshToken(){
		return refreshToken;
	}

	public void setIsblock(Integer isblock){
		this.isblock=isblock;
	}
	public Integer getIsblock(){
		return isblock;
	}

	public void setCookie(String cookie){
		this.cookie=cookie;
	}
	public String getCookie(){
		return cookie;
	}

	public void setAvater(String avater){
		this.avater=avater;
	}
	public String getAvater(){
		return avater;
	}

	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public void setIsmanager(Integer ismanager){
		this.ismanager=ismanager;
	}
	public Integer getIsmanager(){
		return ismanager;
	}

}
