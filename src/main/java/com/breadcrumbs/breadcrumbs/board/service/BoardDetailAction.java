package com.breadcrumbs.breadcrumbs.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.BoardDao;
import com.breadcrumbs.breadcrumbs.dto.BoardDto;

@Service
public class BoardDetailAction {

	@Autowired
	private BoardDao boardDao;

	public BoardDto execute(int num) {
		BoardDto dto = boardDao.getBoard(num);
		return dto;
	}
}
