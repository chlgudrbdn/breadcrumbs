package com.breadcrumbs.breadcrumbs.dto;

public class CategoryChoiceDto {

	/*
	 * mybatis를 사용할려면 변수명과 필드명이 같아야 한다.
	 */
	private String text;
	private String code_piece;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCode_piece() {
		return code_piece;
	}
	public void setCode_piece(String code_piece) {
		this.code_piece = code_piece;
	}


}
