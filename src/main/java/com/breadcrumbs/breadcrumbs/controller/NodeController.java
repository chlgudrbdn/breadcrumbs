package com.breadcrumbs.breadcrumbs.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.breadcrumbs.breadcrumbs.dto.CategoryDto;
import com.breadcrumbs.breadcrumbs.node.service.NodeAction;


@Controller
@SessionAttributes("useraccount")
public class NodeController {
	
	@Autowired
	private NodeAction nodeAction;
	
	//노드 추가-노드와 연동된 코드 추가와는 다르다.
	@RequestMapping("/NodeAdd.node")
	@ResponseBody //이게 있어야 json으로 반환되는데 이건 숫자만 반환하므로 없어도 됨.
	public void NodeAdd(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
			// 데이터 삽입
		PrintWriter out = response.getWriter();
//		ModelAndView mav = new ModelAndView();
//		boolean result = emailCheckAction.idCheck(email);
//		System.out.println("result="+result);
////		mav.addObject("result", result);
////		mav.setViewName("emailCheck");
////		return mav;
//		int req = 0;
//		if(result == true)	// 사용 가능한 email
//			req = 1;
//		else				// 사용 불가능한 email(중복  email)
//			req = -1;
//		System.out.println("req="+req);
//		out.println(req);//callback으로 리턴되는 부분.
	}

	@RequestMapping("/myTree.node")
	public String myTreeAction() {//사용자가 만든 트리 관리하는 페이지로 가는 경로 지정
		return "tree/myTree";
	}
	
	@RequestMapping("/makeTree.node")
	public String noticeWriteAction() {//트리 새로 만드는 페이지로 가는 경로 지정
		return "tree/makeTree";
	}

	//이게 있어야 json으로 반환	
	@RequestMapping("/checkRecommendCategory.node")
	@ResponseBody
//	public @ResponseBody List checkRecommendCategory(@RequestParam("value") String value,HttpServletResponse response) throws Exception {
	public List<String> checkRecommendCategory(@RequestParam("value") String value,
			HttpServletResponse response) throws Exception {
//		PrintWriter out = response.getWriter();
		List<String> result = this.nodeAction.getRecommendCategoryList(value);
		System.out.println("result="+result);
//		out.println(result);
		return result;
	}
	
	@RequestMapping("/makeRootNode.node")
	public String makeRootNode(@RequestParam("category") String category, @RequestParam("dataInput") File dataInput) {
		this.nodeAction.makeTreeNo(category, dataInput);
		
		//할일 : m_t_relation 테이블에 트리 시퀀스+1, 추천수는 0, 카테고리, 세션에 있는 멤버 객체(아마도 useraccount)이메일 넣는다.
		//       t_n_relation에는 루트 노드로 하나 넣는다. 30자가 넘지 않도로 해야하는데 seq는 27자 까지가 한계. 절충해서 대충 20자 정도만 넣을 수 있게하자.
		//    node에도 해당 root 노드 추가 시킨다. id, parent, state, text, li_attr 등. 선택지인 text는 일단 비워둔다.
		//    비슷하게 파일노드도 하나 넣는다. 이때 node의 li_attr에 type을 file로 정해둘 것.
		//   새로운 카테고리의 경우 추가하는 메소드가 필요하다.
		
		
		//
		return "tree/treeMain";
	}
	
