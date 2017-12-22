package com.breadcrumbs.breadcrumbs.member.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;
import com.breadcrumbs.breadcrumbs.dto.BlogMember;

@Service
public class MemberUpdateAction {

	@Autowired
	private MemberDao memberDao;

	public boolean updateMember(MultipartHttpServletRequest request) {
		boolean result = false;

		// 파라미터를 저장할 객체 생성
		BlogMember member = new BlogMember();

		// 이미지 파일을 업로드할 폴더 가져오기
		String uploadPath = request.getRealPath("image");
		// 업로드할 파일에 대한 정보 가져오기
		MultipartFile report = request.getFile("imgInp");
		// 업로드할 파일 이름 가져오기
		String filename = report.getOriginalFilename();
		// 파일을 선택하지 않으면
		// 이전 파일 이름을 filename에 저장
		if (filename.length() == 0) {
			filename = request.getParameter("oldimage");
		} else {
			filename = request.getParameter("email") + filename;
		}

		// 업로드할 파일 경로 생성
		String filepath = uploadPath + "\\" + filename;
		// 파일 객체 생성
		File f = new File(filepath);
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			// 업로드한 파일을 fos에 전송 - 저장
			bos.write(report.getBytes());
			bos.flush();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}

		member.setName(request.getParameter("name"));
		member.setPw(request.getParameter("pw"));
		member.setEmail(request.getParameter("email"));
		member.setImage(filename);
		member.setMobile(request.getParameter("mobile"));
		member.setAddress1(request.getParameter("address1"));
		member.setAddress2(request.getParameter("address2"));

		result = memberDao.updateMember(member);
		return result;
	}

}
