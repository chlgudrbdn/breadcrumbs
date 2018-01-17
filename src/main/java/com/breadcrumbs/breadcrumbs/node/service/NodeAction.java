package com.breadcrumbs.breadcrumbs.node.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.breadcrumbs.breadcrumbs.dao.NodeDaoImpl;
import com.breadcrumbs.breadcrumbs.dto.CategoryChoiceDto;
import com.breadcrumbs.breadcrumbs.dto.ChoiceListDto;
import com.breadcrumbs.breadcrumbs.dto.MemberTreeRelationDto;
import com.breadcrumbs.breadcrumbs.dto.NodeDto;
import com.breadcrumbs.breadcrumbs.dto.TreeNodeRelationDto;


@Service
public class NodeAction{

	@Autowired
	private NodeDaoImpl NodeDao;

	/*
	 * public void setBoardDao(BoardDAOImpl boardDao) { this.boardDao =
	 * boardDao; }
	 */

	public void insert(NodeDto node) throws Exception {
		NodeDao.insertNode(node);
	}
	
	public int insertChoice(ChoiceListDto choice) {
		return NodeDao.insertChoice(choice);
	}
	
//	public List<String> getRecommendCategoryList(String value) {
	public List<String> getRecommendCategoryList(String value) {
		return NodeDao.getRecommendCategoryList(value);
	}
	
	public int makeTreeNo(MultipartHttpServletRequest request) {
		MemberTreeRelationDto m_t_relation = new MemberTreeRelationDto();
		
		
		//tree_no 미리 예측해서 폴더도 미리 만든다.
		int tree_no=NodeDao.getLastTreeSeq();
//		tree_no = tree_no==-1 ? 0 : tree_no;//아무것도 없는 경우 가정(null리턴할 땐 -1 리턴하도록 sql에서 NVL 함수 써둠)
//		tree_no++;
		
		String uploadPath = request.getRealPath("dataForAnalysis") +"\\"+ tree_no;
		System.out.println(uploadPath); // C:\Spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\breadcrumbs\dataForAnalysis하위에 각 트리이름을 가진 폴더가 있고 거기에 데이터를 저장한다.

		File desti = new File(uploadPath);//폴더 생성.
		boolean dirResult = desti.mkdir();
		System.out.println("mkdir work?"+dirResult);
		
		// 매개변수로 넘어온 파일 가져오기
		MultipartFile report = request.getFile("dataInp");
		String filename = report.getOriginalFilename(); //db엔 이게 저장되는데 아마 이걸로 불러올 것이다.
		
		// 파일 저장 경로 생성
		String filepath = uploadPath + "\\" + filename;
		
		File f = new File(filepath);//파일 넣는다.
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			fos.write(report.getBytes());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
		
		System.out.println("db Setting start");
	//   새로운 카테고리의 경우 추가하는 메소드가 필요하다.
		String category = request.getParameter("category");
		if(NodeDao.checkDuplicateCategory(category).size() == 0  ) {//만약 중복되는 카테고리가 없는 새로운 카테고리라면
			NodeDao.insertCategory(category);
		}//중복되는 카테고리 있으면 딱히 m_t_relation, c_c_relation의 무결성에 문제 줄 것이 없다.
		System.out.println("category setting done");
		
		//새로운 선택지의 경우 추가하는 메소드가 필요.
		String text = request.getParameter("rootnodeName");//선택지 중복여부 확인 후 없으면 추가.
		if(NodeDao.checkDuplicateChoice(text).size() == 0  ) {//만약 중복되는 카테고리가 없는 새로운 카테고리라면
			System.out.println(text);
			ChoiceListDto choiceList = new ChoiceListDto();
			choiceList.setText(text);
			choiceList.setCode_piece("setwd("+uploadPath+")");//최초엔 코드는 없는 상태로 넣는다.
			NodeDao.insertChoice(choiceList);
		}//중복되는 카테고리 있으면 딱히 c_c_relation, node의 무결성에 문제 줄 것이 없다.
		System.out.println("choiceList setting done");
		
		// 카테고리_선택지 관계 추가
		CategoryChoiceDto cc = new CategoryChoiceDto();
		cc.setCategory(category);
		cc.setText(text);
		cc.setPre_choice(null);
		cc.setChoice_pick_freq(1);
		cc.setChoice_weight(0.0);
		System.out.println("chk the cc="+cc);
		if(NodeDao.checkDuplicateCC(cc).size() < 1   ) {
			NodeDao.insertCategoryChoice(cc);
		}else {
			NodeDao.updateCategoryChoice(cc);
		}
		System.out.println("ccRelation setting done");
		
		//    node에도 해당 root 노드 추가 시킨다. id, parent, state, text, li_attr 등. 선택지인 text는 일단 비워둔다.
		//    루트는 파일노드로 간주. 이때 node의 li_attr에 type을 file로 정해둘 것.
		NodeDto node = new NodeDto();
		String nodeId = tree_no+"-1-"+text;//root니까 깊이는 1로 간주(jstree기준)
		node.setId(nodeId);
		node.setLi_attr( "type:file" );
		node.setParent("#");//Root노드는 #으로 불러오게 되어 .
		node.setState("undetermined");//default로 undetermined를 정하긴 했는데 일단 넣는다.
		node.setText(text); // choice
		NodeDao.insertNode(node);
		System.out.println("node setting");
		
		//사용자-트리 관계 추가
		//m_t_relation 테이블에 트리 시퀀스+1, 추천수는 0, 카테고리, 세션에 있는 멤버 객체(아마도 useraccount)이메일 넣는다.
		m_t_relation.setCategory(category);
		m_t_relation.setDatafilename(filename);//파일 명만 넣는다.
		System.out.println("email : ="+request.getParameter("email"));
		m_t_relation.setEmail(request.getParameter("email"));
//		m_t_relation.setRecommend_cnt(0);//null이어도 xml에서 그냥 0으로 처리.
//		m_t_relation.setTree_no(tree_no); // null이어도 그냥 seq로 넣는다.
		NodeDao.insertTree(m_t_relation);
		System.out.println("m_t_relation setting");
		
		//       t_n_relation에는 루트 노드로 하나 넣는다. 30자가 넘지 않도로 해야하는데 seq는 27자 까지가 한계. 절충해서 대충 20자 정도만 넣을 수 있게하자.
		TreeNodeRelationDto t_n_relation=new TreeNodeRelationDto();
		t_n_relation.setId(nodeId);
		t_n_relation.setTree_no(tree_no);
		NodeDao.insertTreeNodeRelation(t_n_relation);
		System.out.println("t_n_relation setting");
	
		return tree_no;
	}

