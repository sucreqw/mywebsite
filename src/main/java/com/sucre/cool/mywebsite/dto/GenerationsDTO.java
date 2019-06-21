package com.sucre.cool.mywebsite.dto;

public class GenerationsDTO{
	//private Integer id;	/*	len: 10*/
	private String nickname;	/*	len: 32*/
	private String wife;	/*	len: 32*/
	private Integer gender;	/*	len: 3*/
	private String father;	/*	len: 32*/
	private String sons;	/*	len: 32*/
	private String details;	/*	len: 1024*/
	private Integer generation;	/*	len: 10*/
	private String birthday;	/*	len: 32*/
	private String avater;	/*	len: 32*/
	//private String cookie;	/*	len: 32*/

	/*public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}*/

	public void setNickname(String nickname){
		this.nickname=nickname;
	}
	public String getNickname(){
		return nickname;
	}

	public void setWife(String wife){
		this.wife=wife;
	}
	public String getWife(){
		return wife;
	}

	public void setGender(Integer gender){
		this.gender=gender;
	}
	public Integer getGender(){
		return gender;
	}

	public void setFather(String father){
		this.father=father;
	}
	public String getFather(){
		return father;
	}

	public void setSons(String sons){
		this.sons=sons;
	}
	public String getSons(){
		return sons;
	}

	public void setDetails(String details){
		this.details=details;
	}
	public String getDetails(){
		return details;
	}

	public void setGeneration(Integer generation){
		this.generation=generation;
	}
	public Integer getGeneration(){
		return generation;
	}

	public void setBirthday(String birthday){
		this.birthday=birthday;
	}
	public String getBirthday(){
		return birthday;
	}

	public void setAvater(String avater){
		this.avater=avater;
	}
	public String getAvater(){
		return avater;
	}

	/*public void setCookie(String cookie){
		this.cookie=cookie;
	}
	public String getCookie(){
		return cookie;
	}*/

}
