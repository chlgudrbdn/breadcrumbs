<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>


<!-- jQuery 사용을 위한 라이브러리 설정 -->
<script src="../js/jquery.js"></script>
<script>
	//body의 내용을 읽자 마자 호출되는 jquery 이벤트
	//자바스크립트의 window.onload와 유사하지만 
	//먼저 호출됩니다.
	$(function(){
		//imgInp를 눌러서 파일을 변경하면 함수 호출
	});
	
	//mobile에 숫자와 방향키만 사용할 수 있도록
	//해주는 함수
// 	function onlyNum(e){
// 		//이벤트 발생 객체 가져오기
// 		var event = e || window.event;
// 		//누른 키보드 값 가져오기
// 		var keycode = event.keyCode || e.which;
// 		if(keycode == 8 || 
// 			keycode >= 35 && keycode <= 40 ||
// 			keycode >= 46 && keycode <= 57 || 
// 			keycode >= 96 && keycode <= 105){
// 			return true;
// 		}
// 		else{
// 			return false;
// 		}
// 	}
	
	function confirmId(){	
		
		if($("#email").val() == ""){
			alert("email주소를 입력 하세요");
			$("#email").focus();
			return false;
		}
		
//		$("#emailSpan").hide();
//		alert("들어옴");
		//ajax 요청
		//ajax: 페이지 이동이 아니고 페이지의 내용을
		//가져오는 것
		$.post("/breadcrumbs/EmailCheck.member", 
				{"email":joinform.email.value},
				function(req){
//					alert(req);
//					var sp = 
//						document.getElementById("emailSpan");
//					$("#emailSpan").hide();
					//중복되지 않는 경우
					if(req == 1){// 사용 가능한 email	
						$("#emailSpan").text("");
						$("#emailSpan").text("사용 가능한 이메일")
//						sp.innerHTML = "사용 가능한 이메일";
						joinform.idCheck.value="true";
					}
					//있는 경우     // 사용 불가능한 email(중복 email)
					else{
						$("#emailSpan").text("");
						$("#emailSpan").text("사용 불가능한 이메일")
//						sp.innerHTML = "사용 불가능한 이메일";
						joinform.idCheck.value="false";
					}
		})
	}
	
	//유효성 검사를 위한 함수
	function check(){
		//이메일 관련 유효성 검사
		var email = joinform.email.value;
		var emailSp = 
			document.getElementById("emailSpan");
		//이메일에 값이 없으면 span에 메시지 출력하고
		//전송되지 않도록 설정
		if(email.length == 0){
			emailSp.innerHTML = 
				"이메일은 필수 입력입니다.";
			return false;
		}
		//정규식을 생성 - 이메일 패턴
		//\w는 단어
		var regemail = 
			/\w+@\w+\.\w+/;
		if(!regemail.test(email)){
			emailSp.innerHTML = 
				"이메일 형식이 아닙니다.";
			return false;
		}
		
		//이름은 영문자 또는 한글 2자 이상
		var regname = /([a-z,A-Z,가-힣]){2,}/g;
		var irum = joinform.name.value;
		var nameSp = 
			document.getElementById("nameSpan");
		if(!regname.test(irum)){
			nameSp.innerHTML = 
				"이름은 영문자와 한글로 2자 이상";
			return false;
		}
		
		var idCheck = joinform.idCheck.value;
		if(idCheck == "false"){
			alert("이메일 중복 체크를 해야 합니다.");
			return false;
		} 
		
		return true;
	}
</script>

</head>
<body>
<div>
	<header>
<%-- 		<c:import url ="<%=request.getContextPath() %>/header.jsp" /> --%>
		<c:import url ="./header.jsp" />
	</header>
</div>
	<div class="container">
		<!-- 회원가입 -->
		<!-- 폼을 생성할 때 파일이 있으면
		enctype을 설정해야 하고 
		method는 반드시 post -->
		<form name="joinform" action="JoinProcess.member"
			enctype="multipart/form-data" method="post" onsubmit="return check()">

			<!-- 아이디 중복 검사 여부를 저장할 변수 -->
			<input type="hidden" name="idCheck" value="false" />

			<p align="center">
			<table class="table" width="60%" height="80%">
				<tr>
					<td colspan="3" align="center"><h2>회원 가입</h2></td>
				</tr>

				<tr>
					<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;이메일</font>
					</td>
					<td>&nbsp;&nbsp;&nbsp; <input type="text" name="email" id="email"
						size="20" maxlength=30>
						<input type=button class="btn btn-default"  value="중복확인" onClick="confirmId()" /> 
						<span id="emailSpan"></span>
					</td>
				</tr>

				<tr>
					<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호</font>
					</td>
					<td>&nbsp;&nbsp;&nbsp; <input type="password" name="pass"
						size="15" /><span id="pwSpan"></span>
					</td>
				</tr>
				<tr>
					<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호
							확인</font></td>
					<td>&nbsp;&nbsp;&nbsp; <input type="password" name="pass2"
						size="15" /><span id="pw2Span"></span>
					</td>
				</tr>

				<tr>
					<td colspan="2"><font size="2">&nbsp;&nbsp;&nbsp;
							(비밀번호는 대문자,소문자와 숫자를 조합하여 7~12자리로 만들어 주세요)</font></td>
				</tr>
			</table>
			<table width="80%">
				<tr>
					<td align="center"><br /> 
					<input type="submit" class="btn btn-default" value="회원가입" />
					<input type="button" class="btn btn-default" value="메인으로"
						onclick="javascript:window.location='./index.jsp'"></td>
				</tr>
			</table>
		</form>
		<!-- 회원가입 -->
	</div>
</body>
</html>
