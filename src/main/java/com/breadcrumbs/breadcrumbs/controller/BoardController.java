package com.breadcrumbs.breadcrumbs.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.breadcrumbs.breadcrumbs.board.service.BoardDeleteAction;
import com.breadcrumbs.breadcrumbs.board.service.BoardDetailAction;
import com.breadcrumbs.breadcrumbs.board.service.BoardInsertAction;
import com.breadcrumbs.breadcrumbs.board.service.BoardListAction;
import com.breadcrumbs.breadcrumbs.board.service.BoardModifyView;
import com.breadcrumbs.breadcrumbs.board.service.BoardUpdateAction;
import com.breadcrumbs.breadcrumbs.board.service.NoticeDetailAction;
import com.breadcrumbs.breadcrumbs.board.service.NoticeListAction;
import com.breadcrumbs.breadcrumbs.board.service.NoticeWriteAction;
import com.breadcrumbs.breadcrumbs.board.service.ReplyInsertAction;
import com.breadcrumbs.breadcrumbs.board.service.ReplyListAction;
import com.breadcrumbs.breadcrumbs.dto.BoardDto;
import com.breadcrumbs.breadcrumbs.dto.FormBoardDto;
import com.breadcrumbs.breadcrumbs.dto.NoticeDto;

@Controller
public class BoardController {
	
	@Autowired
	private NoticeListAction noticeListAction;

	@RequestMapping("/NoticeList.board")
	public ModelAndView noticeList() {
		ModelAndView mav = new ModelAndView();

		List<NoticeDto> list = noticeListAction.execute();
		mav.addObject("result", list);
		mav.setViewName("/board/noticeList");

		return mav;
	}

	
	@Autowired
	private NoticeDetailAction noticeDetailAction;

	@RequestMapping("/NoticeDetail.board")
	public ModelAndView noticeDetail(String num) {
		ModelAndView mav = new ModelAndView();
		NoticeDto dto = noticeDetailAction.execute(num);
		mav.addObject("result", dto);
		mav.setViewName("/board/noticeDetail");
		return mav;
	}

