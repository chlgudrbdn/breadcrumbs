package com.breadcrumbs.breadcrumbs.node.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.NodeDaoImpl;
import com.breadcrumbs.breadcrumbs.dto.NodeDto;


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
	

//	public List<String> getRecommendCategoryList(String value) {
	public List<String> getRecommendCategoryList(String value) {
		return NodeDao.getRecommendCategoryList(value);
	}
	
	public void makeTreeNo(String category, File dataInput) {
		if(dataInput!=null) {
			NodeDao.insertTree();
		}
		NodeDao.insertCategory(category);
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
