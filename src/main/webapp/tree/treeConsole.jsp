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
$(document).ready(function(){		
	
	
	// 댓글 작성
	$("#addNode").click(function(){
		
		if($.trim($("#codeTypingArea").val()) == ""){
			 alert("해당 단계의 코드를 입력하세요!");
			 $("#comment_cont").val("").focus();
			 return false;
		 }	
		
//		  alert("호출")
		  var g_no ='${g.node_no}';
		  var comment_name ='${g.g_name}';
		  var comment_cont =$("#codeTypingArea").val();
		  
		  /* var query = {g_no :'${g.g_no}', 
				  	   comment_name :'${g.g_name}',
				  	   comment_cont =$("#comment_cont").val()};	 */		  
		  
		  $.ajax({
				type:'post',
				url:'/restguestbook/addNode.tree',
				data: {"g_no":g_no,"comment_name":comment_name,"comment_cont":comment_cont}, 			
				/* data : query, */
				dataType:'text', 	 			
				success:function(result){
					$("#comment_cont").val("").focus();//내용 입력후 지우기
					
					alert(result)							
					
//					console.log("result: " + result);
					
					var obj = JSON.parse(result);		                     
					
					var output='';      					          			
	          						          			
						output += "<tr>" + "<td>"		                        
	                    output += obj.comment_name + "</td>" + "<td>"
	                    output += obj.comment_date + "삭제</td>" + "</tr><tr><td colspan=2><pre>"
	                    output += obj.comment_cont + "</pre></td>" + "</tr>"		                       
	             		       							
             			$("#reply_count").text(obj.com_count);	// 댓글수 증가 
	          			$("#allData").append(output); // 댓글을 추가
					
			}});  // ajax() end
			
	});	// click() end			
	
}); // ready() end

// function addNode() {
// 		NodeForm.submit();
// 	}
</script>
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
			<form action="./TreeAddAction.tree" method="post" name="NodeForm">
				<div class="form-group">
					<label for="Codes">Codes:</label>
					<textarea class="form-control" name="codeTypingArea" rows="10"
						id="codeTypingArea" ></textarea>
				</div>
				<button type="submit" class="btn btn-default" id=addNode>등록</button>
				<button type="button" class="btn btn-default" id=delNode>삭제</button>
				<button type="button" class="btn btn-default" id=deactivateTree>비활성화</button>
				<button type="button" class="btn btn-default" id=delTree>전체 코드 다운로드</button>
				<button type="button" class="btn btn-default" id=executeTree>선택 코드 실행</button>
<%-- <input type="button" value="삭제"onclick="location='g_cont.do?g_no=${g.g_no}&state=del'" /> --%>
			</form>
	</div>
<!-- 	</div> -->
</body>
</html>