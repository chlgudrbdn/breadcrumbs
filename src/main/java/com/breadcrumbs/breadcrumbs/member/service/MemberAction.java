package com.breadcrumbs.breadcrumbs.member.service;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;
import com.breadcrumbs.breadcrumbs.dto.UseraccountDto;
import com.breadcrumbs.breadcrumbs.dto.LoginDto;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class MemberAction {
	
	@Autowired
	// 사용할 Dao 클래스를 프로퍼티로 생성
	private MemberDao memberDao;

	public UseraccountDto login(LoginDto loginDto) {
		return memberDao.login(loginDto);
	}
	
	//sleepMember
	public boolean execute() {
		return memberDao.sleepMember();
	}
	
	
	public boolean idCheck(String email) {
		return memberDao.emailCheck(email);
	}
	
	
	public boolean updateMember(MultipartHttpServletRequest request) {
		boolean result = false;

		// 파라미터를 저장할 객체 생성
		UseraccountDto member = new UseraccountDto();

		// 이미지 파일을 업로드할 폴더 가져오기
//		String uploadPath = request.getRealPath("image");
		// 업로드할 파일에 대한 정보 가져오기
//		MultipartFile report = request.getFile("imgInp");
		// 업로드할 파일 이름 가져오기
//		String filename = report.getOriginalFilename();
		// 파일을 선택하지 않으면
		// 이전 파일 이름을 filename에 저장
//		if (filename.length() == 0) {
//			filename = request.getParameter("oldimage");
//		} else {
//			filename = request.getParameter("email") + filename;
//		}

		// 업로드할 파일 경로 생성
//		String filepath = uploadPath + "\\" + filename;
		// 파일 객체 생성
//		File f = new File(filepath);
//		FileOutputStream fos = null;
//		BufferedOutputStream bos = null;
//		try {
//			fos = new FileOutputStream(f);
//			bos = new BufferedOutputStream(fos);
//			// 업로드한 파일을 fos에 전송 - 저장
//			bos.write(report.getBytes());
//			bos.flush();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				fos.close();
//			} catch (Exception e) {
//			}
//		}

		member.setEmail(request.getParameter("email"));
		member.setPass(request.getParameter("pass"));
		member.setPayment_type(request.getParameter("payment_type"));
		System.out.println("ready to input the member"+member);
		result = memberDao.updateMember(member);
		return result;
	}
	
	public List<UseraccountDto> getMember() {
		return memberDao.getMember();
	}
	
	// 요청을 처리하는 메소드
		// 파라미터를 직접 처리할 때는
		// HttpServletRequest 나
		// MultipartHttpServletRequest를 매개변수로 받아서
		// 처리합니다.
		public boolean insertMember(MultipartHttpServletRequest request) {
			boolean result = false;
			// Dao의 파라미터를 만들어서 Dao의 메소드를
			// 호출하고 결과를 가공해서 Controller에게 넘겨줍니다.
			UseraccountDto member = new UseraccountDto();
			// 파일 업로드 처리
			// 업로드 할 폴더이름 설정
//			String uploadPath = request.getRealPath("image");
//			System.out.println("uploadPath="+uploadPath);
			
			// 매개변수로 넘어온 파일 가져오기
//			MultipartFile report = request.getFile("imgInp");
//			String filename = report.getOriginalFilename();
			// 이메일과 파일 이름을 합성
//			filename = request.getParameter("email") + filename;
			// 파일 저장 경로 생성
//			String filepath = uploadPath + "\\" + filename;

//			File f = new File(filepath);
//			FileOutputStream fos = null;
//			try {
////				fos = new FileOutputStream(f);
//				fos.write(report.getBytes());
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			} finally {
//				try {
//					fos.close();
//				} catch (Exception e) {
//				}
//			}

			member.setEmail(request.getParameter("email"));
			member.setPass(request.getParameter("pass"));
			System.out.println("member="+member);
			if (memberDao.insertMember(member))
				result = true;

			return result;
		}
		
		public boolean updatePass(HttpServletRequest request) {
			boolean result = false;
			// 이전 페이지에서 입력한 email
			// 파라미터값 가져오기
			String email = request.getParameter("email");
			// 위의 email 데이터가 있는지 데이터베이스에서
			// 조회
			UseraccountDto member = memberDao.getMember(email);
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
					map.put("pass", pw);
					result = memberDao.updatePass(map);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return result;
		}
		//GetMemberAction
		public UseraccountDto getMember(UseraccountDto member) {
			return memberDao.getMember(member.getEmail());
		}
		
		//MemberDeleteAction
		public boolean execute(String email) {
			UseraccountDto member = new UseraccountDto();
			member.setEmail(email);
			return memberDao.deleteMember(member);
		}
		
		//MemberDropAction
		public boolean dropMember(UseraccountDto member){
			return memberDao.dropMember(member);
		}
}
