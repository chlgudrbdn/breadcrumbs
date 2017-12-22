package com.breadcrumbs.breadcrumbs.member.service;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;
import com.breadcrumbs.breadcrumbs.dto.BlogMember;
import com.breadcrumbs.breadcrumbs.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAction {
	
	@Autowired
	// 사용할 Dao 클래스를 프로퍼티로 생성
	private MemberDao memberDao;

	public BlogMember login(LoginDto loginDto) {
		return memberDao.login(loginDto);
	}
}
