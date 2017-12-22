package com.breadcrumbs.breadcrumbs.dto;

import java.sql.Date;

public class NoticeDto {
	private int notice_Num;
	private String notice_Subject;
	private String notice_Content;
	private int notice_Readcount;
	private Date notice_Date;

	public NoticeDto() {
		super();
	}

	public NoticeDto(int notice_Num, String notice_Subject,
			String notice_Content, int notice_Readcount, Date notice_Date) {
		super();
		this.notice_Num = notice_Num;
		this.notice_Subject = notice_Subject;
		this.notice_Content = notice_Content;
		this.notice_Readcount = notice_Readcount;
		this.notice_Date = notice_Date;
	}

	public int getNotice_Num() {
		return notice_Num;
	}

	public void setNotice_Num(int notice_Num) {
		this.notice_Num = notice_Num;
	}

	public String getNotice_Subject() {
		return notice_Subject;
	}

	public void setNotice_Subject(String notice_Subject) {
		this.notice_Subject = notice_Subject;
	}

	public String getNotice_Content() {
		return notice_Content;
	}

	public void setNotice_Content(String notice_Content) {
		this.notice_Content = notice_Content;
	}

	public int getNotice_Readcount() {
		return notice_Readcount;
	}

	public void setNotice_Readcount(int notice_Readcount) {
		this.notice_Readcount = notice_Readcount;
	}

	public Date getNotice_Date() {
		return notice_Date;
	}

	public void setNotice_Date(Date notice_Date) {
		this.notice_Date = notice_Date;
	}

	@Override
	public String toString() {
		return "NoticeDto [notice_Num=" + notice_Num + ", notice_Subject="
				+ notice_Subject + ", notice_Content=" + notice_Content
				+ ", notice_Readcount=" + notice_Readcount + ", notice_Date="
				+ notice_Date + "]";
	}
}