	@RequestMapping("/executeCode.node")
	@ResponseBody
	public String noticeWriteView(@RequestParam("codes") String codes,
			HttpServletResponse response) throws REXPMismatchException, REngineException {
		System.out.println("executeCode");
		String result=this.nodeAction.executeCode(codes);
		return result;
	}
//	
//	@Autowired
//	private NoticeListAction noticeListAction;
//
//	@RequestMapping("/NoticeList.board")
//	public ModelAndView noticeList() {
//		ModelAndView mav = new ModelAndView();
//
//		List<NoticeDto> list = noticeListAction.execute();
//		mav.addObject("result", list);
//		mav.setViewName("/board/noticeList");
//
//		return mav;
//	}
//
//	

//

//
//	
//	@Autowired
//	private NoticeWriteAction noticeWriteAction;
//
//	@RequestMapping("/NoticeAddAction.board")
//	public ModelAndView noticeWriteAction(NoticeDto dto) {
//		ModelAndView mav = new ModelAndView();
//		boolean r = noticeWriteAction.execute(dto);
//		if (r) {
//			// 목록보기로 redirect
//			mav.setViewName("redirect:NoticeList.board");
//
//		} else {
//			// 공지사항 쓰기로 redirect
//			mav.setViewName("redirect:NoticeWrite.board");
//		}
//		return mav;
//	}
//
//	
//	@Autowired
//	private BoardListAction boardListAction;
//
//	@RequestMapping("/BoardList.board")
//	public ModelAndView getBoardList(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		HttpSession session = request.getSession();
//		// 로그인 되어 있지 않으면 로그인 페이지로 이동
//		if (session.getAttribute("member") == null)
//			mav.setViewName("/member/login");
//		// 로그인 되어 있으면
//		else {
//			// 공지 사항 목록 가져오기
//			List<NoticeDto> list = noticeListAction.execute();
//			// 게시물 목록 가져오기
//			Map<String, Object> map = boardListAction.execute(request);
//			// 여러 개의 데이터를 묶어서 저장할 때는
//			// addObject를 사용하지 않고 Map을 저장할 수
//			// 있습니다.
//			mav.addAllObjects(map);
//			// 공지사항을 다음페이지에 전달하기 위해서
//			// 저장하기
//			mav.addObject("result", list);
//			mav.setViewName("/board/boardList");
//		}
//		return mav;
//	}
//
//	
//	@Autowired
//	private BoardDetailAction boardDetailAction;
//	@Autowired
//	private ReplyListAction replyListAction;
//
//	@RequestMapping("/BoardDetail.board")
//	public ModelAndView getBoardDetail(int num, HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		// 로그인 정보를 확인해서 로그인 안되어
//		// 있으면 로그인 페이지로 이동
//		if (session.getAttribute("member") == null) {
//			mav.setViewName("/member/login");
//		} else {
//			BoardDto dto = boardDetailAction.execute(num);
//			List<Map<String, Object>> list = replyListAction.getReplyList(num);
//			// 데이터를 저장
//			mav.addObject("boarddata", dto);
//			mav.addObject("replydata", list);
//			// 출력할 뷰 파일 설정
//			mav.setViewName("/board/boardDetail");
//		}
//		return mav;
//	}
//	
//
//	@Autowired
//	private ReplyInsertAction replyInsertAction;
//
//	@RequestMapping("/ReplyAdd.board")
//	public ModelAndView insertReply(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		// 로그인 되어 있지 않으면 로그인 페이지로 이동
//		HttpSession session = request.getSession();
//		if (session.getAttribute("member") == null) {
//			mav.setViewName("/member/login");
//		} else {
//			// 데이터 삽입
//			boolean r = replyInsertAction.execute(request);
//			// 삽입에 실패했을 때 목록보기로 이동
//			if (!r) {
//				List<NoticeDto> list = noticeListAction.execute();
//				Map<String, Object> map = boardListAction.execute(request);
//				mav.addObject("result", list);
//				mav.addAllObjects(map);
//				mav.setViewName("/board/boardList");
//			} else {
//				// 상세보기 수행
//				// 글번호가져오기
//				int num = Integer.parseInt(request.getParameter("num"));
//				// redirect 할 때는 출력할 파일이름을
//				// 직접 사용하지 않고 요청 주소를 이용합니다.
//				mav.setViewName("redirect:BoardDetail.board?num=" + num);
//
//			}
//		}
//		return mav;
//	}
//
//	
//	// BoardWrite.board 요청이 오면
//	// board/boardwrite.jsp로 이동하도록
//	// 요청을 처리하는 메소드
//	// session에 로그인 정보가 없으면
//	// member/login.jsp로 이동
//	@RequestMapping("/BoardWrite.board")
//	public ModelAndView writeView(HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		if (session.getAttribute("member") == null) {
//			mav.setViewName("/member/login");
//		} else {
//			mav.setViewName("/board/boardwrite");
//		}
//		return mav;
//	}
//
//	
//	@Autowired
//	private BoardInsertAction boardInsertAction;
//
//	@RequestMapping("/BoardAddAction.board")
//	public ModelAndView writeBoard(FormBoardDto dto, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		boolean r = boardInsertAction.execute(dto, request);
//		if (r) {
//			// 목록보기로 리다이렉트
//			mav.setViewName("redirect:BoardList.board");
//		} else {
//			// 글쓰기 페이지로 포워딩
//			mav.setViewName("board/boardwrite");
//		}
//		return mav;
//	}
//	
//
//	@Autowired
//	private BoardModifyView boardModifyView;
//
//	@RequestMapping("/BoardModify.board")
//	public ModelAndView modifyView(@RequestParam("num") int num,
//			HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		// 로그인 되어 있지 않으면 로그인페이지로
//		// 이동하도록 설정
//		if (session.getAttribute("member") == null) {
//			mav.setViewName("/member/login");
//		}
//		// 로그인 되어 있으면 서비스를 수행
//		else {
//			BoardDto dto = boardModifyView.execute(num);
//			mav.addObject("boarddata", dto);
//			mav.setViewName("/board/boardModify");
//		}
//		return mav;
//	}
//
//	
//	@Autowired
//	private BoardUpdateAction boardUpdateAction;
//
//	@RequestMapping("/BoardModifyAction.board")
//	public ModelAndView updateBoard(FormBoardDto formBoardDto,
//			HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//
//		// 로그인 한 상태가 아니면 로그인 페이지로 이동
//		HttpSession session = request.getSession();
//		if (session.getAttribute("member") == null) {
//			mav.setViewName("/member/login");
//		} else {
//			boolean r = boardUpdateAction.execute(formBoardDto, request);
//			if (r) {
//				// 목록보기로 리다이렉트
//				mav.setViewName("redirect:BoardList.board");
//			} else {
//				// index.jsp로 포워딩
//				mav.setViewName("index");
//			}
//		}
//
//		return mav;
//	}
//
//	
//	@Autowired
//	private BoardDeleteAction boardDeleteAction;
//
//	@RequestMapping("/BoardDelete.board")
//	public ModelAndView deleteBoard(@RequestParam("num") int num,
//			HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		// 로그인 되어 있지 않으면 로그인 페이지로 이동
//		if (session.getAttribute("member") == null) {
//			mav.setViewName("/member/login");
//		} else {
//			boolean r = boardDeleteAction.execute(num);
//			if (r) {
//				mav.setViewName("redirect:BoardList.board");
//			} else {
//				mav.setViewName("index");
//			}
//		}
//		return mav;
//	}
//
//	
//	@RequestMapping("/download.board")
//	public ModelAndView download(String filename, HttpServletRequest request) {
//		String realPath = request.getSession().getServletContext()
//				.getRealPath("boardupload");
//		File downloadFile = new File(realPath + "\\" + filename);
//		// download 라는 View에 출력하고
//		// downloadFile 이라는 이름으로
//		// downloadFile 이라는 데이터를 전송
//		return new ModelAndView("download", "downloadFile", downloadFile);
//	}
}
