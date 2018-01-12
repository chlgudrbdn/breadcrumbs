package com.breadcrumbs.breadcrumbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.breadcrumbs.breadcrumbs.dto.UseraccountDto;
import com.breadcrumbs.breadcrumbs.dto.LoginDto;

@Repository
public class MemberDaoImpl implements MemberDao {

	// 마이바티스 사용을 위한 SqlSession 프로퍼티
	@Autowired
	private SqlSession sqlSession;

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public UseraccountDto login(LoginDto loginDto) {
		// 1개를 찾아와서 리턴하는 경우
//		System.out.println("login"+loginDto);
		List<UseraccountDto> list = sqlSession.selectList("member.loginMember", loginDto);
		// 검색된 데이터가 없는 경우 null 리턴
//		System.out.println(list);
		if (list == null || list.size() < 1) {
			return null;
		// 검색된 데이터가 있으면
		// 그 중에서 첫번째 데이터 리턴
		}else {
//			// 업데이트 날짜 수정하는 sql 호출
//			int r = sqlSession
//					.update("member.updateLogin", loginDto.getEmail());
//			if (r > 0)
			return list.get(0);
//			else
//				return null;
		}
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean emailCheck(String email) {
		boolean result = false;	// 사용 불가능한 email(중복 email)
		List<LoginDto> list = sqlSession.selectList("member.idCheck", email);
		// 검색된 데이터가 없다면
		if (list == null || list.size() < 1)
			result = true;		// 사용 가능한 email

		return result;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean insertMember(UseraccountDto member) {
		System.out.println(member);
		// sql 문장 실행
		int r = sqlSession.insert("member.insertMember", member);
		// insert, delete, update 구문은 수행을 하고나면
		// 영향받은 행의 개수를 리턴합니다.
		// 삽입은 0보다 큰 숫자가 리턴되면 삽입을 한 것입니다.
		if (r > 0)
			return true;
		else
			return false;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public UseraccountDto getMember(String email) {
		List<UseraccountDto> list = sqlSession.selectList("member.idCheck", email);
		if (list.size() >= 1)
			return list.get(0);
		return null;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean updateMember(UseraccountDto member) {
		int r = sqlSession.update("member.updateMember", member);
		if (r > 0)
			return true;
		else
			return false;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean dropMember(UseraccountDto member) {
		// 패스워드를 찾아오는 sql을 실행
		List<String> list = sqlSession.selectList("member.pwSelect", member);
		System.out.println("list.get(0)="+list.get(0));
		if (list.size() >= 1) {
			// 패스워드 비교
			if (list.get(0).equals(member.getPass())) {
				int r = sqlSession.delete("member.deleteMember", member);
				if (r > 0)
					return true;
			}
		}
		return false;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean updatePass(Map<String, Object> map) {
		int r = sqlSession.update("member.updatePass", map);
		if (r > 0)
			return true;
		else
			return false;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public List<UseraccountDto> getMember() {
		List<UseraccountDto> list = sqlSession.selectList("member.selectMember");
		if (list.size() > 0)
			return list;
		else
			return null;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean deleteMember(UseraccountDto member) {
		boolean result = false;

		int r = sqlSession.delete("member.deleteMember", member);
		if (r >= 1)
			result = true;

		return result;
	}

	@Override
	@Transactional// 메소드 수행 중에 예외가 발생하면 rollback 그렇지 않으면 commit
	public boolean sleepMember() {
		boolean result = false;
		int r = sqlSession.delete("member.sleepMember");
		if (r >= 0)
			result = true;
		return result;
	}
}
