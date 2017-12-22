package com.breadcrumbs.breadcrumbs.member.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;
import com.breadcrumbs.breadcrumbs.dto.BlogMember;

@Service
public class MemberFindAction {

	@Autowired
	private MemberDao memberDao;

//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}

	public boolean updatePass(HttpServletRequest request) {
		boolean result = false;
		// 이전 페이지에서 입력한 email
		// 파라미터값 가져오기
		String email = request.getParameter("email");
		// 위의 email 데이터가 있는지 데이터베이스에서
		// 조회
		BlogMember member = memberDao.getMember(email);
		// 데이터가 없는 경우에 request에 result에
		// 아무 데이터나 저장
		if (member == null) {
			request.setAttribute("result", "1");
		} else {
			// 메일에 패스워드 보내기
			SimpleEmail simpleEmail = new SimpleEmail();
			simpleEmail.setHostName("smtp.naver.com");
			simpleEmail.setSmtpPort(587);
			simpleEmail.setAuthentication("apxkfchlgudr", "m1273910!@");

			try {
				simpleEmail.setSSL(true);
				simpleEmail.setTLS(true);
				simpleEmail.setCharset("utf-8");

				// 받는 사람 설정
				simpleEmail.addTo(email, "이름", "utf-8");
				// 보내는 사람 설정
				simpleEmail.setFrom("apxkfchlgudr@naver.com", "관리자", "utf-8");
				// 제목 설정
				simpleEmail.setSubject("비번 보내기");
				// 랜덤한 영문자 및 숫자 7개 생성
				Random r = new Random();
				String pw = "";
				for (int i = 0; i < 7; i++) {
					int x = Math.abs(r.nextInt(62));
					if (x >= 0 && x <= 9) {
						x = x + 48;
					} else if (x >= 10 && x < 36) {
						x = x + 55;
					} else {
						x = x + 61;
					}
					pw = pw + (char) x;
				}
				simpleEmail.setMsg("새로운 비번\n" + pw);
				// 메일 전송
				simpleEmail.send();

				// 데이터베이스에 비번 변경 작업
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("email", email);
				map.put("pw", pw);
				result = memberDao.updatePass(map);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
