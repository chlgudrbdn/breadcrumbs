<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
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
		      	<a class="navbar-brand" href="/breadcrumbs/index.jsp">Breadcrumbs</a>
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
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" id="Borads" onclick="toggle('aboutBorads', 'Borads')" aria-haspopup="true" aria-expanded="false" href="#">게시판<span class="caret"></span></a>
						<ul class="dropdown-menu" id="aboutBorads">
<!-- 							<li><a href="NoticeList.board">공지사항보기</a></li> -->
							<li><a href="BoardList.board">포럼글보기</a></li>
							<li><a href="BoardWrite.board">게시판글쓰기</a></li>
						</ul>
					</li>
					
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" id="Member" onclick="toggle('aboutMember', 'Member')" href="#" role="button" aria-haspopup="true" aria-expanded="false">마이페이지<span class="caret"></span></a>
						<ul class="dropdown-menu" id="aboutMember">
							<li><a href="UpdateView.member">회원정보수정</a></li>
							<li><a href="DropView.member">회원탈퇴</a></li>
						</ul>
					</li>
					
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" id="decisionTree" onclick="toggle('aboutTree', 'decisionTree')" href="#">결정트리<span class="caret"></span></a>
						<ul class="dropdown-menu" id="aboutTree">
							<li><a href="makeTree.node">트리 만들기 시작</a></li>
							<li><a href="myTree.node">내 트리</a></li>
						</ul>
					</li>
				</c:if>

				<!-- 관리자로 로그인 했을 때 -->
				<c:if test="${member!=null and member.email == 'admin@breadcrumbs.com' }">
					<li><a href="Logout.member">로그아웃</a></li>
					<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" id="boradForAdmin" onclick="toggle('boradAdmin', 'boradForAdmin')"  href="#">게시판<span class="caret"></span></a>
						<ul class="dropdown-menu" id="boradAdmin">
							<li><a href="BoardList.board">전체 글 보기</a></li>
						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" id="noticeForAdmin" onclick="toggle('NoticeAndmemberAdmin', 'noticeForAdmin')" aria-expanded="false" href="#">관리자 기능<span class="caret"></span></a>
						<ul class="dropdown-menu" id="NoticeAndmemberAdmin">
							<li><a href="NoticeWrite.board">공지사항쓰기</a></li>
							<li><a href="Management.member">회원정보관리</a></li>
						</ul></li>
				</c:if>
				
			</ul>
		</div>
	</div>
	</nav>

<script type="text/javascript">
  function toggle(id, id2) {
    var n = document.getElementById(id);
	if (n.style.display != 'none') 
	  {
	  n.style.display = 'none';
      document.getElementById(id2).setAttribute('aria-expanded', 'false');
  }
  else
  {
  n.style.display = 'inline';
  document.getElementById(id2).setAttribute('aria-expanded', 'true');
	  }
  }
</script>
</body>
</html>