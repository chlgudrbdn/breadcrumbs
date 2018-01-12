package com.breadcrumbs.breadcrumbs.dto;

public class CategoryChoiceDto {

	/*
	 * mybatis를 사용할려면 변수명과 필드명이 같아야 한다.
	 */
	private String category;
	private String text;
	private String pre_choice;
	private int choice_pick_freq;
	private int choice_weight;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPre_choice() {
		return pre_choice;
	}
	public void setPre_choice(String pre_choice) {
		this.pre_choice = pre_choice;
	}
	public int getChoice_pick_freq() {
		return choice_pick_freq;
	}
	public void setChoice_pick_freq(int choice_pick_freq) {
		this.choice_pick_freq = choice_pick_freq;
	}
	public int getChoice_weight() {
		return choice_weight;
	}
	public void setChoice_weight(int choice_weight) {
		this.choice_weight = choice_weight;
	}
	


}
