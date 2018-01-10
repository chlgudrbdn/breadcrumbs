<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="navbar-header">
			<c:if test="${member != null}">
				${member.name}님 환영합니다.
			</c:if>
			<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<!-- 로그인 안 된 경우의 메뉴 -->
				<c:if test="${member==null}">
					<li><a href="Login.member" class="box_layer"> 로그인 </a></li>
					<li><a href="JoinView.member" class="box_layer"> 회원가입 </a></li>
					<li><a href="NoticeList.board">공지사항보기</a></li>
				</c:if>

				<!-- 일반 사용자로 로그인 한 경우 -->
				<c:if test="${member!=null and member.gubun==0}">
					<li><a href="Logout.member">로그아웃</a></li>
					<li><a href="#">게시판</a>
						<ul>
							<li><a href="NoticeList.board">공지사항보기</a></li>
							<li><a href="BoardList.board">전체글보기</a></li>
							<li><a href="BoardWrite.board">게시판글쓰기</a></li>
						</ul></li>
					<li><a href="#">마이페이지</a>
						<ul>
							<li><a href="UpdateView.member">회원정보수정</a></li>
							<li><a href="DropView.member">회원탈퇴</a></li>
						</ul></li>
					<li><a href="#">결정트리</a>
						<ul>
							<li><a href="makeTree.node">트리 만들기 시작</a></li>
							<li><a href="myTree.node">내 트리</a></li>
						</ul></li>
				</c:if>

				<!-- 관리자로 로그인 했을 때 -->
				<c:if test="${member!=null and member.gubun==1 }">
					<li><a href="Logout.member">로그아웃</a></li>
					<li><a href="#">게시판</a>
						<ul>
							<li><a href="BoardList.board">전체 글 보기</a></li>
						</ul></li>
					<li><a href="#">관리자 기능</a>
						<ul>
							<li><a href="NoticeWrite.board">공지사항쓰기</a></li>
							<li><a href="Management.member">회원정보관리</a></li>
						</ul></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>
</body>
</html>