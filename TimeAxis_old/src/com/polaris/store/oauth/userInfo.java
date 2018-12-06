package com.polaris.store.oauth;
import java.io.Serializable;

public class userInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String token;
	private String secret;
	private String tokenstatus;
	
	public String getTokenstatus() {
		return tokenstatus;
	}
	public void setTokenstatus(String tokenstatus) {
		this.tokenstatus = tokenstatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}
