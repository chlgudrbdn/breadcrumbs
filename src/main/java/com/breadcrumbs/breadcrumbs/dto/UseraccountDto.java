package com.breadcrumbs.breadcrumbs.dto;

public class UseraccountDto {
	private String email;
	private String pass;
	private String payment_type;


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getPayment_type() {
		return payment_type;
	}


	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}


	@Override
	public String toString() {
		return "Member [email=" + email + ", pass=" + pass + ", payment_type=" + payment_type+ "]";
	}
}
