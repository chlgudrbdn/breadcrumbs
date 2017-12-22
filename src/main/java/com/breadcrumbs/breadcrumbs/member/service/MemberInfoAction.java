package com.breadcrumbs.breadcrumbs.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;
import com.breadcrumbs.breadcrumbs.dto.BlogMember;

@Service
public class MemberInfoAction {

	@Autowired
	private MemberDao memberDao;

	public List<BlogMember> getMember() {
		return memberDao.getMember();
	}
}
