package com.breadcrumbs.breadcrumbs.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.breadcrumbs.breadcrumbs.dao.GDao;
import com.breadcrumbs.breadcrumbs.dto.ComDto;
import com.breadcrumbs.breadcrumbs.dto.GDto;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

@Controller
// @RestController
public class GAction {

	@Autowired
	private GDao gService;

	// @Autowired 애노테이션을 사용하면 setter 메소드 없이 bean의 id값을 주입이 가능함.
	// public void setgService(GDao gService) {
	// this.gService = gService;
	// }
	
	/* 방명록 글쓰기 */
	@RequestMapping(value = "/g_write.do")
	public String g_write() {
		return "fb/g_write";
	}

	
	
	
	/* 회원 가입 저장 */
	@RequestMapping(value = "/g_write_ok.do", method = RequestMethod.POST)
	public String g_write_ok(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ComDto m = new ComDto();

		String uploadPath = request.getRealPath("./upload");
		System.out.println(uploadPath);
		int size = 10 * 1024 * 1024; // 10MB까지 업로드 가능

		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
				new DefaultFileRenamePolicy());

		String g_name = multi.getParameter("g_name").trim();// 회원아이디
		String g_title = multi.getParameter("g_title").trim();// 회원비번
		String g_pwd = multi.getParameter("g_pwd").trim();// 회원이름
		String g_cont = multi.getParameter("g_cont").trim();// 회원이름
		

		// 첨부 파일 받는 부분
		m.setG_image(multi.getFilesystemName((String) multi.getFileNames().nextElement()));

		m.setG_name(g_name);
		m.setG_title(g_title);
		m.setG_pwd(g_pwd);
		m.setG_cont(g_cont);
		
		this.gService.insertG(m);

		// 로그인 페이지로 이동
		// response.sendRedirect("member_login.nhn");

		return "redirect:/g_list.do";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 방명록 글저장 
	@RequestMapping("/g_write_ok.do")
	public String g_write_ok(@ModelAttribute ComBean c) {
		// 네임피라미터 이름과 빈클래스의 변수명이 같으면 스프링 @ModelAttribute
		// 어노테이션으로 쉽게 값을 가져와 setter()메서드를 호출해서 저장
		System.out.println("들어옴1");

		this.gService.insertG(c);
		return "redirect:/g_list.do";
	}
		*/

	// 방명록 목록
	@RequestMapping(value = "/g_list.do", method = RequestMethod.GET)
	// @ResponseBody
	public ModelAndView g_list() {

		List<GDto> list = this.gService.getGList();
		// 방명록 목록을 받아옴.

		ModelAndView gm = new ModelAndView();
		gm.addObject("glist", list);
		gm.setViewName("fb/g_list");
		return gm;

		// return list;
	}

	/* 방명록 내용+댓글 입력폼+댓글 목록 */
	@RequestMapping(value = "/g_cont.do")
	public ModelAndView g_cont(@RequestParam("g_no") int g_no, @RequestParam("state") String state) {

		int com_count = 0;// 댓글 개수를 저장하기위한 변수

		if (state.equals("cont")) {
			/*
			 * 6번째 과제물 1.조회수가 증가되는 updateHit(g_no); 메서드를 작성하세요! 첨부파일을
			 * GAction.java,GDao.java,GDaoImpl.java, guest.xml 입니다.
			 */
			this.gService.updatehit(g_no);// 조회수증가

			com_count = this.gService.getComCount(g_no);
			// 댓글 개수를 저장
		}
		GDto g = this.gService.getCont(g_no);
		// 번호를 기준으로 디비로 부터 내용을 가져온다.
		String g_cont = g.getG_cont().replace("\n", "<br/>");
		// textarea박스에 엔터키 친부분을 다음줄로 개행

		List<ComDto> clist = this.gService.getComList(g_no);
		// 해당 방명록 번호에 해당하는 댓글 목록을 가져온다.

		ModelAndView cm = new ModelAndView();
		cm.addObject("g", g);
		cm.addObject("g_cont", g_cont);
		cm.addObject("com_count", com_count);// 댓글 개수를 저장

		cm.addObject("clist", clist);// 댓글목록을 저장

		if (state.equals("cont")) {// 내용보기일때만
			cm.setViewName("fb/g_cont");
		} else if (state.equals("edit")) {// 수정폼
			cm.setViewName("fb/g_edit");
		} else if (state.equals("del")) {// 삭제폼
			cm.setViewName("fb/g_del");
		}
		return cm;
	}

