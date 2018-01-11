package com.breadcrumbs.breadcrumbs.dto;

//로그인 처리만을 위한 DTO
public class LoginDto {
	private String email;
	private String pass;

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

	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", pass=" + pass + "]";
	}

}
