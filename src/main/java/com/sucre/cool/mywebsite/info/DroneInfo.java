package com.sucre.cool.mywebsite.info;

public class DroneInfo{
	private Integer id;	/*	len: 10*/
	private Integer type;	/*	len: 10*/
	private String urls;	/*	len: 100*/
	private Integer likecount;	/*	len: 10*/
	private String content;	/*	len: 140*/

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}

	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}

	public void setUrls(String urls){
		this.urls=urls;
	}
	public String getUrls(){
		return urls;
	}

	public void setLikecount(Integer likecount){
		this.likecount=likecount;
	}
	public Integer getLikecount(){
		return likecount;
	}

	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}

}
