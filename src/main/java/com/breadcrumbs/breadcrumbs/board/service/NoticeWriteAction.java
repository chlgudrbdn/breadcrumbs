package com.breadcrumbs.breadcrumbs.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.NoticeDao;
import com.breadcrumbs.breadcrumbs.dto.NoticeDto;

@Service
public class NoticeWriteAction {

	@Autowired
	private NoticeDao noticeDao;

	public boolean execute(NoticeDto dto) {
		// 가장 큰 글번호를 가져오기
		int num = noticeDao.getMaxNoticeNum();
		// 글번호 설정
		dto.setNotice_Num(num + 1);
		// 공지사항을 데이터베이스에 추가
		boolean r = noticeDao.noticeWrite(dto);
		return r;
	}
}