	@RequestMapping("/NoticeWrite.board")
	public ModelAndView noticeWriteView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/noticeWrite");
		return mav;
	}

	
	@Autowired
	private NoticeWriteAction noticeWriteAction;

	@RequestMapping("/NoticeAddAction.board")
	public ModelAndView noticeWriteAction(NoticeDto dto) {
		ModelAndView mav = new ModelAndView();
		boolean r = noticeWriteAction.execute(dto);
		if (r) {
			// 목록보기로 redirect
			mav.setViewName("redirect:NoticeList.board");

		} else {
			// 공지사항 쓰기로 redirect
			mav.setViewName("redirect:NoticeWrite.board");
		}
		return mav;
	}

	
	@Autowired
	private BoardListAction boardListAction;

	@RequestMapping("/BoardList.board")
	public ModelAndView getBoardList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		// 로그인 되어 있지 않으면 로그인 페이지로 이동
		if (session.getAttribute("member") == null)
			mav.setViewName("/member/login");
		// 로그인 되어 있으면
		else {
			// 공지 사항 목록 가져오기
			List<NoticeDto> list = noticeListAction.execute();
			// 게시물 목록 가져오기
			Map<String, Object> map = boardListAction.execute(request);
			// 여러 개의 데이터를 묶어서 저장할 때는
			// addObject를 사용하지 않고 Map을 저장할 수
			// 있습니다.
			mav.addAllObjects(map);
			// 공지사항을 다음페이지에 전달하기 위해서
			// 저장하기
			mav.addObject("result", list);
			mav.setViewName("/board/boardList");
		}
		return mav;
	}

	
	@Autowired
	private BoardDetailAction boardDetailAction;
	@Autowired
	private ReplyListAction replyListAction;

	@RequestMapping("/BoardDetail.board")
	public ModelAndView getBoardDetail(int num, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 로그인 정보를 확인해서 로그인 안되어
		// 있으면 로그인 페이지로 이동
		if (session.getAttribute("member") == null) {
			mav.setViewName("/member/login");
		} else {
			BoardDto dto = boardDetailAction.execute(num);
			List<Map<String, Object>> list = replyListAction.getReplyList(num);
			// 데이터를 저장
			mav.addObject("boarddata", dto);
			mav.addObject("replydata", list);
			// 출력할 뷰 파일 설정
			mav.setViewName("/board/boardDetail");
		}
		return mav;
	}
	

	@Autowired
	private ReplyInsertAction replyInsertAction;

	@RequestMapping("/ReplyAdd.board")
	public ModelAndView insertReply(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 로그인 되어 있지 않으면 로그인 페이지로 이동
		HttpSession session = request.getSession();
		if (session.getAttribute("member") == null) {
			mav.setViewName("/member/login");
		} else {
			// 데이터 삽입
			boolean r = replyInsertAction.execute(request);
			// 삽입에 실패했을 때 목록보기로 이동
			if (!r) {
				List<NoticeDto> list = noticeListAction.execute();
				Map<String, Object> map = boardListAction.execute(request);
				mav.addObject("result", list);
				mav.addAllObjects(map);
				mav.setViewName("/board/boardList");
			} else {
				// 상세보기 수행
				// 글번호가져오기
				int num = Integer.parseInt(request.getParameter("num"));
				// redirect 할 때는 출력할 파일이름을
				// 직접 사용하지 않고 요청 주소를 이용합니다.
				mav.setViewName("redirect:BoardDetail.board?num=" + num);

			}
		}
		return mav;
	}

	
	// BoardWrite.board 요청이 오면
	// board/boardwrite.jsp로 이동하도록
	// 요청을 처리하는 메소드
	// session에 로그인 정보가 없으면
	// member/login.jsp로 이동
	@RequestMapping("/BoardWrite.board")
	public ModelAndView writeView(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("member") == null) {
			mav.setViewName("/member/login");
		} else {
			mav.setViewName("/board/boardwrite");
		}
		return mav;
	}

	
	@Autowired
	private BoardInsertAction boardInsertAction;

	@RequestMapping("/BoardAddAction.board")
	public ModelAndView writeBoard(FormBoardDto dto, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		boolean r = boardInsertAction.execute(dto, request);
		if (r) {
			// 목록보기로 리다이렉트
			mav.setViewName("redirect:BoardList.board");
		} else {
			// 글쓰기 페이지로 포워딩
			mav.setViewName("board/boardwrite");
		}
		return mav;
	}
	

	@Autowired
	private BoardModifyView boardModifyView;

	@RequestMapping("/BoardModify.board")
	public ModelAndView modifyView(@RequestParam("num") int num,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 로그인 되어 있지 않으면 로그인페이지로
		// 이동하도록 설정
		if (session.getAttribute("member") == null) {
			mav.setViewName("/member/login");
		}
		// 로그인 되어 있으면 서비스를 수행
		else {
			BoardDto dto = boardModifyView.execute(num);
			mav.addObject("boarddata", dto);
			mav.setViewName("/board/boardModify");
		}
		return mav;
	}

	
	@Autowired
	private BoardUpdateAction boardUpdateAction;

	@RequestMapping("/BoardModifyAction.board")
	public ModelAndView updateBoard(FormBoardDto formBoardDto,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		// 로그인 한 상태가 아니면 로그인 페이지로 이동
		HttpSession session = request.getSession();
		if (session.getAttribute("member") == null) {
			mav.setViewName("/member/login");
		} else {
			boolean r = boardUpdateAction.execute(formBoardDto, request);
			if (r) {
				// 목록보기로 리다이렉트
				mav.setViewName("redirect:BoardList.board");
			} else {
				// index.jsp로 포워딩
				mav.setViewName("index");
			}
		}

		return mav;
	}

	
	@Autowired
	private BoardDeleteAction boardDeleteAction;

	@RequestMapping("/BoardDelete.board")
	public ModelAndView deleteBoard(@RequestParam("num") int num,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 로그인 되어 있지 않으면 로그인 페이지로 이동
		if (session.getAttribute("member") == null) {
			mav.setViewName("/member/login");
		} else {
			boolean r = boardDeleteAction.execute(num);
			if (r) {
				mav.setViewName("redirect:BoardList.board");
			} else {
				mav.setViewName("index");
			}
		}
		return mav;
	}

	
	@RequestMapping("/download.board")
	public ModelAndView download(String filename, HttpServletRequest request) {
		String realPath = request.getSession().getServletContext()
				.getRealPath("boardupload");
		File downloadFile = new File(realPath + "\\" + filename);
		// download 라는 View에 출력하고
		// downloadFile 이라는 이름으로
		// downloadFile 이라는 데이터를 전송
		return new ModelAndView("download", "downloadFile", downloadFile);
	}
}