	/* 방명록수정 */
	@RequestMapping(value = "/g_edit_ok")
	public String g_edit_ok(@ModelAttribute ComDto ec, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		GDto db_pwd = this.gService.getCont(ec.getG_no());
		// 디비에 저장된 비번을 가져옴.

		if (!db_pwd.getG_pwd().equals(ec.getG_pwd())) {
			// 비번이 같지 않을때
			out.println("<script>");
			out.println("alert('비번이 다릅니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			this.gService.updateG(ec);// 수정
			return "redirect:/g_cont.do?g_no=" + ec.getG_no() + "&state=cont";
		}
		return null;
	}

	/* 방명록 삭제 */
	@RequestMapping(value = "/g_del_ok")
	public String g_del_ok(@RequestParam("g_no") int g_no, @RequestParam("del_pwd") String del_pwd,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		GDto db_pwd = this.gService.getCont(g_no);
		if (!db_pwd.getG_pwd().equals(del_pwd)) {
			out.println("<script>");
			out.println("alert('비번이 다릅니다!')");
			out.println("history.go(-1)");
			out.println("</script>");
		} else {
			List<ComDto> clist = this.gService.getComList(g_no);
			if (clist.size() > 0) {// size()메서드는 컬렉션 요소개수를 반환
				out.println("<script>");
				out.println("alert('댓글목록이 있어서 삭제 할수 없습니다!')");
				out.println("location='g_cont.do?g_no=" + g_no + "&state=cont'");
				out.println("</script>");
			} else {
				this.gService.delG(g_no);
				return "redirect:/g_list.do";
			}
		}
		return null;
	}

	/* 댓글 저장 */
	// @RequestMapping(value = "/comment_ok.do")
	// public String com_ok(@ModelAttribute ComBean c, HttpServletResponse
	// response)
	// throws Exception {
	// response.setContentType("text/html;charset=UTF-8");
	// PrintWriter out = response.getWriter();
	//
	// if (c.getComment_cont().length() > 200) {
	// out.println("<script>");
	// out.println("alert('댓글을 200자 초과를 못합니다!')");
	// out.println("history.go(-1)");
	// out.println("</script>");
	// } else {
	// this.gService.insertCom(c);// 댓글 저장
	//
	// return "redirect:/g_cont.do?g_no=" + c.getG_no() + "&state=cont";
	// // get 방식으로 *.do?g_no=번호값&state=cont 2개의 피라미터
	// // 값을 넘김
	// }
	// return null;
	// }

	/* 댓글 삭제 */
	@RequestMapping(value = "/comment_del_ok.do")
	public String com_del_ok(@RequestParam("comment_no") int comment_no, @RequestParam("g_no") int g_no,
			@RequestParam("state") String state) {

		this.gService.com_del(comment_no);// 댓글 삭제
		return "redirect:/g_cont.do?g_no=" + g_no + "&state=" + state;
	}

	/* 댓글 갯수 구하기 (getJSON) */
	@RequestMapping(value = "/reply_count.do", method = RequestMethod.GET)
	// @ResponseBody
	public void reply_count(@RequestParam("g_no") int g_no) {
		// public int reply_count(@PathVariable("g_no") int g_no) {
		System.out.println("");
		System.out.println("댓글 갯수 들어옴");
		System.out.println("g_no값: " + g_no);
		System.out.println("댓글 수:" + gService.getComCount(g_no));

		// System.out.println(gService.getComCount(g_no));
		// System.out.println("");
		int cnt = 0;
		cnt = gService.getComCount(g_no);
		System.out.println(cnt);
		// return cnt;
		// return gService.getComCount(g_no);
	}

	
	/* 댓글 목록(ajax로 처리) */
	@RequestMapping(value = "/reply.do", method = RequestMethod.POST)
	@ResponseBody
	// public List<ComBean> g_reply(@RequestBody ComBean c, HttpServletResponse
	// response) throws Exception {
	public Map g_reply(@ModelAttribute ComDto c, HttpServletResponse response) throws Exception {
		System.out.println("");
		System.out.println("댓글 들어옴");
		// response.setContentType("text/html;charset=UTF-8");
		List clist = null;

		this.gService.insertCom(c);// 댓글 저장

		int c_max = gService.getComMax(c.getG_no());

		// 부모글
		// GBean g = this.gService.getCont(c.getG_no());

		// 댓글 갯수 구하기
		int com_count = gService.getComCount(c.getG_no());
		// clist = this.gService.getComList(c.getG_no());
		// System.out.println("댓글 목록="+clist);

		Map m = new HashMap();
		m.put("g_no", c.getG_no());
		m.put("c_max", c_max);

		// 입력된 댓글 1개만 구함.
		ComDto cb = gService.getComBean(m);
		System.out.println("cb=" + cb);

		Map mm = new HashMap();
		mm.put("comment_name", cb.getComment_name());
		mm.put("comment_date", cb.getComment_date());
		mm.put("comment_cont", cb.getComment_cont());
		mm.put("com_count", com_count);

		return mm;
	}
	

	/* 상세정보 (ajax연습) */
	@RequestMapping(value = "/cont.do")
	@ResponseBody
	public GDto cont(@RequestParam("g_no") int g_no) {
		System.out.println("들어옴1");

		System.out.println("상세:" + gService.getCont(g_no));
		return gService.getCont(g_no);
	}

}
