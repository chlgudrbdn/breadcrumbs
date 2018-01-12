<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>트리만들기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script>
	//body의 내용을 읽자 마자 호출되는 jquery 이벤트
	//자바스크립트의 window.onload와 유사하지만 
	//먼저 호출됩니다.
	$(function(){
		// 파일을 변경하면 함수 호출
		$("#dataInput").on('change', function(){
			readURL(this);
		});

	
	function readURL(input){
		//input에 선택된 파일이 있다면
		if(input.files && input.files[0]){
			//파일 이름 가져오기
			var filename = input.files[0].name;
			//파일의 내용을 읽을 수 있는 객체 생성
			var reader = new FileReader();
			//input.files[0] 파일을 읽기
			reader.readAsDataURL(input.files[0]);
			//파일을 전부 읽었으면
// 			reader.onload = function(e){
// 				$("#dataInputSpan").innerHTML = filename;
// 			}
		}
	}
	
// 	function confirmCategory(){	
//		$("#emailSpan").hide();
//		alert("들어옴");
		//ajax 요청
		//ajax: 페이지 이동이 아니고 페이지의 내용을
		//가져오는 것
		
		// 		http://hellogk.tistory.com/74 보고 추천 검색 하도록 한다.
		$( "#category" ).autocomplete({
			source : function( request, response ) {
				$.ajax({
		        	type: 'post',
		            url: "/breadcrumbs/checkRecommendCategory.node",
		            dataType: "json",
		            autoFocus: true,
		            scroll: true,
// 		            error:function(request,status,error){ console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); },
// 		            출처: http://withpie.tistory.com/entry/Spring-Ajax-Json-406-error [이상한 나라의 루돌프]
// 		            request.term = $("#category").val(),
		            data: { value : request.term },
			            success: function(data) {
			            //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
			            console.log(data);
			            response(
			            	$.map(data, function(item) {
			                	return {
			                    	label: item,
			                        value: item
			                    }
			                })
			            );
		            }
				});
			},
		    //조회를 위한 최소글자수
		    minLength: 2,
		    select: function( event, ui ) {
		    // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
				event.preventDefault();
		    	$("#category").val(ui.item.value);
		    }
		});
// 	}
	
	//유효성 검사를 위한 함수
	function check(){
		//카테고리 관련 유효성 검사
		var category = joinform.category.value;
		var categorySp = 
			document.getElementById("categorySpan");
		//카테고리에 값이 없으면 span에 메시지 출력하고
		//전송되지 않도록 설정
		if(category.length == 0){
			categorySp.innerHTML = "카테고리는 필수 입력입니다.";
			$( "#category" ).focus();
			return false;
		}else if(category.length >= 30){
			categorySp.innerHTML = "30자 이상은 카테고리를 입력할 수 없습니다.";
			$( "#category" ).focus();
			return false;
		}
		return true;
	}
	
	});
</script>

</head>
<body>
<div>
	<header>
		<c:import url ="../member/header.jsp" />
	</header>
</div>

<div class="container col-sm-8 col-md-offset-2">
		<!-- 회원가입 -->
		<!-- 폼을 생성할 때 파일이 있으면
		enctype을 설정해야 하고 
		method는 반드시 post -->
		<form name="makeTree" action="makeRootNode.node"
			enctype="multipart/form-data" method="post" onsubmit="return check()">
			<!-- 아이디 별로 종속된  -->
			<input type="hidden" name="categoryCheck" value="false" /> <!-- 일단은 이건 새로 카테고리를 추가해야하는가 마는가의 이야기. -->
			<input type="hidden" name="recommend_cnt" value="0" /><!-- 처음엔 누가  추천할리가 없으므로-->
			<input type="hidden" name="email" value="${member.email}" /><!-- 처음엔 누가  추천할리가 없으므로-->
<!-- 			<p align="center"> -->
			<table class="table">
				<tr>
					<td colspan="3" align="center"><h2>트리 작성</h2></td>
				</tr>
				<tr>
					<td colspan="2"><font size="2">
							카테고리는 30자 이하에 대소문자는 구분됩니다.
							<br>
							기존 카테고리를 사용할 수록 더 좋은 선택결과를 추천 받을 가능성이 높아집니다.</font></td>
				</tr>
				<tr>
					<td width="17%" bgcolor="#f5f5f5"><font size="2">*카테고리</font>
					</td>
					<td><input type="text" id="category" name="category" size="20" /> <!-- 일단 나중에 추천 검색어 처럼 아래에 추천 카테고리가 나오도록 한다. 후순위. http://tcpschool.com/ajax/ajax_application_suggestion-->
						<span id="categorySpan"></span>
<!-- 						<input type=button value="중복확인" onClick="confirmCategory()" />  -->
					</td>
				</tr>
				
				<tr>
					<td width="17%" bgcolor="#f5f5f5"><font size="2">분석 데이터<br>(파일 하나만)</font><!-- 일단은 하나만 넣을 수 있게. -->
					<td><input type='file' class="btn btn-default" id="dataInput" name="dataInp" />
							<span id="dataInputSpan"></span>
					</td>
				</tr>
				
				<tr>
					<td width="17%" bgcolor="#f5f5f5"><font size="2">루트노드명<br>(20자 이하를 권장합니다)</font>
					</td>
					<td><input type="text" id="rootnodeName" name="rootnodeName" size="20" /> <!-- 일단 나중에 추천 검색어 처럼 아래에 추천 카테고리가 나오도록 한다. 후순위. http://tcpschool.com/ajax/ajax_application_suggestion-->
						<span id="rootnodeSpan"></span>
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td bgcolor="#f5f5f5"><font size="2">비밀번호</font> -->
<!-- 					</td> -->
<!-- 					<td><input type="password" name="pw" size="15" /><span id="pwSpan"></span> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;비밀번호 -->
<!-- 							확인</font></td> -->
<!-- 					<td>&nbsp;&nbsp;&nbsp; <input type="password" name="pw2" -->
<!-- 						size="15" /><span id="pw2Span"></span> -->
<!-- 					</td> -->
<!-- 				</tr> -->



<!-- 				<tr> -->
<!-- 					<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;기본주소</font> -->
<!-- 					</td> -->

<!-- 					<td>&nbsp;&nbsp;&nbsp; <input type="text" name="address1" -->
<!-- 						size="50" /> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td bgcolor="#f5f5f5"><font size="2">&nbsp;&nbsp;&nbsp;&nbsp;상세주소</font> -->
<!-- 					</td> -->
<!-- 					<td>&nbsp;&nbsp;&nbsp; <input type="text" name="address2" -->
<!-- 						size="50" /> -->
<!-- 					</td> -->
<!-- 				</tr> -->
			</table>
			<table width="80%">
				<tr>
					<td align="center"><br /> 
					<input type="submit" class="btn btn-default" value="트리 작성 시작" />
					<input type="reset" class="btn btn-default" value="작성 내용 초기화" ></td>
				</tr>
			</table>
		</form>
	</div>


</body>
</html>