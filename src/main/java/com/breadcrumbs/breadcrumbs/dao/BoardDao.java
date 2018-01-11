package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;

import com.breadcrumbs.breadcrumbs.dto.BoardDto;

public interface BoardDao {

	public void insertBoard(BoardDto b) throws Exception;

	public List<BoardDto> getBoardList(int page) throws Exception;

	public int getListCount() throws Exception;

	public BoardDto getBoardCont(int board_num) throws Exception;

	public void boardHit(int board_num) throws Exception;

	public void boardEdit(BoardDto b) throws Exception;

	public void boardDelete(int board_num) throws Exception;

	public void refEdit(BoardDto b) throws Exception;

	public void boardReplyOk(BoardDto b) throws Exception;
}
