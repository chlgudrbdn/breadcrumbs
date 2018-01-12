<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 수정</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>


<script>
// $(function() {
//     $("#imgInp").on('change', function(){
//         readURL(this);
//     });
// });
// function readURL(input) {
//     if (input.files && input.files[0]) {
//     var filename = input.files[0].name;
//     var ext = filename.substr(filename.length-3, filename.length);
//     var isCheck=false;
//     if((ext.toLowerCase() == "jpg" || ext.toLowerCase() == "gif")){
//     	isCheck=true;
//     }
//     if(isCheck == false){
//     	alert("jpg나 gif만 업로드가 가능합니다.");
//     	return;
//     }
//     var reader = new FileReader();

//     reader.onload = function (e) {
//             $('#image').attr('src', e.target.result);
//         }
//       reader.readAsDataURL(input.files[0]);
//     }
// }
//유효성 검사를 위한 함수
function check() {
// 	var name = updateform.name.value;
	var password1 = updateform.pass.value;
	var password2 = updateform.pass2.value;
// 	var mobile = updateform.mobile.value;

	//이름 관련
// 	if ((name == "") || (name.length <= 1)) {
// 		document.getElementById("nameSpan").innerHTML = "이름을 제대로 입력해 주세요!";
// 		updateform.name.focus();
// 		return false;
// 	}
// 	else{
// 		document.getElementById("nameSpan").innerHTML = "";
// 	}
	
	
	//이름을 한글 2자 이상 입력받기
	//정규식(javascript, java, c#, php에 모두 활용) 객체 생성
	var regExp = /([a-z, A-Z, 가-힣]){2,}/g;
	if(!regExp.test(name)){
		document.getElementById("nameSpan").innerHTML = "이름은 문자 2자 이상입니다!";
		updateform.name.focus();
		return false;
	}
	else{
		document.getElementById("nameSpan").innerHTML = "";
	}

	//비밀번호 관련
	if (password1.length < 7 && password1.length > 12 ) {
		document.getElementById("pwSpan").innerHTML = "비밀번호는 7자 이상 12자 이하로 입력하세요!";
		updateform.pw.focus();
		return false;
	}
	else{
		document.getElementById("pwSpan").innerHTML = "";
		updateform.pw.focus();
	}
		
	//비밀번호는 영문 대소문자 및 숫자 1개 이상 입력받기
	var s_cnt = 0;
	var d_cnt = 0;
	var n_cnt = 0;		
	for(var i=0; i<password1.length; i++){
		var ch = password1.charAt(i);
		if(ch>='a' && ch<='z')
			s_cnt++;
		else if(ch>='A' && ch<='Z')
			d_cnt++;
		else if(ch>='0' && ch<='9')
			n_cnt++;
	}		
	
	if(s_cnt<1 || d_cnt<1 || n_cnt<1){
		updateform.pw.value = "";
		document.getElementById("pwSpan").innerHTML = "비밀번호는 영문대소문자와 숫자가 1개 이상 포함되어야 합니다!";
		updateform.pw.focus();			
		return false;
	}
	else{
		document.getElementById("pwSpan").innerHTML = "";
	}
	
			
		if (password2.length == 0) {
		document.getElementById("pw2Span").innerHTML = "비밀번호를 입력하세요!";
		updateform.pw2.focus();
		return false;
	}
	else if (password1 != password2) {
		updateform.pw.value = "";
		updateform.pw2.value = "";
		document.getElementById("pwSpan").innerHTML = "비밀번호가 일치하지 않습니다!";			
		updateform.pw.focus();
		return false;
	}
	else{
		document.getElementById("pw2Span").innerHTML = "";
		updateform.pw.focus();
	}
	
	//Mobile 관련
// 	if (mobile.length != 11) {
// 		document.getElementById("mobileSpan").innerHTML = "휴대폰 번호를 입력하세요!(휴대폰 번호는 11자리입니다.)";
// 		updateform.mobile.focus();
// 		return false;
// 	}
// 		else{
// 		document.getElementById("mobileSpan").innerHTML = "";
// 	}
// return true;
	
}
// function onlyNum(e) {
// 		var event = e || window.event;

// 		var keycode = event.keyCode || e.which;
// 		if (keycode == 8 || (keycode >= 35 && keycode <= 40)
// 				|| (keycode >= 46 && keycode <= 57)
// 				|| (keycode >= 96 && keycode <= 105)) {
// 			return true;
// 		} else {
// 			return false;
// 		}
// 	}
	
</script>
</head>
<body>

<div>
	<header>
		<c:import url ="./header.jsp" />
	</header>
</div>
	
	<div class="container col-sm-6 col-md-offset-3">
		<!-- 회원가입 -->
		<!-- 폼을 생성할 때 파일이 있으면
		enctype을 설정해야 하고 
		method는 반드시 post -->
		<form name="updateform" action="UpdateProcess.member"
			enctype="multipart/form-data" method="post" onsubmit="return check()">

			<input type="hidden" name="email" value="${member.email }" /> 
			<input type="hidden" name="payment_type" value="${member.payment_type}" /> 

			<p align="center">
			<table class="table" width="60%" height="80%">
				<tr>
					<td colspan="3" align="center"><h2>회원 정보 수정</h2>
					<br>
					<c:if test="${result == false}">
						<span>회원정보 수정에 실패했습니다.</span>
					</c:if>
					</td>
				</tr>

				<tr>
					<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;이메일</font>
					<td>${member.email } </td> 
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
						<input type="submit" class="btn btn-default" value="수정완료" />
						<input type="button" class="btn btn-default" value="메인으로"
						onclick="javascript:window.location='./index.jsp'"></td>
				</tr>
			</table>
		</form>
		<!-- 회원가입 -->
	</div>
	
</body>
</html>

