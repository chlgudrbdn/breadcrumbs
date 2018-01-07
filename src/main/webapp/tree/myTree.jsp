<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
		<table>
			<tr>
				<td colspan="4">
				<h2>${email} 님의 결정트리 출력.</h2>
				</td>
			</tr>
			<tr>
				<th>트리번호</th>
				<th>카테고리</th>
				<th>링크</th>
				<th>추천수</th>
				<th>삭제</th>
			</tr>	
			<c:forEach var="item" items="${treeList}">
			<tr>
				<td>${item.tree_no}</td>
				<td>${item.category}</td>
				<td>
					<a href="treeMap.node?tree_no=${item.tree_no}">
						수정 및 열람
					</a>
				</td>
				<td>
				${item.recommend_cnt}</td>
				</tr>	
				<td>
					<a href="delTree.node?tree_no=${item.tree_no}">
						삭제
					</a>
				</td>
			</c:forEach>
<tr> 
<td colspan="4" align="center">
<!-- 				현재 page가 1이 아니면 이전 페이지로
<!-- 				이동할 수 있는 [이전] 링크 만들기 -->
<%-- 				<c:if test = "${page>1} "> --%>
<%-- 					<a href="BoardList.board?page=${page-1}"> --%>
<!-- 					[이전]	 -->
<!-- 					</a> -->
<%-- 				</c:if> --%>
<!-- 				페이지 번호 목록 출력 -->
<%-- 				<c:forEach var="a" begin="${startpage}"  --%>
<%-- 				end="${endpage}"> --%>
<!-- 				출력하는 페이지 번호가 현재 페이지
<!-- 				번호이면 그냥 출력하고 그렇지 않으면 링크 설정 -->
<%-- 				<c:if test="${a==page}"> --%>
<%-- 					[${a}] --%>
<%-- 				</c:if> --%>
<%-- 				<c:if test="${a!=page}"> --%>
<%-- 					<a href="BoardList.board?page=${a}"> --%>
<%-- 					[${a}]</a> --%>
<%-- 				</c:if> --%>
<%-- 				</c:forEach> --%>
<!-- 				현재 출력 중인 페이지가 마지막 페이지가 아니면
<!-- 				[다음]을 만들어서 현재 출력 중인 페이지 다음으로 -->
<!-- 				이동하도록 링크 설정 -->
<%-- 				<c:if test="${page!=maxpage }"> --%>
<%-- 					<a href="BoardList.board?page=${page+1 }"> --%>
<!-- 					[다음]</a> -->
<%-- 				</c:if> --%>
<!-- 				</td> -->
<!-- 				</tr> -->
				<tr>
					<td colspan="4" align="center">
						<a href="./makeTree.jsp">[새로운 트리 작성]</a>
						<a href="./treeboard.node">[참고할 트리 확인]</a>
					</td>
				</tr>		
		
		</table>
	</div>



</body>
</html>