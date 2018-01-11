<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>게시판 입력폼</title>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<!-- <script src="/breadcrumbs/js/board.js"></script>		 -->
	<script>
	$(document).ready(function() {
		$("form").submit(function() {
			if ($.trim($("#board_name").val()) == "") {
				alert("글쓴이를 입력하세요!");
				$("#board_name").val("").focus();
				return false;
			}
			if ($.trim($("#board_pass").val()) == "") {
				alert("비밀번호를 입력하세요!");
				$("#board_pass").val("").focus();
				return false;
			}
			if ($.trim($("#board_subject").val()) == "") {
				alert("글제목을 입력하세요!");
				$("#board_subject").val("").focus();
				return false;
			}
			if ($.trim($("#board_content").val()) == "") {
				alert("글내용을 입력하세요!");
				$("#board_content").val("").focus();
				return false;
			}

		});

	});
	</script>
</head>

<body>
 <div id="bbswrite_wrap">
  <h2 class="bbswrite_title">게시판 입력폼</h2>
  <form method="post" action="/breadcrumbs/board_write_ok.do">
   <table id="bbswrite_t">
    <tr>
     <th>글쓴이</th>
     <td>
     <input name="board_name" id="board_name" size="14" class="input_box" />
     </td>
    </tr>
    
    <tr>
     <th>비밀번호</th>
     <td>
      <input type="password" name="board_pass" id="board_pass" size="14"
      class="input_box" />
     </td>
    </tr>
    
    <tr>
     <th>글제목</th>
     <td>
      <input name="board_subject" id="board_subject" size="40" 
      class="input_box" />
     </td>
    </tr>
    
    <tr>
     <th>글내용</th>
     <td>
      <textarea name="board_content"  id="board_content" rows="8" cols="50"
      class="input_box"></textarea>
     </td>
    </tr> 
    
    <tr>
    <td>
    
   </table>
   
   <div id="bbswrite_menu">
    <input type="submit" value="등록" class="input_button" />
    <input type="reset" value="취소" class="input_button"
    onclick="$('#board_name').focus();" />
   </div>
  </form>
 </div>
</body>
</html>