<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물 목록</title>
</head>
<body>
	<div align="center">
		<table width="600" border="1">
			<tr>
				<td colspan="4" align="center">
				<h2>게시물 목록 출력</h2>
				</td>
			</tr>
			<tr>
				<th width="10%">번호</th>
				<th width="60%">제목</th>
				<th width="20%">작성일</th>
				<th width="10%">조회수</th>
			</tr>	
			<c:forEach var="item" items="${result}">
			<tr>
				<td align="center">공지</td>
				<td>
				<a href="NoticeDetail.board?num=${item.notice_Num}">
					${item.notice_Subject}	
				</a>
				</td>
				<td align="center">${item.notice_Date }</td>
				<td align="right">
				${item.notice_Readcount}&nbsp;&nbsp;</td>
				</tr>	
			</c:forEach>
			<c:forEach var="item" items="${boardlist}">
				<tr>
					<td align="right">${item.board_Num }
					&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;
					<a href="BoardDetail.board?num=${item.board_Num}">
					${item.board_Subject}
					</a></td>
					<td align="center">${item.board_Date}</td>
					<td align="right">
					${item.board_ReadCount}&nbsp;&nbsp;</td>
				</tr>	
			</c:forEach>
			<tr>
				<td colspan="4" align="center">
				<!-- 현재 page가 1이 아니면 이전 페이지로
				이동할 수 있는 [이전] 링크 만들기 -->
				<c:if test = "${page>1} ">
					<a href="BoardList.board?page=${page-1}">
					[이전]	
					</a>
				</c:if>
				<!-- 페이지 번호 목록 출력 -->
				<c:forEach var="a" begin="${startpage}" 
				end="${endpage}">
				<!-- 출력하는 페이지 번호가 현재 페이지
				번호이면 그냥 출력하고 그렇지 않으면 링크 설정 -->
				<c:if test="${a==page}">
					[${a}]
				</c:if>
				<c:if test="${a!=page}">
					<a href="BoardList.board?page=${a}">
					[${a}]</a>
				</c:if>
				</c:forEach>
				<!-- 현재 출력 중인 페이지가 마지막 페이지가 아니면
				[다음]을 만들어서 현재 출력 중인 페이지 다음으로
				이동하도록 링크 설정 -->
				<c:if test="${page!=maxpage }">
					<a href="BoardList.board?page=${page+1 }">
					[다음]</a>
				</c:if>
				</td>
				</tr>
				
				<tr>
					<td colspan="4" align="center">
						<a href="./index.jsp">[메인으로]</a>
						<a href="BoardWrite.board">[게시글 작성]</a>
					</td>
				</tr>		
		
		</table>
	</div>
</body>
</html>







