package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.breadcrumbs.breadcrumbs.dto.CategoryChoiceDto;
import com.breadcrumbs.breadcrumbs.dto.CategoryDto;
import com.breadcrumbs.breadcrumbs.dto.ChoiceListDto;
import com.breadcrumbs.breadcrumbs.dto.MemberTreeRelationDto;
import com.breadcrumbs.breadcrumbs.dto.NodeDto;
import com.breadcrumbs.breadcrumbs.dto.TreeNodeRelationDto;

@Repository
public class NodeDaoImpl implements NodeDao {

	@Autowired
	private SqlSession sqlSession;//mybatis 쿼리문 실행 변수

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}//스프링 setter DI 의존관계를 설정

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<NodeDto> getNodeList(String id) {//t_n_relation에서 노드 id만 불러온 뒤 하위 테이블 불러오기.
		return sqlSession.selectList("nodeList", id);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<NodeDto> getRootNodes(String tree_no) {//t_n_relation에서 노드 id만 불러온 뒤 node 테이블에 루트에 해당하는 것 불러오기.
		return sqlSession.selectList("getRootNode", tree_no);
	}
	
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
//	public List<String> getRecommendCategoryList(String value) {//t_n_relation에서 노드 id만 불러온 뒤 node 테이블에 거기에 해당하는 것 불러오기.
	public List<String> getRecommendCategoryList(String value) {//t_n_relation에서 노드 id만 불러온 뒤 node 테이블에 거기에 해당하는 것 불러오기.
		return sqlSession.selectList("categoryList", value);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public NodeDto getNode(String id) {//노드 로딩 R
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getNode", id );
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public void insertNode(NodeDto node) {//노드 저장 C
		// TODO Auto-generated method stub
		this.sqlSession.insert("add_node", node);
	}

	public void insertTreeNodeRelation(TreeNodeRelationDto t_n_relation) {
		this.sqlSession.insert("add_t_n_relation", t_n_relation);
	}
	
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public void updateNode(int id) {//노드의 코드 수정 U
		// TODO Auto-generated method stub
		
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int getNodeCount(int tree_no) {//각 노드에 노드 개수
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int getNodeDepth(int id) {//각 노드의 깊이를
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public void node_del(int id) {//노드삭제
		// TODO Auto-generated method stub
		
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<NodeDto> getComList(int tree_no) {//전체 노드 목록
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int getLeafNode(NodeDto id) { // 가장 마지막 댓글 comment_no 값 구하기 
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public NodeDto getNodeDto(Map m) {// 입력된 댓글 1개 추출
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public void insertTree(MemberTreeRelationDto m_t_relation) {
		this.sqlSession.insert("tree_insert", m_t_relation);
	}


	
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int getLastTreeSeq() {//전체 노드 목록
		int i = this.sqlSession.selectOne("tree_max");
		System.out.println("Lastseq"+i);
//		return this.sqlSession.selectOne("tree_max");
		return i;
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<String> checkDuplicateCategory(String category) {//카테고리 중복여부 확인
		// TODO Auto-generated method stub
		
		return this.sqlSession.selectList("category_chk", category);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public String getCategory(String tree_no) {//카테고리 중복여부 확인
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("category_get", tree_no);
	}
	
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public void insertCategory(String category) {//카테고리 중복여부 확인
		// TODO Auto-generated method stub
		sqlSession.insert("add_category", category);
	}
	
	

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<String> checkDuplicateChoice(String text) {//선택지 중복여부 확인
		// TODO Auto-generated method stub
		return this.sqlSession.selectList("choice_chk", text);
	}
	
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public void insertChoice(ChoiceListDto choiceList) {//선택지 추가
		// TODO Auto-generated method stub
		sqlSession.insert("add_choice", choiceList);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public void insertCategoryChoice(CategoryChoiceDto cc) {
		// TODO Auto-generated method stub
		System.out.println("ccRelation Add");
		sqlSession.insert("add_CategoryChoice", cc);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<MemberTreeRelationDto> getTreeList(String email) {
		// TODO Auto-generated method stub
		return  this.sqlSession.selectList("get_treeList", email);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int addNode(NodeDto node) {
		// TODO Auto-generated method stub
		return this.sqlSession.insert("add_node", node);
	}

	
	/*
	 *  mybatis 쿼리문 실행 메서드
	 *   1.단 한개의 레코드만 검색: selectOne()
	 *   2.하나이상의 레코드를 검색해서 컬렉션 List로 반환:selectList()
	 *   3.레코드 저장:insert()
	 *   4.레코드 수정:update()
	 *   5.레코드 삭제:delete() 
	 */
//	public List<MemberTreeRelationDto> getGList() {
//		return sqlSession.selectList("Gt.g_list");
//	//Gt는 네임 스페이스 이름, g_list는 select 아이디 이름
//	}
//
//	/*방명록 내용*/
//	public MemberTreeRelationDto getCont(int g_no) {
//		return this.sqlSession.selectOne("Gt.g_cont",g_no);
//	}
//
//	/*댓글 저장*/
//	public void insertCom(NodeDto c) {
//		this.sqlSession.insert("Gt.com_in",c);		
//	}
//
//	//댓글 개수
//	public int getComCount(int g_no) {
//		return this.sqlSession.selectOne("Gt.c_count",g_no);
//	}
//
//	//방명록 조회수 증가
//	public void updatehit(int g_no) {
//		this.sqlSession.update("Gt.g_hit",g_no);
//		//Gt는 네임스페이스 이름, g_hit는  update id 이름
//	}
//
//	/*댓글목록*/
//	public List<NodeDto> getComList(int g_no) {
//		return this.sqlSession.selectList("Gt.c_list",g_no);
//	}
//
//	//댓글 삭제
//	public void com_del(int comment_no) {
//		this.sqlSession.delete("Gt.c_del", comment_no);
//		//Gt는 네임스페이스 이름,c_del은 delete 아이디이름
//	}
//
//	/* 댓글 저장 */
//	public void insertG(NodeDto c) {		
//		this.sqlSession.insert("Gt.g_in",c);
//	}
//	
//	/* 가장 마지막 댓글 comment_no 값 구하기 */
//	public int getComMax(int g_no){
//		return sqlSession.selectOne("Gt.c_max",g_no);
//	}
//	
//	
//	/* 입력된 댓글 1개 추출 */
//	public NodeDto getNodeDto(Map m){
//		return sqlSession.selectOne("Gt.c_bean",m);
//	}	
//	
//
//	/*방명록 수정*/
//	public void updateG(NodeDto ec) {
//		this.sqlSession.update("Gt.g_edit",ec);		
//	}
//
//	/*방명록 삭제*/
//	public void delG(int g_no) {
//		this.sqlSession.delete("Gt.g_del",g_no);		
//	}		
	
}





