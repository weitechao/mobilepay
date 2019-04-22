package com.bracelet.entity;

import java.sql.Timestamp;

public class CxInfo {

	private Long id;
	private String username;
	private String order_id;
	private String charge_acct;
	private Integer charge_cash;
	private Integer error_code;
	private Timestamp createtime;
	private Integer user_id;
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getCharge_acct() {
		return charge_acct;
	}
	public void setCharge_acct(String charge_acct) {
		this.charge_acct = charge_acct;
	}
	public Integer getCharge_cash() {
		return charge_cash;
	}
	public void setCharge_cash(Integer charge_cash) {
		this.charge_cash = charge_cash;
	}
	public Integer getError_code() {
		return error_code;
	}
	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}


}
