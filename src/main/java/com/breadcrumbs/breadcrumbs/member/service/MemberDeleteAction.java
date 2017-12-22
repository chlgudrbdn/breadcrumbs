package com.breadcrumbs.breadcrumbs.member.service;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;
import com.breadcrumbs.breadcrumbs.dto.BlogMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberDeleteAction {
	
	@Autowired
	private MemberDao memberDao;

	public boolean execute(String email) {
		BlogMember member = new BlogMember();
		member.setEmail(email);
		return memberDao.deleteMember(member);
	}
}
