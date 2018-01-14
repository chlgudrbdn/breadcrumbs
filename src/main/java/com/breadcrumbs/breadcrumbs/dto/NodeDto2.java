package com.breadcrumbs.breadcrumbs.dto;

public class NodeDto2 {//노드 자바빈 저장 클래스

	private String id;
	private String parent;
	private String state;
	private String text;
	private TypeDto li_attr;
	
	public TypeDto getLi_attr() {
		return li_attr;
	}
	public void setLi_attr(TypeDto li_attr) {
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
	
	@Override
	public String toString() {
		return "NodeDto [id =" + id+ ", parent=" + parent + ", state=" + state
				+ ", text=" + text + ", li_attr="+ li_attr+ "]\n";
	}
	
}
