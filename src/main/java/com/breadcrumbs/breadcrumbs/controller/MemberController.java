package com.breadcrumbs.breadcrumbs.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.breadcrumbs.breadcrumbs.dto.BlogMember;
import com.breadcrumbs.breadcrumbs.dto.LoginDto;
import com.breadcrumbs.breadcrumbs.member.service.EmailCheckAction;
import com.breadcrumbs.breadcrumbs.member.service.GetMemberAction;
import com.breadcrumbs.breadcrumbs.member.service.LoginAction;
import com.breadcrumbs.breadcrumbs.member.service.MemberDeleteAction;
import com.breadcrumbs.breadcrumbs.member.service.MemberDropAction;
import com.breadcrumbs.breadcrumbs.member.service.MemberFindAction;
import com.breadcrumbs.breadcrumbs.member.service.MemberInfoAction;
import com.breadcrumbs.breadcrumbs.member.service.MemberJoinAction;
import com.breadcrumbs.breadcrumbs.member.service.MemberUpdateAction;
import com.breadcrumbs.breadcrumbs.member.service.SleepMemberAction;

@Controller
public class MemberController {
	
	@RequestMapping("/Login.member")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		// 이동할 페이지 설정
		mav.setViewName("member/login");
		return mav;
	}

	@RequestMapping("/MainView.member")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	
	@Autowired
	private LoginAction loginAction;

	@RequestMapping("/LoginAction.member")
	public ModelAndView loginAction(LoginDto loginDto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 로그인 서비스 객체를 작업을 수행
		BlogMember member = loginAction.login(loginDto);
		// 로그인에 실패했을 때
		if (member == null) {
			mav.addObject("login", "fail");
			mav.setViewName("/member/login");
		}
		// 로그인에 성공한 경우
		else {
			session.setAttribute("member", member);
			mav.setViewName("index");
		}

		return mav;
	}

	// Logout.member 요청 처리
	@RequestMapping("/Logout.member")
	public ModelAndView logout(HttpSession session) {
		// 세션의 모든 내용 클리어
		session.invalidate();

		ModelAndView mav = new ModelAndView();
		// index.jsp로 리다이렉트
		mav.setViewName("redirect:index.jsp");

		return mav;
	}

	@RequestMapping("/JoinView.member")
	public ModelAndView join() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/member/join");

		return mav;
	}

	
	@Autowired
	private EmailCheckAction emailCheckAction;

	@RequestMapping("/EmailCheck.member")
	@ResponseBody //이게 있어야 json으로 반환되는데 이건 숫자만 반환하므로 없어도 됨.
	public void check(@RequestParam("email") String email,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		ModelAndView mav = new ModelAndView();
		boolean result = emailCheckAction.idCheck(email);
		System.out.println("result="+result);
//		mav.addObject("result", result);
//		mav.setViewName("emailCheck");
//		return mav;
		int req = 0;
		if(result == true)	// 사용 가능한 email
			req = 1;
		else				// 사용 불가능한 email(중복  email)
			req = -1;
		System.out.println("req="+req);
		out.println(req);//callback으로 리턴되는 부분.
	}

	
	@Autowired
	private MemberJoinAction memberJoinAction;

	@RequestMapping("/JoinProcess.member")
	public ModelAndView insertMember(MultipartHttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		boolean result = memberJoinAction.insertMember(request);
		if (result) {
			mav.setViewName("redirect:Login.member");
		} else {
			mav.addObject("result", result);
			mav.setViewName("/member/join");
		}
		return mav;
	}

	
	@Autowired
	private GetMemberAction getMemberAction;

	@RequestMapping("/UpdateView.member")
	public ModelAndView updateView(HttpSession session) {

		ModelAndView mav = new ModelAndView();
		// 로그인 된 사용자의 정보를 세션에서 가져오기
		BlogMember member = (BlogMember) session.getAttribute("member");
		// 로그인 정보가 없으면 로그인 페이지로 이동
		if (member == null) {
			mav.setViewName("/member/login");
		} else {
			member = getMemberAction.getMember(member);
			// 데이터를 request에 저장
			mav.addObject("member", member);
			mav.setViewName("/member/update");
		}
		return mav;
	}

	
	@Autowired
	private MemberUpdateAction memberUpdateAction;

	@RequestMapping("/UpdateProcess.member")
	public ModelAndView update(MultipartHttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();

		// 로그인을 확인해서 로그인이 안되어있으면
		// 로그인 페이지로 이동하도록 설정
		BlogMember member = (BlogMember) session.getAttribute("member");
		if (member == null) {
			mav.setViewName("/member/login");
		} else {
			// 이미 로그인 된 상태라면
			if (memberUpdateAction.updateMember(request)) {
				session.invalidate();
				mav.setViewName("redirect:Login.member");
			} else {
				mav.addObject("result", false);
				mav.setViewName("/member/update");
			}
		}

		return mav;
	}

	
	@RequestMapping("/DropView.member")
	public ModelAndView dropView(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 로그인 되어 있는지 확인해서
		// 로그인이 안되어있으면 로그인페이지로
		// 로그인이 되어 있으면
		// 비밀번호 입력페이지로 이동
		if (session.getAttribute("member") == null) {
			mav.setViewName("/member/login");
		} else {
			mav.setViewName("/member/drop");
		}

		return mav;
	}

	
	@Autowired
	private MemberDropAction memberDropAction;

	@RequestMapping("/DropProcess.member")
	public ModelAndView dropMember(BlogMember member, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session.getAttribute("member") == null) {
			mav.setViewName("/member/login");
		} else {
			BlogMember sMember = (BlogMember) session.getAttribute("member");
			member.setEmail(sMember.getEmail());
			boolean result = memberDropAction.dropMember(member);
			if (result) {
				session.invalidate();
				mav.setViewName("redirect:MainView.member");
			} else {
				mav.addObject("result", result);
				mav.setViewName("/member/drop");
			}
		}
		return mav;
	}

	@RequestMapping("/PassView.member")
	public ModelAndView passView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/find");
		return mav;
	}
	

	@Autowired
	private MemberFindAction memberFindAction;

	@RequestMapping("/FindPassword.member")
	public ModelAndView find(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		boolean result = memberFindAction.updatePass(request);
		if (result) {
			mav.setViewName("/member/pass");
		} else {
			mav.setViewName("/member/find");
		}
		return mav;
	}

	
	@Autowired
	private MemberInfoAction memberInfoAction;

	@RequestMapping("/Management.member")
	public ModelAndView getMember() {
		ModelAndView mav = new ModelAndView();
		List<BlogMember> list = memberInfoAction.getMember();
		mav.addObject("result", list);
		mav.setViewName("/member/memberInfo");
		return mav;
	}

	
	@Autowired
	private MemberDeleteAction memberDeleteAction;

	@RequestMapping("/DeleteMember.member")
	public ModelAndView deleteMember(String email) {
		ModelAndView mav = new ModelAndView();
		boolean result = memberDeleteAction.execute(email);
		if (result) {
			mav.setViewName("redirect:Management.member");
		} else {
			mav.setViewName("/index");
		}
		return mav;
	}
	

	@Autowired
	private SleepMemberAction sleepMemberAction;

	@RequestMapping("/SleepMember.member")
	public ModelAndView sleepMember() {
		ModelAndView mav = new ModelAndView();
		boolean r = sleepMemberAction.execute();
		if (r) {
			mav.setViewName("redirect:Management.member");
		} else {
			mav.setViewName("index");
		}
		return mav;
	}
}
