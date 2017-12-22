<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 상세보기</title>
</head>
<body>
	<div align="center">
		<table border="1">
			<tr>
				<td>제목</td>
				<td>${boarddata.board_Subject}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${boarddata.board_Content}</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
				<a href="download.board?filename=${boarddata.board_file }">
				${boarddata.board_file}
				</a>
				</td>
			</tr>
			<!-- 수정,삭제,목록 버튼 만들기
			수정과 삭제는 글쓴이와 
			로그인한 사용자가 같을 때만 출력 -->	
			<tr>
				<td colspan="2" align="center">
				<c:if test=
				"${member.email == boarddata.board_Id}">
					<a href="BoardModify.board?num=${boarddata.board_Num }">[수정]</a> 
					<a href="BoardDelete.board?num=${boarddata.board_Num }">[삭제]</a>
				</c:if>
				<a href="BoardList.board">[목록]</a>
				</td>
			</tr>	
			<c:if test="${replydata != null }">
				<c:forEach var="re" items="${replydata }">
					<tr>
						<td><img width="50" height="50"
						src="./image/${re.IMAGE }" />
						</td>
						<td>
							${re.NAME }:${re.REPLY_CONTENT }
						</td>
					</tr>	
				</c:forEach>
			</c:if>	
			
			<tr>
				<td colspan="2">
				<form action="ReplyAdd.board"
				method="post">
					<!-- 현재 글번호 숨겨서 저장 -->
					<input type="hidden"
					value="${boarddata.board_Num}"
					name="num" />
					<textarea cols="60" rows="3"
					name="comment"></textarea>
					<input type="submit" value="댓글입력"/>
				</form>
				</td>
			</tr>	
			
		</table>
	</div>
</body>
</html>






