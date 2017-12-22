<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
</head>
<body>
		<div align="center">
			<table width="400" border="1">
				<tr>
					<th width="50%">이메일</th>
					<th width="20%">이름</th>
					<th width="20%">강제탈퇴</th>
				</tr>
				<!-- JSTL을 이용한 배열이나
				List 출력 -->
				<c:forEach var="item" items="${result}">
					<!-- 리스트나 배열의 요소가 DTO나 맵인 경우
					변수명.키이름 을 이용하면 출력가능 -->
					<tr>
						<td>${item.email}</td>
						<td>${item.name }</td>
						<td>
						<a href="DeleteMember.member?email=${item.email}">
						삭제</a></td>
					</tr>	
				
				</c:forEach>	
				<tr>
					<td colspan="3" align="center">
					<a href="SleepMember.member">[휴면계정정리]</a>
					&nbsp;&nbsp;
					<a href="index.jsp">[메인으로]</a>
					</td>
				</tr>	
			</table>
		</div>
</body>
</html>



