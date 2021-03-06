package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.breadcrumbs.breadcrumbs.dto.ComDto;
import com.breadcrumbs.breadcrumbs.dto.GDto;




@Repository
public class GDaoImpl implements GDao {

	@Autowired
	private SqlSession sqlSession;//mybatis 쿼리문 실행 변수

	//public void setSqlSession(SqlSession sqlSession) {
	//	this.sqlSession = sqlSession;
	//}//스프링 setter DI 의존관계를 설정

	/*
	 *  mybatis 쿼리문 실행 메서드
	 *   1.단 한개의 레코드만 검색: selectOne()
	 *   2.하나이상의 레코드를 검색해서 컬렉션 List로 반환:selectList()
	 *   3.레코드 저장:insert()
	 *   4.레코드 수정:update()
	 *   5.레코드 삭제:delete() 
	 */
	public List<GDto> getGList() {
		return sqlSession.selectList("Gt.g_list");
	//Gt는 네임 스페이스 이름, g_list는 select 아이디 이름
	}

	/*방명록 내용*/
	public GDto getCont(int g_no) {
		return this.sqlSession.selectOne("Gt.g_cont",g_no);
	}

	/*댓글 저장*/
	public void insertCom(ComDto c) {
		this.sqlSession.insert("Gt.com_in",c);		
	}

	//댓글 개수
	public int getComCount(int g_no) {
		return this.sqlSession.selectOne("Gt.c_count",g_no);
	}

	//방명록 조회수 증가
	public void updatehit(int g_no) {
		this.sqlSession.update("Gt.g_hit",g_no);
		//Gt는 네임스페이스 이름, g_hit는  update id 이름
	}

	/*댓글목록*/
	public List<ComDto> getComList(int g_no) {
		return this.sqlSession.selectList("Gt.c_list",g_no);
	}

	//댓글 삭제
	public void com_del(int comment_no) {
		this.sqlSession.delete("Gt.c_del", comment_no);
		//Gt는 네임스페이스 이름,c_del은 delete 아이디이름
	}

	/* 댓글 저장 */
	public void insertG(ComDto c) {		
		this.sqlSession.insert("Gt.g_in",c);
	}
	
	/* 가장 마지막 댓글 comment_no 값 구하기 */
	public int getComMax(int g_no){
		return sqlSession.selectOne("Gt.c_max",g_no);
	}
	
	
	/* 입력된 댓글 1개 추출 */
	public ComDto getComBean(Map m){
		return sqlSession.selectOne("Gt.c_bean",m);
	}	
	

	/*방명록 수정*/
	public void updateG(ComDto ec) {
		this.sqlSession.update("Gt.g_edit",ec);		
	}

	/*방명록 삭제*/
	public void delG(int g_no) {
		this.sqlSession.delete("Gt.g_del",g_no);		
	}		
	
}





