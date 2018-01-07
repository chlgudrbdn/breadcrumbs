<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="../treant-js-master/Treant.css" type="text/css" /> -->
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="accumlatedCodes h-100 p-3" style="height:50%">
		<!-- 레코드가 있으면 -->
		<%-- 	<c:if test="${search_list_count > 0 }"> --%>
		<%-- 		<c:forEach var="b" items="${search_list}"> --%>
		<%-- 			${b.code_of_parentNodes} --%>
		<%-- 		</c:forEach> --%>
		<%-- 	</c:if> --%>

<!-- 		<div class="container"> -->
<!-- 			<pre> -->
<p>각종 코드들.</p>
<!-- </pre> -->
	</div>
	<div class="controllPanel h-100 p-3" >
			<form action="./addNode.node" method="post" name="NodeForm">
				<div class="form-group">
					<label for="Codes">Codes:</label>
					<textarea class="form-control" name="codeTypingArea" rows="10"
						id="codeTypingArea" ></textarea>
				</div>
				<button type="submit" class="btn btn-default" id=addCode>코드 수정/등록</button> <!-- CU -->
				<button type="button" class="btn btn-default" id=delNode>노드 삭제</button> <!-- D --> <!-- R은 그냥 노드 클릭하면 누적되서 보인다. -->
				<button type="button" class="btn btn-default" id=deactivateNode>노드 비활성화</button>
				<button type="button" class="btn btn-default" id=downloadTree>리프노드 코드 다운로드</button>
				<button type="button" class="btn btn-default" id=executeCode>선택 코드 실행(유료)</button>
<%-- <input type="button" value="삭제"onclick="location='g_cont.do?g_no=${g.g_no}&state=del'" /> --%>
			</form>
	</div>
<!-- 	</div> -->
</body>
</html>