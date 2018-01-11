<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 내용보기_댓글입력폼_댓글목록</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
 function comment_check(){
	 if($.trim($("#comment_cont").val()) == ""){
		 alert("댓글 내용을 입력하세요!");
		 $("#comment_cont").val("").focus();
		 return false;
	 }
 }
 
 /* function com_del_ok($comment_no,$g_no){
	 if(window.confirm("정말로 댓글을 삭제할까요?")==true){
		 location="comment_del_ok.do?comment_no="+$comment_no+
				 "&g_no="+$g_no+"&state=cont";
		 
		   자바스크립트 최상위 객체는 웹브라우저를 가리키는 window객체이다. 이 객체
		  하위의 confirm()메서드는 삭제유무를 물어볼때 주로 사용한다.즉 확인/취소
		  버튼을 가진 경고창을 만들어 준다.확인을 클릭하면  true로 반환해서 삭제하게
		  하고,취소를 클릭하면 false 를 반환해서 삭제 안되게 한다.
		
	 }else{
		 return;
	 }
 }  */
</script>
<script>
	$(document).ready(function(){		
		
		// 댓글 작성
		$("#reply").click(function(){
			
			if($.trim($("#comment_cont").val()) == ""){
				 alert("댓글 내용을 입력하세요!");
				 $("#comment_cont").val("").focus();
				 return false;
			 }	
			if($.trim($("#comment_cont").val()).length > 200 ){
				 alert("200자 이내로 입력하세요!");
				 $("#comment_cont").val("").focus();
				 return false;
			 }
			
//			  alert("호출")
			  var g_no ='${g.g_no}';
			  var comment_name ='${g.g_name}';
			  var comment_cont =$("#comment_cont").val();
			  
			  /* var query = {g_no :'${g.g_no}', 
					  	   comment_name :'${g.g_name}',
					  	   comment_cont =$("#comment_cont").val()};	 */		  
			  
			  $.ajax({
					type:'post',
					url:'/breadcrumbs/reply.do',
					data: {"g_no":g_no,"comment_name":comment_name,"comment_cont":comment_cont}, 			
					/* data : query, */
					dataType:'text', 	 			
					success:function(result){
						$("#comment_cont").val("").focus();//내용 입력후 지우기
						
						alert(result)							
						
//						console.log("result: " + result);
						
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
</script>


</head>
<body>
 <table align="center" border="1" width="550">
  <tr>
   <th colspan="2">방명록 내용</th>
  </tr>
  <tr>
   <th>제목</th> <td>${g.g_title}</td>
  </tr>
  <tr>
   <th>내용</th> <td>${g_cont}</td>
  </tr>
  <tr>
   <th>조회수</th> <td>${g.g_hit}</td>
  </tr>
  <tr>
   <th colspan="2">
    <input type="button" value="수정"
		onclick="location='g_cont.do?g_no=${g.g_no}&state=edit'" />

    <input type="button" value="삭제"
		onclick="location='g_cont.do?g_no=${g.g_no}&state=del'" />

    <input type="button" value="목록" onclick="location='g_list.do'" />            
   </th>
  </tr>
 </table>
 
 <hr/>
 
 <%-- 댓글 입력폼 --%>
 <form ><!-- method="post" action="/g_reply.do"
 	onsubmit="return comment_check();">  -->
  <input type="hidden" name="g_no" value="${g.g_no}" />
  <input type="hidden" name="comment_name" value="${g.g_name}" />
  <table align="center" border="1" width="550">
   <tr>
    <td colspan="2">댓글수:<div id="reply_count">${com_count}</div></td>
   </tr>  
   <tr>
    <th>
     <textarea name="comment_cont" id="comment_cont"
     rows="5" cols="50"></textarea>
    </th>
    <th>
     <!-- <input type="submit" value="댓글달기" id="reply" /> -->
     <button type="button" id="reply">댓글달기</button>
    </th>
   </tr>
  </table>
 </form>
 
 <p/>
 <p/>
 

<%--댓글 목록 --%>
<table align="center" border="1" width="550" id="allData">
<c:if test="${!empty clist}">

  <c:forEach var="c" items="${clist}">
  		<tr>
   			<th>${c.comment_name}</th>
   			<td>${fn:substring(c.comment_date,0,16)}
   				<span id="com_del">
   					<a href="javascript:com_del_ok(${c.comment_no},${c.g_no});" 
   						onfocus="this.blur();">삭제</a></span>
 				<!--   onfocus="this.blur();" ie 웹브라우저 계열에서 하이퍼링크 걸린곳
   						클릭시 생기는 사각점선을 사라지게 한다. 삭제를 클릭시 com_del_ok()함수를  호출 -->
   			</td>
   					<!-- jstl substring을 사용하여 0이상 16미만 사이의 년월일 시분 까지만  출력한다. -->
  		</tr>
  		<tr>
   			<td colspan="2"><pre>${c.comment_cont}</pre></td>
  		</tr>
  </c:forEach>
  
</c:if>
</table> 
 
</body>
</html>