<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" 
uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정</title>
<script>
	function modifyboard(){
		//modifyform의 내용을 전송
		modifyform.submit();
	}
</script>
</head>
<body>
	<!-- 파일 업로드가 있는 폼의 기본 형식은 
	method는 post 이어야 하고 
	enctype이 multipart/form-data 이어야 합니다. -->
	<form action="BoardModifyAction.board"
	method="post" name="modifyform"
	enctype="multipart/form-data">
	
	<!-- 직접 입력받지 않고 다음 페이지로 데이터를
	전송하고자 할 때는 input type이 hidden 인 객체를
	만들어서 넘겨줍니다.
	폼의 전송이나 링크를 타고 이동하는 것은 forwarding이
	아니기 때문에 request를 이용해서 데이터를
	넘겨줄 수 없습니다. -->
	<!-- 글번호, 접속한 유저의 이메일, 파일이름을
	저장 -->
	<input type="hidden" name="board_num"
	value="${boarddata.board_Num }"/>
	<input type="hidden" name="email"
	value="${member.email }" />
	<input type="hidden" name="board_oldfile"
	value="${boarddata.board_file }"/>
	
	<table align="center" border="1">
		<tr>
			<td colspan="2" align="center">
			<h2>게시글 수정</h2>
			</td>
		</tr>
		<tr>
			<td align="right">제목</td>
			<td><input type="text" 
		name="board_Subject"
		value="${boarddata.board_Subject}"
		size="50" />
			</td>
		</tr>	
		<tr>
			<td align="right">내용</td>
			<td><textarea cols="60" rows="15"
			name="board_Content">${boarddata.board_Content}
			</textarea>
			</td>
		</tr>
		<tr>
			<td align="right">첨부파일</td>
			<td>기존파일:${boarddata.board_file}
			<br />
			<input type="file" name="board_File" />
			<br/>
			파일을 수정하려면 다시 선택하세요
			</td>
		</tr>	
		<tr>
			<td colspan="2" align="center">
			<a href="javascript:modifyboard()">수정</a>
			&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">뒤로</a>
			</td>
		</tr>	
	
	</table>
	</form>
</body>
</html>















