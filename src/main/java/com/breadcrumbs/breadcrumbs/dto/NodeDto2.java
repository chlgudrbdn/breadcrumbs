package com.breadcrumbs.breadcrumbs.dto;

public class NodeDto2 {//노드 자바빈 저장 클래스

	private String id;
	private String parent;
	private String state;
	private String text;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return "NodeDto2 [id =" + id+ ", parent=" + parent + ", state=" + state
				+ ", text=" + text + ", type="+ type+ "]\n";
	}
	
}
