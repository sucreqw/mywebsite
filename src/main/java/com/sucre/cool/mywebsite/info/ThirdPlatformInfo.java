package com.sucre.cool.mywebsite.info;

public class ThirdPlatformInfo {
    private Integer id;
	private String name;	/*	len: 32*/
	private String appId;	/*	len: 32*/
	private String appSecret;	/*	len: 140*/
	private String redirect;	/*	len: 140*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
}
