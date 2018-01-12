package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import com.breadcrumbs.breadcrumbs.dto.CategoryChoiceDto;
import com.breadcrumbs.breadcrumbs.dto.ChoiceListDto;
import com.breadcrumbs.breadcrumbs.dto.MemberTreeRelationDto;
import com.breadcrumbs.breadcrumbs.dto.NodeDto;
import com.breadcrumbs.breadcrumbs.dto.TreeNodeRelationDto;

public interface NodeDao {

	List<NodeDto> getNodeList(String tree_no);//목록
	List<String> getRecommendCategoryList(String term);//목록
	MemberTreeRelationDto getNode(int g_no);//노드 로딩 R
	void insertNode(NodeDto node);//노드 저장 C
	void updateNode(int id);//노드의 코드 수정 U
	int getNodeCount(int tree_no);//각 노드에 노드 개수
	int getNodeDepth(int id);//각 노드의 깊이를
	void node_del(int id);//노드삭제
	List<NodeDto> getComList(int tree_no);//전체 노드 목록
	
	int getLeafNode(NodeDto id); // 가장 마지막 댓글 comment_no 값 구하기 
	NodeDto getNodeDto(Map m); // 입력된 댓글 1개 추출
	
	void insertTree(MemberTreeRelationDto m_t_relation);//tree 목록. 카테고리가 선행되어야 무결성에 위반되지 않는다.
	
	void insertTreeNodeRelation(TreeNodeRelationDto t_n_relation); //t_n_realation 에 tree와 node추가. 다른 테이블의 기본키 되는 tree_no와 node의 id가 선행되어야 무결성 침해되지 않음.
	
	List<String> checkDuplicateCategory(String category);//중복되는 카테고리 확인
	void insertCategory(String category);//카테고리 추가
	
	List<String> checkDuplicateChoice(String text);//중복되는 선택지 확인
	void insertChoice(ChoiceListDto choiceList); //선택지 추가
	
	void insertCategoryChoice(CategoryChoiceDto cc); // c_c_relation 추가
	
	int getLastTreeSeq();//Tree_no 마지막 번호 확인
	
}
