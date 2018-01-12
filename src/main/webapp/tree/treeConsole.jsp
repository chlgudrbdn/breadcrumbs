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
$(function(){
	
	

	$("#addCode").click(function(){
// 		$.ajax({
// 			type:'post',
// 			url:'/restguestbook/reply.do',
// 			data: {"g_no":g_no,"comment_name":comment_name,"comment_cont":comment_cont}, 			
// 			/* data : query, */
// 			dataType:'text', 	 			
// 			success:function(result){
// 				$("#comment_cont").val("").focus();//내용 입력후 지우기
				
// 				alert(result)							
				
// 		//		console.log("result: " + result);
				
// 				var obj = JSON.parse(result);		                     
				
// 				var output='';      					          			
// 		 			$("#reply_count").text(obj.com_count);	// 댓글수 증가 
// 		  			$("#allData").append(output); // 댓글을 추가
// 		});  // ajax() end
	});  // ajax() end
	
	$("#executeCode").click(function(){
		var codes = $("#accumlatedCodes").text().trim().split("\n");
		console.log(codes);
		$.ajax({ 
			url: "/breadcrumbs/executeCode.node",
		    data: {"codes" : codes},
		    type: 'post',
		    dataType:'json', 
		    success: function(result){
// 		    	console.log(result)
		    	$("#codeExecuteResult").val("")
		    	var arr = $.map(result, function(el) { return el });
// 		    	console.log(arr)
				
		    	var output="<ul>"
		    	$.each(arr, function(i){
// 			    	console.log(arr[i])
					output+="<li>"+arr[i]+"</li>"		    		
		    	})
		    	output+="</ul>"
// 			    	console.log(output)
			    $("#codeExecuteResult").append(output);
		    }
		});
	
	});
	
});
</script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="h-100 p-3" id="accumlatedCodes">
		<!-- 레코드가 있으면 -->
		<%-- 	<c:if test="${search_list_count > 0 }"> --%>
		<%-- 		<c:forEach var="b" items="${search_list}"> --%>
		<%-- 			${b.code_of_parentNodes} --%>
		<%-- 		</c:forEach> --%>
		<%-- 	</c:if> --%>
<!--   library(Rserve) -->
<!-- Rserve(FALSE,port=6311,args='--RS-encoding utf8 --no-save --slave --encoding utf8  --internet2') -->
<!-- Rserve(args="--RS- encoding utf8") -->
<!-- 출처: http://tastydarr.tistory.com/64 [맛동산] -->
  <p>getwd()
  <p>x1<-c(1,3,5,7,9)

  <p># x is numeric or character?
  <p>class(x1)
  <p>is.numeric(x1)
	</div>

	<div class="controllPanel h-50 p-3" >
			<form action="./addCode.node" method="post" name="NodeForm">
				<div class="form-group">
					<label for="Codes">Codes:</label>
					<textarea class="form-control" name="codeTypingArea" rows="5"
						id="codeTypingArea" ></textarea>
				</div>
				<button type="submit" class="btn btn-default" id=addCode>코드 수정/등록</button> <!-- CU -->
				<button type="button" class="btn btn-default" id=delNode >노드 삭제</button> <!-- D --> <!-- R은 그냥 노드 클릭하면 누적되서 보인다. -->
				<button type="button" class="btn btn-default" id=deactivateNode>노드 비활성화</button>
				<button type="button" class="btn btn-default" id=downloadTree>리프노드 코드 다운로드</button>
				<button type="button" class="btn btn-default" id=executeCode>선택 코드 실행(유료)</button>
<%-- <input type="button" value="삭제"onclick="location='g_cont.do?g_no=${g.g_no}&state=del'" /> --%>
			</form>
	</div>
	<label>Result :</label>
	<div class="h-50 p-3" id="codeExecuteResult">
	</div>
<!-- 	</div> -->
</body>
</html>