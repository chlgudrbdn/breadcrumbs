<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
<title>MVC 게시판</title>
<script>
	function addboard() {
		boardform.submit();
	}
</script>
</head>
<body>
	<!-- 게시판 등록 -->
	<!-- 파일을 전송할 때는 method는 반드시 post 이어야 하고
enctype을 반드시 변경해 주어야 합니다. 그렇지 않으면 파일의 내용이 전송되는 것이 아니고
파일의 이름이 전송됩니다. -->
	<form action="./BoardAddAction.board" method="post"
		enctype="multipart/form-data" name="boardform">

		<!-- id를 입력받지 않고 다음 요청으로 전송하기 위해서 hidden을 생성 -->
		<input type="hidden" name="email" value="${member.email}">

		<table>
			<tr align="center" valign="middle">
				<td colspan="5">게시판 글쓰기</td>
			</tr>
			<tr>
				<td style="font-family: 돋음; font-size: 12" height="16">
					<div align="center">글쓴이</div>
				</td>
				<td>${member.name}</td>
			</tr>


			<tr>
				<td style="font-family: 돋음; font-size: 12" height="16">
					<div align="center">제 목</div>
				</td>
				<td><input name="board_Subject" type="text" size="50"
					maxlength="100" value="" /></td>
			</tr>

			<tr>
				<td style="font-family: 돋음; font-size: 12">
					<div align="center">내 용</div>
				</td>
				<td><textarea name="board_Content" cols="67" rows="15"></textarea>
				</td>
			</tr>

			<tr>
				<td style="font-family: 돋음; font-size: 12">
					<div align="center">파일 첨부</div>
				</td>

				<td><input name="board_File" type="file" /></td>
			</tr>
			<tr bgcolor="cccccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>

			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr align="center" valign="middle">
				<td colspan="5"><a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp;
					<a href="javascript:history.go(-1)">[뒤로]</a></td>
			</tr>
		</table>
	</form>
</body>
</html>


