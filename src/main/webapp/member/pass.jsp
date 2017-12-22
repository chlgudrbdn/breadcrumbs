<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
	var su = 0;
	var i = 0;
	setInterval(function(){
		su++;
		if(su == 4){
			location.href="./index.jsp";
		}
		var msg = "time/" + (6-su) + ".png";
		$('#t').attr("src", msg);
	}, 1000)
	setInterval(function(){
		i++;
		var msg1 = "time/heli" + (i%3+1) + ".png";
		$('#t1').attr("src", msg1);
	}, 50)
</script>
</head>
<body>
	비밀번호가 변경되었습니다.
	<img width="100" height="100" 
	src="time/9.png" id="t" />
	<img width="100" height="100" 
	src="time/heli1.png" id="t1" />
</body>
</html>


