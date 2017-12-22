package com.breadcrumbs.breadcrumbs.member.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.breadcrumbs.breadcrumbs.dao.MemberDao;

@Service
public class EmailCheckAction {
	
	@Autowired
	private MemberDao memberDao;

	// 이메일 중복 검사를 위한 메소드
	public boolean idCheck(String email) {
		return memberDao.emailCheck(email);
	}

}
