package com.breadcrumbs.breadcrumbs.dto;

public class MemberTreeRelationDto {

	/*
	 * mybatis를 사용할려면 변수명과 필드명이 같아야 한다.
	 */
	private int tree_no;
	private int recommend_cnt;
	private String category;
	private String email;
	private String datafilename;
	
	public String getDatafilename() {
		return datafilename;
	}
	public void setDatafilename(String datafilename) {
		this.datafilename = datafilename;
	}
	public int getTree_no() {
		return tree_no;
	}
	public void setTree_no(int tree_no) {
		this.tree_no = tree_no;
	}
	public int getRecommend_cnt() {
		return recommend_cnt;
	}
	public void setRecommend_cnt(int recommend_cnt) {
		this.recommend_cnt = recommend_cnt;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
