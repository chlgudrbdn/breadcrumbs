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
<script>
	function addNode() {
		NodeForm.submit();
	}
</script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class=accumlatedCodes>
		<!-- 레코드가 있으면 -->
		<%-- 	<c:if test="${search_list_count > 0 }"> --%>
		<%-- 		<c:forEach var="b" items="${search_list}"> --%>
		<%-- 			${b.code_of_parentNodes} --%>
		<%-- 		</c:forEach> --%>
		<%-- 	</c:if> --%>

<!-- 		<div class="container"> -->
			<pre>
각종 코드들.



</pre>

			<form action="./TreeAddAction.tree" method="post" name="NodeForm">
				<div class="form-group">
					<label for="Codes">Codes:</label>
					<textarea class="form-control" name="codeTypingArea" rows="10"
						id="codeTypingArea"></textarea>
				</div>
				<button type="submit" class="btn btn-default"
					onclick='href="javascript:addNode()"'>등록</button>
			</form>
<!-- 		</div> -->
	</div>
</body>
</html>