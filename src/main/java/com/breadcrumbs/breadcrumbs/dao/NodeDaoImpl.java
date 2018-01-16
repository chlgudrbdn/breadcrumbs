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
	public int insertChoice(ChoiceListDto choice) {//선택지 추가
		// TODO Auto-generated method stub
		return sqlSession.insert("add_choice", choice);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int insertCategoryChoice(CategoryChoiceDto cc) {
		// TODO Auto-generated method stub
		System.out.println("ccRelation Add"+cc);
		return sqlSession.insert("add_CategoryChoice", cc);
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

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<CategoryChoiceDto> checkDuplicateCC(CategoryChoiceDto cc) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectList("chkDuplicateCC", cc);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int updateCategoryChoice(CategoryChoiceDto cc) {//중복되는 cc가 있으면 choice_pick_freq 1증가
		// TODO Auto-generated method stub
		return this.sqlSession.update("updateCC", cc);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public String selectChoice(String text) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("selectChoice",text);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int updateChoice(NodeDto node) {
		// TODO Auto-generated method stub
		return this.sqlSession.update("updateChoice", node);
	}

	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public int updateNodeName(NodeDto node) {
		// TODO Auto-generated method stub
		return this.sqlSession.update("update_node_name" , node);
	}


//	public void delG(int g_no) {
//		this.sqlSession.delete("Gt.g_del",g_no);		
//	}		
	
}





