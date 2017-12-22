package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;

import com.breadcrumbs.breadcrumbs.dto.NoticeDto;

public interface NoticeDao {
	// 5개의 공지사항을 가져오는 메소드
	public List<NoticeDto> noticeList();

	// 글번호를 받아서 readcount를 증가시켜주는 메소드
	public boolean readCountUpdate(int num);

	// 글번호를 받아서 데이터를 검색해서 리턴하는 메소드
	public NoticeDto getNotice(int num);

	// blognotice 테이블에서 가장 큰 글번호를 가져오는 메소드
	public int getMaxNoticeNum();

	// NoticeDto 1개를 받아서 데이터베이스에 삽입하는 메소드
	public boolean noticeWrite(NoticeDto dto);
}
