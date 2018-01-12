<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기 페이지</title>
<style>
#emailSpan {
	color: red;
}
</style>
<c:if test="${result != null }">
	<script>
		window.onload = function() {
			document.getElementById("emailSpan").innerHTML = "없는 이메일 주소입니다.";
		}
	</script>
</c:if>
</head>
<body>
<div>
	<header>
		<c:import url ="./header.jsp" />
	</header>
</div>

	<div class="container col-sm-6 col-md-offset-3">
		<form action="./FindPassword.member" method="get">
			<span id="emailSpan"></span><br />
			<span>비밀번호를 다시 설정하려면 로그인할 때 사용하는 이메일 주소를 입력하세요.</span>
			<br>
			 이메일 주소
			 <br> 
				<input type="text"	name="email" size="50" required="required" /><br /> 
				<input type="submit" class="btn btn-default" value="비번전송" />
				<input type="button" class="btn btn-default" value="메인으로"
				onclick="javascript:location.href='./index.jsp'" />
		</form>
	</div>
</body>
</html>
