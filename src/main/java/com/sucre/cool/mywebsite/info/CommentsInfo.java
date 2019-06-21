package com.sucre.cool.mywebsite.info;

public class CommentsInfo{
	private Integer id;	/*	len: 10*/
	private String postip;	/*	len: 15*/
	private String postday;	/*	len: 19*/
	private String nickname;	/*	len: 32*/
	private String token;	/*	len: 32*/
	private String avater;	/*	len: 140*/
	private String content;	/*	len: 140*/
	private Integer wid;	/*	len: 10*/

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}

	public String getPostip() {
		return postip;
	}

	public void setPostip(String postip) {
		this.postip = postip;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setPostday(String postday){
		this.postday=postday;
	}
	public String getPostday(){
		return postday;
	}

	public void setNickname(String nickname){
		this.nickname=nickname;
	}
	public String getNickname(){
		return nickname;
	}


	public void setAvater(String avater){
		this.avater=avater;
	}
	public String getAvater(){
		return avater;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}

	public void setWid(Integer wid){
		this.wid=wid;
	}
	public Integer getWid(){
		return wid;
	}

}
