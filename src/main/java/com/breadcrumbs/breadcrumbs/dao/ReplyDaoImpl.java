package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	
	@Autowired
	private SqlSession sqlSession;

//	public void setSqlSession(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}

	@Override
	@Transactional // 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<Map<String, Object>> getReplyList(int num) {
		List<Map<String, Object>> list = sqlSession.selectList(
				"board.getReplyList", num);
		return list;
	}

	@Override
	@Transactional // 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean insertReply(Map<String, Object> map) {
		// 가장 큰 글번호 목록 가져오기
		List<Integer> list = sqlSession.selectList("board.maxReplyNum");
		int num = 0;
		if (list == null || list.size() < 1)
			num = 1;
		else
			num = list.get(0) + 1;
		// 추가될 글 번호 설정
		map.put("num", num);
		// 데이터 추가
		int r = sqlSession.insert("board.insertReply", map);
		if (r > 0)
			return true;
		else
			return false;
	}

}
