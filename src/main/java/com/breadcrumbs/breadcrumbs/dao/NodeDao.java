package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import com.breadcrumbs.breadcrumbs.dto.MemberTreeRelationDto;
import com.breadcrumbs.breadcrumbs.dto.NodeDto;

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
	
	public int getLeafNode(NodeDto id); // 가장 마지막 댓글 comment_no 값 구하기 
	NodeDto getNodeDto(Map m); // 입력된 댓글 1개 추출
	void insertTree();
}
