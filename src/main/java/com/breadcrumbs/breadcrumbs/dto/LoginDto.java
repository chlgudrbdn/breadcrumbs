package com.breadcrumbs.breadcrumbs.dto;

//로그인 처리만을 위한 DTO
public class LoginDto {
	private String email;
	private String pw;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", pw=" + pw + "]";
	}

}
