package com.breadcrumbs.breadcrumbs.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.BoardDao;
import com.breadcrumbs.breadcrumbs.dto.BoardDto;

@Service
public class BoardModifyView {

	@Autowired
	private BoardDao boardDao;

	// 글번호를 매개변수로 받아서 하나의 BoardDto를
	// 리턴해주는 메소드
	public BoardDto execute(int num) {
		BoardDto dto = boardDao.getBoard(num);
		return dto;
	}

}
