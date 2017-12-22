package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.breadcrumbs.breadcrumbs.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

//	public void setSqlSession(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int getListCount() {
		List<Integer> list = sqlSession.selectList("board.countBoardList");
		if (list == null || list.size() == 0)
			return 0;
		else
			return list.get(0);
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<BoardDto> getBoardList(Map<String, Object> map) {
		List<BoardDto> list = sqlSession.selectList("board.selectBoardList",
				map);
		if (list == null || list.size() == 0)
			return null;
		else
			return list;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public BoardDto getBoard(int num) {
		// 조회수를 1증가시키는 sql 실행
		int r = sqlSession.update("board.updateReadcount", num);
		// 업데이트에 실패하면 null 리턴
		if (r < 1)
			return null;
		// 업데이트에 성공하면 하나의 데이터를 가져오는
		// sql을 실행
		else {
			List<BoardDto> list = sqlSession.selectList("board.getBoard", num);
			if (list == null || list.size() < 1)
				return null;
			else
				return list.get(0);
		}
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int maxBoardNum() {
		List<Integer> list = sqlSession.selectList("board.maxBoardNum");
		if (list == null || list.size() < 0)
			return 0;
		else
			return list.get(0);
	}

	@Override	
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean insertBoard(BoardDto dto) {
		int r = sqlSession.insert("board.insertBoard", dto);
		if (r > 0)
			return true;
		else
			return false;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean updateBoard(Map<String, Object> map) {
		int r = sqlSession.update("board.updateBoard", map);
		if (r > 0)
			return true;
		else
			return false;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean deleteBoard(int num) {
		int r = sqlSession.insert("board.deleteBoard", num);
		if (r > 0)
			return true;
		else
			return false;
	}

}
