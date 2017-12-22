package com.breadcrumbs.breadcrumbs.dto;

import java.sql.Date;

public class BlogMember {
	private String email;
	private String pw;
	private String name;
	private String image;
	private String mobile;
	private String address1;
	private String address2;
	private Date joinDate;
	private Date loginDate;
	private int gubun;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public int getGubun() {
		return gubun;
	}

	public void setGubun(int gubun) {
		this.gubun = gubun;
	}

	@Override
	public String toString() {
		return "BlogMember [email=" + email + ", pw=" + pw + ", name=" + name
				+ ", image=" + image + ", mobile=" + mobile + ", address1="
				+ address1 + ", address2=" + address2 + ", joinDate="
				+ joinDate + ", loginDate=" + loginDate + ", gubun=" + gubun
				+ "]";
	}
}
