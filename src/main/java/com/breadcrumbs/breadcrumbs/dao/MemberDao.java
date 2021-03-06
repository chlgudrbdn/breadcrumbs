package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import com.breadcrumbs.breadcrumbs.dto.UseraccountDto;
import com.breadcrumbs.breadcrumbs.dto.LoginDto;

//Member 테이블 작업을 위한 Dao 인터페이스
public interface MemberDao {
	// 로그인 처리를 위한 메소드
	public UseraccountDto login(LoginDto loginDto);

	// 이메일 중복 체크를 위한 메소드
	public boolean emailCheck(String email);

	// 회원가입을 처리해주는 메소드
	public boolean insertMember(UseraccountDto member);

	// Email을 가지고 회원정보를 찾아오는 메소드
	public UseraccountDto getMember(String email);

	// 회원정보를 수정하는 메소드
	public boolean updateMember(UseraccountDto member);

	// 회원탈퇴를 처리하는 메소드
	public boolean dropMember(UseraccountDto member);

	// 비밀번호를 변경해주는 메소드
	public boolean updatePass(Map<String, Object> map);

	// 관리자를 제외한 모든 유저 정보를 가져오는 메소드
	public List<UseraccountDto> getMember();

	// 관리자가 하나의 데이터를 삭제하는 메소드
	public boolean deleteMember(UseraccountDto member);

	// 휴먼 계정을 삭제하는 메소드
	public boolean sleepMember();
}
