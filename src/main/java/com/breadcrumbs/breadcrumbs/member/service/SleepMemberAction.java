package com.breadcrumbs.breadcrumbs.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.breadcrumbs.breadcrumbs.dao.MemberDao;

@Service
public class SleepMemberAction {

	@Autowired
	private MemberDao memberDao;

	public boolean execute() {
		return memberDao.sleepMember();
	}
}
