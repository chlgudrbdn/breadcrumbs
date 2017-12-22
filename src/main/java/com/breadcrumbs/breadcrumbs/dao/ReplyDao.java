package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

public interface ReplyDao {
	// 원본 글번호에 해당하는 reply 목록을 가져오는 메소드
	public List<Map<String, Object>> getReplyList(int num);

	// 맵을 매개변수로 해서 데이터를 삽입하는 메소드
	public boolean insertReply(Map<String, Object> map);
}
