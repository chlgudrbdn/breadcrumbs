package com.breadcrumbs.breadcrumbs.dto;

public class TypeDto {//노드 자바빈 저장 클래스

	private String type;
	
	@Override
	public String toString() {
		return "{type :" + type+"}\n";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
