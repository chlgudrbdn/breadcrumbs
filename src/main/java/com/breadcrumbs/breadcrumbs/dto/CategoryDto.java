package com.breadcrumbs.breadcrumbs.dto;

public class CategoryDto {

	/*
	 * mybatis를 사용할려면 변수명과 필드명이 같아야 한다.
	 */
	private String category;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
