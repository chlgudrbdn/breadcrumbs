package com.breadcrumbs.breadcrumbs.member.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;
import com.breadcrumbs.breadcrumbs.dto.BlogMember;

@Service
public class MemberJoinAction {

	@Autowired
	private MemberDao memberDao;

//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}

	// 요청을 처리하는 메소드
	// 파라미터를 직접 처리할 때는
	// HttpServletRequest 나
	// MultipartHttpServletRequest를 매개변수로 받아서
	// 처리합니다.
	public boolean insertMember(MultipartHttpServletRequest request) {
		boolean result = false;
		// Dao의 파라미터를 만들어서 Dao의 메소드를
		// 호출하고 결과를 가공해서 Controller에게 넘겨줍니다.
		BlogMember member = new BlogMember();
		// 파일 업로드 처리
		// 업로드 할 폴더이름 설정
		String uploadPath = request.getRealPath("image");
		System.out.println("uploadPath="+uploadPath);
		
		// 매개변수로 넘어온 파일 가져오기
		MultipartFile report = request.getFile("imgInp");
		String filename = report.getOriginalFilename();
		// 이메일과 파일 이름을 합성
		filename = request.getParameter("email") + filename;
		// 파일 저장 경로 생성
		String filepath = uploadPath + "\\" + filename;

		File f = new File(filepath);
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

		member.setEmail(request.getParameter("email"));
		member.setName(request.getParameter("name"));
		member.setPw(request.getParameter("pw"));
		member.setImage(filename);
		member.setMobile(request.getParameter("mobile"));
		member.setAddress1(request.getParameter("address1"));
		member.setAddress2(request.getParameter("address2"));

		if (memberDao.insertMember(member))
			result = true;

		return result;
	}
}
