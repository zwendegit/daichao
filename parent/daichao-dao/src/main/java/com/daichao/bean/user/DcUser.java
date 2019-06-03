package com.daichao.bean.user;

import java.util.Date;

public class DcUser {
    private Integer id;

    private String username;

    private String phone;

    private String password;

    private String accessToken;

    private String picImg;

    private Date registTime;

    private String registChannel;

    private Date firstLoginTime;

    private String firstLoginChannel;

    private Integer status;

    private boolean isSetPassword;
    
    private String registChannelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getPicImg() {
        return picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg == null ? null : picImg.trim();
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getRegistChannel() {
        return registChannel;
    }

    public void setRegistChannel(String registChannel) {
        this.registChannel = registChannel == null ? null : registChannel.trim();
    }

    public Date getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(Date firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public String getFirstLoginChannel() {
        return firstLoginChannel;
    }

    public void setFirstLoginChannel(String firstLoginChannel) {
        this.firstLoginChannel = firstLoginChannel == null ? null : firstLoginChannel.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isSetPassword() {
        return isSetPassword;
    }

    public void setSetPassword(boolean setPassword) {
        isSetPassword = setPassword;
    }

	public String getRegistChannelName() {
		return registChannelName;
	}

	public void setRegistChannelName(String registChannelName) {
		this.registChannelName = registChannelName;
	}
    
    
}