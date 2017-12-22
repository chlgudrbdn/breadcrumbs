package com.breadcrumbs.breadcrumbs.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.NoticeDao;
import com.breadcrumbs.breadcrumbs.dto.NoticeDto;

@Service
public class NoticeDetailAction {

	@Autowired
	private NoticeDao noticeDao;

	public NoticeDto execute(String num) {
		int noticeNum = Integer.parseInt(num);
		boolean r = noticeDao.readCountUpdate(noticeNum);
		if (r == false)
			return null;
		else
			return noticeDao.getNotice(noticeNum);

	}

}
