package com.breadcrumbs.breadcrumbs.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.NoticeDao;
import com.breadcrumbs.breadcrumbs.dto.NoticeDto;

@Service
public class NoticeListAction {

	@Autowired
	private NoticeDao noticeDao;

	public List<NoticeDto> execute() {
		return noticeDao.noticeList();
	}
}
