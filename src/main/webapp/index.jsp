<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- if 문을 사용하기 위해서 jstl 라이브러리를 추가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>breadcrumb Main page </title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<!-- 로그인 되어 있다면 - 
	session의 member에 로그인 정보를 저장-->
<div>
	<header>
		<c:import url ="./member/header.jsp" />
<%-- 		<c:import url ="<%=request.getContextPath() %>/member/header.jsp" /> --%>
<%-- 		<jsp:include page="<%=request.getContextPath() %>/member/header.jsp" /> --%>
	</header>
</div>

<!-- 	<div> -->
<%-- 		<c:import url="tree/treeMain.jsp"></c:import> --%>
<!-- 	</div> -->
	
</body>
</html>










