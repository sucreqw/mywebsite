package com.sucre.cool.mywebsite.dto;

public class ShareDTO {
	private String title;	/*	len: 140*/
	private String content;	/*	len: 140*/

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
