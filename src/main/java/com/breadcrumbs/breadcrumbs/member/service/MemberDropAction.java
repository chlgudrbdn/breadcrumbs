package com.breadcrumbs.breadcrumbs.member.service;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;
import com.breadcrumbs.breadcrumbs.dto.BlogMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberDropAction {
	
	@Autowired
	private MemberDao memberDao;
	
	public boolean dropMember(BlogMember member){
		return memberDao.dropMember(member);
	}
}
