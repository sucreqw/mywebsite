package com.sucre.cool.mywebsite.info;

public class DronecommentInfo{
	private Integer id;	/*	len: 10*/
	private String mid;	/*	len: 11*/
	private String token;	/*	len: 32*/
	private String avater;	/*	len: 140*/
	private String nickname;	/*	len: 32*/
	private String content;	/*	len: 140*/
	private String postip;	/*	len: 15*/
	private String postday;	/*	len: 19*/

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMid(String mid){
		this.mid=mid;
	}
	public String getMid(){
		return mid;
	}


	public void setAvater(String avater){
		this.avater=avater;
	}
	public String getAvater(){
		return avater;
	}

	public void setNickname(String nickname){
		this.nickname=nickname;
	}
	public String getNickname(){
		return nickname;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}


}
