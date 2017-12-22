package com.breadcrumbs.breadcrumbs.member.service;

import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;
import com.breadcrumbs.breadcrumbs.dto.BlogMember;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GetMemberAction {
	
	@Autowired
	private MemberDao memberDao;

	public BlogMember getMember(BlogMember member) {
		return memberDao.getMember(member.getEmail());
	}
}
