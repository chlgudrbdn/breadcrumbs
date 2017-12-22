package com.breadcrumbs.breadcrumbs.dto;

import java.sql.Date;

public class BoardDto {
	private int board_Num;
	private String board_Id;
	private String board_Subject;
	private String board_Content;
	private String board_file;
	private int board_ReadCount;
	private Date board_Date;
	private String board_Ip;

	public BoardDto() {
		super();
	}

	public BoardDto(int board_Num, String board_Id, String board_Subject,
			String board_Content, String board_file, int board_ReadCount,
			Date board_Date, String board_Ip) {
		super();
		this.board_Num = board_Num;
		this.board_Id = board_Id;
		this.board_Subject = board_Subject;
		this.board_Content = board_Content;
		this.board_file = board_file;
		this.board_ReadCount = board_ReadCount;
		this.board_Date = board_Date;
		this.board_Ip = board_Ip;
	}

	public int getBoard_Num() {
		return board_Num;
	}

	public void setBoard_Num(int board_Num) {
		this.board_Num = board_Num;
	}

	public String getBoard_Id() {
		return board_Id;
	}

	public void setBoard_Id(String board_Id) {
		this.board_Id = board_Id;
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

	public String getBoard_file() {
		return board_file;
	}

	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}

	public int getBoard_ReadCount() {
		return board_ReadCount;
	}

	public void setBoard_ReadCount(int board_ReadCount) {
		this.board_ReadCount = board_ReadCount;
	}

	public Date getBoard_Date() {
		return board_Date;
	}

	public void setBoard_Date(Date board_Date) {
		this.board_Date = board_Date;
	}

	public String getBoard_Ip() {
		return board_Ip;
	}

	public void setBoard_Ip(String board_Ip) {
		this.board_Ip = board_Ip;
	}

	@Override
	public String toString() {
		return "BoardDto [board_Num=" + board_Num + ", board_Id=" + board_Id
				+ ", board_Subject=" + board_Subject + ", board_Content="
				+ board_Content + ", board_file=" + board_file
				+ ", board_ReadCount=" + board_ReadCount + ", board_Date="
				+ board_Date + ", board_Ip=" + board_Ip + "]";
	}

}
