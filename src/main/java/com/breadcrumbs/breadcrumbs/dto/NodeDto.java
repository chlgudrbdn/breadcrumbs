package com.breadcrumbs.breadcrumbs.dto;

public class NodeDto {//노드 자바빈 저장 클래스

	private String id;
	private String parent;
	private String state;
	private String text;
	private String li_attr;
	
	public String getLi_attr() {
		return li_attr;
	}
	public void setLi_attr(String li_attr) {
		this.li_attr = li_attr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
		
	
}