	public List<String> executeCode(List<String> codes)throws REXPMismatchException, REngineException {
		RConnection c = new RConnection();
		List<String> result = new ArrayList<String>();

		for (String code : codes) {
			System.out.println("code="+code.replaceAll("(^\\p{Z}+|\\p{Z}+$)", ""));
			try {
				if(!code.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "").equals("")
						|| !code.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "").substring(0).equals("#")) {
					REXP x = c.eval(code);
					if(x.asString()!="") {
						System.out.println("x="+x.asString());
						result.add(x.asString());
					}
					System.out.println("-------------done----------------");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(result);
		return result;
	}
	

	public List<NodeDto> getNodeList(String id) {
		// TODO Auto-generated method stub
		return NodeDao.getNodeList(id);
	}
	
	

	public NodeDto getNode(String id) {
		// TODO Auto-generated method stub
		
		return NodeDao.getNode(id);
	}


	public List<NodeDto> getRootNode(String tree_no) {
		// TODO Auto-generated method stub
		return NodeDao.getRootNodes(tree_no);//root노드만 있을거라 가정했으므로 다 끌고와봐야 루트노드만 리턴할 것이다. 여러 루트노드가 생길 수도 있게 일단은 넓게 해둔다.
	}


	public List<MemberTreeRelationDto> getTreeList(String email) {
		
		return NodeDao.getTreeList(email);
	}


	public boolean insertNode(NodeDto node) {
		// TODO Auto-generated method stub
		try {
			int inserted = NodeDao.addNode(node);//1이면 삽입 성공.
			TreeNodeRelationDto t_n_relation=new TreeNodeRelationDto();
			t_n_relation.setId(node.getId());
			t_n_relation.setTree_no(  Integer.parseInt( node.getId().split("-")[0] )  );
			NodeDao.insertTreeNodeRelation(t_n_relation);
			System.out.println("t_n_relation setting");
	
			String text = node.getText();
			if(NodeDao.checkDuplicateChoice(text).size() == 0  ) {//만약 중복되는 카테고리가 없는 새로운 카테고리라면
				System.out.println(text);
				ChoiceListDto choiceList = new ChoiceListDto();
				choiceList.setText(text);
				choiceList.setCode_piece("");//최초엔 코드는 없는 상태로 넣는다.
				NodeDao.insertChoice(choiceList);
			}//중복되는 카테고리 있으면 딱히 c_c_relation, node의 무결성에 문제 줄 것이 없다.
			System.out.println("choiceList setting");
			
			CategoryChoiceDto cc = new CategoryChoiceDto();
			cc.setCategory(NodeDao.getCategory(node.getId().split("-")[0]));
			cc.setText(text);
			cc.setPre_choice( NodeDao.getNode(node.getParent()).getText() );//부모노드의 선택지
			cc.setChoice_pick_freq(1);
			cc.setChoice_weight(0.0);
			System.out.println(cc);
			NodeDao.insertCategoryChoice(cc);
			System.out.println("ccRelation setting");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getCategory(String tree_no) {
		return NodeDao.getCategory(tree_no);		
	}
	
	public String getParentText(NodeDto node) {
		return 	NodeDao.getNode( node.getParent() ).getText() ;		
	}	

	
	public String selectChoice(String text) { //code_piece반환
		return NodeDao.selectChoice(text);
	}

//	public boolean updateNodeChoice(NodeDto node) {
//		// TODO Auto-generated method stub
//		return NodeDao.updateChoice(node);
//	}


	public List<String> checkDuplicateChoice(String text) {
		// TODO Auto-generated method stub
		return NodeDao.checkDuplicateChoice(text);
	}

	public int updateNodeName(NodeDto node, String code_piece) {
		// TODO Auto-generated method stub
		String text= node.getText();
		String tree_no=node.getId().split("-")[0];
		System.out.println("tree_no chk="+tree_no);
		CategoryChoiceDto cc = new CategoryChoiceDto();
		cc.setCategory(getCategory(tree_no) );
		cc.setText(text);
		cc.setPre_choice( getParentText(node) );
		cc.setChoice_pick_freq(1);
		cc.setChoice_weight(0.0);
		System.out.println("chk the cc="+cc);
		
		if(NodeDao.checkDuplicateCC(cc).size() > 0) { //일단 이 같은 이름의 choice가 존재하는데(+sql구문으로 이미 pre_choice,카테고리와 도 동일한지 확인) code_piece가 다를 때.
			// 카테고리_선택지 관계 갱신. 같은 코드를 사용하기 때문.
			// text만 잘 지정하면 getChoice.node ajax로 이미 자동으로 갱신하고 있을 것이다.
			NodeDao.updateCategoryChoice(cc);
			return 0;//이건 그냥 기존 choice를 갖도록 하는 게 좋을 것 같다. 원래대로라면 추천 코드를 넣는 식으로 하겠지만 아직 어렵다. 후순위로 둔다.
//		}else if(checkDuplicateChoice(text).size() > 0 && NodeDao.selectChoice(text).equals(code_piece) ) {//기존에 있는 choice를 사용했단 얘기다. 역시 내버려두는게 상책이다.
//			return 0;
		}else if(checkDuplicateChoice(text).size() <= 0) {//choiceList가 중복된 경우가 그냥 없는 경우 새로운 노드 넣는 것 처럼하되 세부 사항이 조금 다른 방식으로 간다. 크게 세곳을 건드린다. c_c_relation, choice_list, node
			// 선택지 추가
			ChoiceListDto choice = new ChoiceListDto();
			choice.setText(text);
			choice.setCode_piece(code_piece);
			int insertChoiceResult = insertChoice(choice);//기본키인 choice 먼저 건든다. 되면 1이 출력
			System.out.println("choiceList setting done result="+insertChoiceResult);
			if(insertChoiceResult <1) {
				return 1;
			}
			
			//node 수정.
			int updateNodeNameResult = NodeDao.updateNodeName(node);
			String[] idSplit = node.getId().split("-");
			String newId = idSplit[0]+"-"+idSplit[1]+"-"+text;
			node.setText(newId);
			node.setLi_attr(tree_no);
			int updatedNodeIdResult = NodeDao.updateNode(node);
			int updateTNResult = NodeDao.updateTN(node);
			System.out.println("updateNodeNameResult="+updateNodeNameResult);
			System.out.println("updatedNodeIdResult="+updatedNodeIdResult);
			System.out.println("updateTNResult="+updateTNResult);
			if((updateNodeNameResult+updatedNodeIdResult+updateTNResult) <1) {
				return 2;
			}
			
			// 카테고리_선택지 관계 수정
			int insertCCResult=NodeDao.insertCategoryChoice(cc);
			System.out.println("ccRelation setting done result="+insertCCResult);
			if(insertCCResult <1) {
				return 3;
			}
			

		}
		return 0;
	}
	
	
//	/* 게시판 목록 */
//	public Map<String, Object> board_list(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//
//		List<BoardBean> boardlist = new ArrayList<BoardBean>();
//
//		int page = 1;
//		int limit = 10; // 한 화면에 출력할 레코드수
//
//		if (request.getParameter("page") != null) {
//			page = Integer.parseInt(request.getParameter("page"));
//		}
//
//		// 총 리스트 수를 받아옴.
//		int listcount = boardDao.getListCount();
//
//		// 페이지 번호(page)를 DAO클래스에게 전달한다.
//		boardlist = boardDao.getBoardList(page); // 리스트를 받아옴.
//
//		// 총 페이지 수.
//		int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95를 더해서 올림
//																	// 처리.
//		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
//		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
//		// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
//		int endpage = maxpage;
//
//		if (endpage > startpage + 10 - 1)
//			endpage = startpage + 10 - 1;
//
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//
//		resultMap.put("page", page);
//		resultMap.put("startpage", startpage);
//		resultMap.put("endpage", endpage);
//		resultMap.put("maxpage", maxpage);
//		resultMap.put("listcount", listcount);
//		resultMap.put("boardlist", boardlist);
//
//		return resultMap;
//	}
//
//	
//	/* 조회수 증가 */
//	public void hit(int board_num) throws Exception {
//		boardDao.boardHit(board_num); // 조회수 증가
//	}
//	
//
//	/* 상세정보 */
//	public BoardBean board_cont(int board_num) throws Exception {
//
//		BoardBean board = boardDao.getBoardCont(board_num);
//
//		return board;
//	}
//
//	
//	/* 게시판 수정 */
//	public int edit(HttpServletResponse response, BoardBean b) throws Exception {
//		PrintWriter out = response.getWriter();
//		response.setContentType("text/html;charset=UTF-8");
//		int result = 0;
//
//		BoardBean board = boardDao.getBoardCont(b.getBoard_num());
//
//		if (!board.getBoard_pass().equals(b.getBoard_pass())) {// 비번이 같다면
//			out.println("<script>");
//			out.println("alert('비번이 다릅니다!')");
//			out.println("history.back()");
//			out.println("</script>");
//			out.close();
//
//			return result;
//
//		} else {
//			// 수정 메서드 호출
//			boardDao.boardEdit(b);
//
//			result = 1;
//		}
//		return result;
//	}
//	
//
//	/* 게시판 삭제 */
//	public int del_ok(HttpServletResponse response, int board_num,
//			String board_pass) throws Exception {
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();// 출력 스트림 객체생성
//
//		BoardBean board = boardDao.getBoardCont(board_num);
//		int result = 0;
//
//		if (!board.getBoard_pass().equals(board_pass)) {
//			out.println("<script>");
//			out.println("alert('비번이 다릅니다!')");
//			out.println("history.go(-1)");
//			out.println("</script>");
//			out.close();
//
//			return result;
//
//		} else {
//			boardDao.boardDelete(board_num);
//
//			result = 1;
//		}
//		return result;
//	}
//	
//
//	/* 게시판 댓글 달기 */
//	public void reply_ok(BoardBean b) throws Exception {
//
//		boardDao.refEdit(b); // 기존 댓글 board_re_seq값 1증가
//
//		b.setBoard_re_lev(b.getBoard_re_lev() + 1); // 부모보다 1증가된 값을 저장함
//		b.setBoard_re_seq(b.getBoard_re_seq() + 1);
//
//		boardDao.boardReplyOk(b);
//	}










}
