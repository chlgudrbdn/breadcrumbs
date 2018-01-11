package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import com.breadcrumbs.breadcrumbs.dto.ComDto;
import com.breadcrumbs.breadcrumbs.dto.GDto;


public interface GDao {

	List<GDto> getGList();//목록
	GDto getCont(int g_no);//방명록 내용
	void insertCom(ComDto c);//댓글 저장
	int getComCount(int g_no);//각 번호에 해당하는 댓글 개수
	void updatehit(int g_no);//조회수 증가
	//public abstract가 생략된 추상메서드.
	List<ComDto> getComList(int g_no);//댓글 목록
	void com_del(int comment_no);//댓글삭제
	void insertG(ComDto c);//방명록 저장
	void updateG(ComDto ec);//방명록 수정
	void delG(int g_no);//방명록 삭제
	
	public int getComMax(int g_no); // 가장 마지막 댓글 comment_no 값 구하기 
	ComDto getComBean(Map m); // 입력된 댓글 1개 추출
}
