package com.breadcrumbs.breadcrumbs.dto;

import org.springframework.web.multipart.MultipartFile;

public class FormBoardDto {
	private String email;
	private String board_Subject;
	private String board_Content;
	private MultipartFile board_File;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBoard_Subject() {
		return board_Subject;
	}

	public void setBoard_Subject(String board_Subject) {
		this.board_Subject = board_Subject;
	}

	public String getBoard_Content() {
		return board_Content;
	}

	public void setBoard_Content(String board_Content) {
		this.board_Content = board_Content;
	}

	public MultipartFile getBoard_File() {
		return board_File;
	}

	public void setBoard_File(MultipartFile board_File) {
		this.board_File = board_File;
	}

	@Override
	public String toString() {
		return "FormBoardDto [email=" + email + ", board_Subject="
				+ board_Subject + ", board_Content=" + board_Content
				+ ", board_File=" + board_File + "]";
	}

}
