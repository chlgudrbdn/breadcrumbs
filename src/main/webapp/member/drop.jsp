<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>회원탈퇴</title>
<c:if test="${result != null}">
	<script>
		window.onload = function() {
			document.getElementById("dropSpan").innerHTML = "탈퇴에 실패하셨습니다.";
		};
	</script>
</c:if>
<c:if test="${result == null}">
	<script>
		window.onload = function() {
			document.getElementById("dropSpan").innerHTML = ""
		}
	</script>
</c:if>
</head>

<body>
	<table width="960" cellspacing="0" cellpadding="0" border="0"
		align="center">
		<tr>
			<td colspan=2>
				<p align="center">
				<form action="./DropProcess.member" method="post">
					<table border="1" width="380" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center" colspan="2">
								<h2>회원 탈퇴</h2> <span id="dropSpan"></span>
							</td>
						</tr>
						<tr>
							<td align="center" height="35" width="125"><font size="2">비밀번호</font></td>

							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="password" name="pw" />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2" height="35"><input
								type="submit" value="회원 탈퇴" /> <input type="button"
								value="초기 화면" onclick="script:location.href = 'index.jsp'" /></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
