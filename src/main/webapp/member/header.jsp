<%@ page language="java" contentType="text/html; charset=UTF-8"
 	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-inverse ">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#myNavbar">
	        		<span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>                        
		      	</button>
		      	<a class="navbar-brand" href="./index.jsp">Breadcrumbs</a>
		      </div>
		      	
			   <c:if test="${member != null}">
					<span style="color:white">${member.email}님 환영합니다.</span>
				</c:if>
			
			<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<!-- 로그인 안 된 경우의 메뉴 -->
				<c:if test="${member==null}">
					<li><a href="Login.member" class="box_layer">
						<span class="glyphicon glyphicon-log-in"></span> 로그인 </a></li>
					<li><a href="JoinView.member" class="box_layer">
						<span class="glyphicon glyphicon-user"></span> 회원가입 </a></li>
					<li><a href="NoticeList.board">공지사항보기</a></li>
				</c:if>

				<!-- 일반 사용자로 로그인 한 경우 -->
				<c:if test="${member!=null and member.email != 'admin@breadcrumbs.com' }">
					<li><a href="Logout.member">로그아웃</a></li>
					<li class="dropdown">
						<a id="dLabel" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" href="#">게시판<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
<!-- 							<li><a href="NoticeList.board">공지사항보기</a></li> -->
							<li><a href="BoardList.board">포럼글보기</a></li>
							<li><a href="BoardWrite.board">게시판글쓰기</a></li>
						</ul>
					</li>
					
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">마이페이지<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="UpdateView.member">회원정보수정</a></li>
							<li><a href="DropView.member">회원탈퇴</a></li>
						</ul>
					</li>
					
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">결정트리<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="makeTree.node">트리 만들기 시작</a></li>
							<li><a href="myTree.node">내 트리</a></li>
						</ul>
					</li>
				</c:if>

				<!-- 관리자로 로그인 했을 때 -->
				<c:if test="${member!=null and member.email == 'admin@breadcrumbs.com' }">
					<li><a href="Logout.member">로그아웃</a></li>
					<li class="dropdown"><a href="#">게시판</a>
						<ul>
							<li><a href="BoardList.board">전체 글 보기</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">관리자 기능<span class="caret"></span></a>
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