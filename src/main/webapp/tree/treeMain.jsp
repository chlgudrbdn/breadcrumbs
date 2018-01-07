<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BreadCrumbs at turning point</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script> 
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div class="row" style="margin:5px">
<!-- 	<div class="left" style="width:50%;float:left"> -->
<!-- 	<div class="mv left"> -->
	<div class="col-sm-6 left" style="float:left">
			<c:import url="treeMap.jsp"></c:import>
	</div>
	
<!-- 	<div class="right"style="width:50%;float:right"> -->
<!-- 	<div class="mv right"> -->
	<div class="col-sm-6 right"style="float:right">
			<c:import url="treeConsole.jsp"></c:import>
	</div>
</div>
</body>
</html>