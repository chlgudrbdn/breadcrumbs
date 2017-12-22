package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import com.breadcrumbs.breadcrumbs.dto.BoardDto;

public interface BoardDao {
	// 전체 데이터 개수를 리턴하는 메소드
	public int getListCount();

	// 맵을 받아서 데이터 목록을 리턴하는 메소드
	public List<BoardDto> getBoardList(Map<String, Object> map);

	// 조회수를 1증가시키고 하나의 데이터를 가져오는 메소드
	public BoardDto getBoard(int num);

	// 가장 큰 Board_Num을 찾아오는 메소드
	public int maxBoardNum();

	// 데이터를 삽입하는 메소드
	public boolean insertBoard(BoardDto dto);

	// 데이터를 갱신하는 메소드
	public boolean updateBoard(Map<String, Object> map);

	// 정수 1개를 받아서 데이터를 삭제하는 메소드
	public boolean deleteBoard(int num);
}
