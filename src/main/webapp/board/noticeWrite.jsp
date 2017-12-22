<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
<title>공지사항 쓰기</title>
<script >
	function addNotice() {
		noticeform.submit();
	}
</script>
</head>
<body>
	<div align="center">
	<form action="./NoticeAddAction.board" method="post" name="noticeform">
		<table >
			<tr align="center" valign="middle">
				<td colspan="5">공지사항</td>
			</tr>
			<tr>
				<td style="font-family: 돋음; font-size: 12" height="16">
					<div align="center">제 목</div>
				</td>
				
				<td><input name="notice_Subject" type="text" size="50"
					maxlength="100" value="" /></td>
			</tr>

			<tr>
				<td style="font-family: 돋음; font-size: 12">
					<div align="center">내 용</div>
				</td>
				<td><textarea name="notice_Content" cols="67" rows="15"></textarea>
				</td>
			</tr>
			<tr align="center" valign="middle">
				<td colspan="5"><a href="javascript:addNotice()">[등록]</a>&nbsp;&nbsp;
					<a href="javascript:history.go(-1)">[뒤로]</a>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>
				