package com.bracelet.entity;

import java.sql.Timestamp;

public class BusinessUserInfo {
	private Integer id;
	private String username;
	private String avatar;
	private String nickname;
	private Integer use_status;
	private Integer balance;
	private Timestamp createtime;
	private Timestamp updatetime;
	private Integer shangyou_type;
	private String shangyou_content;
	private String scret;
	
	
	
	public String getScret() {
		return scret;
	}
	public void setScret(String scret) {
		this.scret = scret;
	}
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
		this.username = username;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getUse_status() {
		return use_status;
	}
	public void setUse_status(Integer use_status) {
		this.use_status = use_status;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
	public Integer getShangyou_type() {
		return shangyou_type;
	}
	public void setShangyou_type(Integer shangyou_type) {
		this.shangyou_type = shangyou_type;
	}
	public String getShangyou_content() {
		return shangyou_content;
	}
	public void setShangyou_content(String shangyou_content) {
		this.shangyou_content = shangyou_content;
	}
	
}
