package com.breadcrumbs.breadcrumbs.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.BoardDao;

@Service
public class BoardDeleteAction {

	@Autowired
	private BoardDao boardDao;

	public boolean execute(int num) {
		return boardDao.deleteBoard(num);
	}
}
