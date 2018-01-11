package com.breadcrumbs.breadcrumbs.dto;

public class GDto {

	/*
	 * mybatis를 사용할려면 변수명과 필드명이 같아야 한다.
	 */
	private int g_no;
	private String g_name;
	private String g_title;
	private String g_pwd;
	private String g_cont;
	private String g_image;
	private int g_hit;
	private String g_date;
	
	public int getG_no() {
		return g_no;
	}
	public void setG_no(int g_no) {
		this.g_no = g_no;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_title() {
		return g_title;
	}
	public void setG_title(String g_title) {
		this.g_title = g_title;
	}
	public String getG_pwd() {
		return g_pwd;
	}
	public void setG_pwd(String g_pwd) {
		this.g_pwd = g_pwd;
	}
	public String getG_cont() {
		return g_cont;
	}
	public void setG_cont(String g_cont) {
		this.g_cont = g_cont;
	}
	public String getG_image() {
		return g_image;
	}
	public void setG_image(String g_image) {
		this.g_image = g_image;
	}
	public int getG_hit() {
		return g_hit;
	}
	public void setG_hit(int g_hit) {
		this.g_hit = g_hit;
	}
	public String getG_date() {
		return g_date;
	}
	public void setG_date(String g_date) {
		this.g_date = g_date.substring(0,10);
		//0이상 10미만 사이의 시분초는 떼버리고 년월일 만 반환
	}
}
