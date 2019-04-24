package com.bracelet.entity;

import java.sql.Date;

public class CompanyInfo {
	private Integer id;
	private String user_name;
	private String secret_key;
	private String company_name;
	private String contain_type;
	private String returl;
	private Date add_time;
	private String remark;
	private String status;
	private Date update_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSecret_key() {
		return secret_key;
	}
	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getContain_type() {
		return contain_type;
	}
	public void setContain_type(String contain_type) {
		this.contain_type = contain_type;
	}
	public String getReturl() {
		return returl;
	}
	public void setReturl(String returl) {
		this.returl = returl;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}
