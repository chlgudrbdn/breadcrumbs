package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.breadcrumbs.breadcrumbs.dto.NoticeDto;

@Repository
public class NoticeDaoImpl implements NoticeDao {
	
	@Autowired
	private SqlSession sqlSession;

//	public void setSqlSession(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}

	@Override
	@Transactional	// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<NoticeDto> noticeList() {
		List<NoticeDto> list = sqlSession.selectList("board.selectNoticeList");
		if (list == null || list.size() < 1)
			return null;
		else
			return list;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean readCountUpdate(int num) {
		int r = sqlSession.update("board.updateNotice", num);
		if (r > 0)
			return true;
		else
			return false;
	}

	@Override
	@Transactional	// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public NoticeDto getNotice(int num) {
		List<NoticeDto> list = sqlSession.selectList("board.getNotice", num);
		if (list == null || list.size() < 1)
			return null;
		else
			return list.get(0);
	}

	@Override
	@Transactional	// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean noticeWrite(NoticeDto dto) {
		int r = sqlSession.insert("board.insertNotice", dto);
		if (r > 0)
			return true;
		return false;
	}

	@Override
	@Transactional	// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int getMaxNoticeNum() {
		List<Integer> list = sqlSession.selectList("board.maxNoticeNum");
		if (list == null || list.size() < 1)
			return 0;
		else
			return list.get(0);
	}

}
