<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>

<!-- 스타일 시트 -->
<style>
span {
	color: red;
}
</style>

<!-- 로그인에 실패해서 온 경우 메시지를 출력 -->
<c:if test="${login != null}">
	<script>
		window.onload=function(){
			var loginSpan = 
				document.getElementById(
					"loginSpan");
			loginSpan.innerHTML = 
				"없는 아이디 이거나 비밀번호가" +
				 "틀렸습니다.";
		}
	</script>
</c:if>

	<script>
	//email 과 pw라는 id를 가진 input에 내용이 없으면
	//false를 리턴하고 그렇지 않으면 true를 리턴
	//false를 리턴할 때는 loginSpan이라는 id를 가진
	//span 태그에 메시지를 출력
		function check(){
			var email = 
		document.getElementById("email").value;
			var pw = 
		document.getElementById("pw").value;
			var loginSpan = 
		document.getElementById("loginSpan");
			if(email.length == 0){
				loginSpan.innerHTML = 
					"아이디는 필수 입력입니다."
					return false;
			}
			if(pw.length == 0){
				loginSpan.innerHTML = 
					"비밀번호는 필수 입력입니다."
					return false;
			}
			return true;
			
		}
	</script>
</head>

<body>
	<div align="center">
	<!-- submit을 누르면 check라는 함수를 호출하고
	이 함수에서 true를 리턴하면 LoginAction.member로
	name을 가진 객체들의 값을 가지고 이동하고
	false를 리턴하면 현재 페이지를 출력
	전송 방법은 post -->
		<form action="./LoginAction.member" 
		method="post"
		onsubmit="return check()">
			<br /> <br />
			<span id="loginSpan"></span>
			<table cellpadding=0 cellspacing=0 border=0>
				<tr>
					<td width=73>아이디</td>
					<td width=9>:</td>
					<td width=103><input type="text" name="email" id="email"
						size=30 maxlength=50></td>
					<td width=66><input type="submit" value="로그인"></td>
				</tr>
				<tr>
					<td height=4 colspan=3></td>
				</tr>
<tr>
					<td width=73>비밀번호</td>
					<td width=9>:</td>
					<td width=103><input type="password" name="pw" id="pw" size=30
						maxlength=12></td>
					<td width=66><input type="reset" value="취   소">
				</tr>
				<tr>
					<td height=35 colspan=6 align="center"><input type="button"
						value="회원가입"
						onclick="javascript:window.location='./JoinView.member'">
						<input type="button" value="메인으로"
						onclick="javascript:window.location='./MainView.member'">
						<input type="button" value="비번찾기"
						onclick="javascript:window.location='./PassView.member'">
						</td>
				</tr>
			</table>
				
			<table width="500" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2" align="center"><font size=2 color="565656">
							아이디가 없을 경우 '회원가입'을 클릭하십시오. </font></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

				