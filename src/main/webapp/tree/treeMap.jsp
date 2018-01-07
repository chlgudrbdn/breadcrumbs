<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>jstree Map application</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jstree/3.3.3/themes/default/style.min.css" /> -->
<!-- <link rel="stylesheet" href="dist/themes/default/style.min.css" />  기본 테마인데 구림. 위에건 CDN-->
<link rel="stylesheet" href="dist/themes/proton/style.min.css" />
<!-- 출처 : https://github.com/orangehill/jstree-bootstrap-theme -->

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script> CDN-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="dist/jstree.min.js"></script>


</head>

<body>
<div>
	<form id="s">
	  <input type="search" id="q" class="form-control d-inline-block" placeholder="Search" />
	  <button type="submit" class="btn btn-default d-inline-block" >Search</button>
	  <button type="button" class="btn btn-default d-inline-block" >file upload(유료)</button>
	</form>
</div>

	<div id="container"></div>
	<script>
		$('#container').jstree({
			"types" : {
			    "default" : {
// 			      "icon" : "glyphicon glyphicon-folder-close"
			    },
			    "diabled" : { //li 태그에 <li data-jstree='{"opened":true, "type":"demo"}'>같은 느낌으로 type이 부여되야함.
			      "icon" : "glyphicon glyphicon-er"
			    },
			    "data" : { //type 부여는 li_attr컬럼에 '{"type":"demo"}' 형식으로 넣어야할 것이다.
				      "icon" : "glyphicon glyphicon-file"
				},
			    "leaf" : { //li 태그에 <li data-jstree='{"opened":true, "type":"demo"}'>같은 느낌으로 type이 부여되야함.
				      "icon" : "glyphicon glyphicon-leaf"
				}
			},
			"core" : {
				"check_callback" : function (operation, node, parent, position, more) {
					if(operation === "copy_node" || operation === "move_node") {
				    	if(parent.id === "#") {
				        	return false; // prevent moving a child above or below the root
						}
				    }
				    return true; // allow everything else
				},
				'data' : {
					"url" : "//www.jstree.com/fiddle/?lazy",
			        "data" : function (node) {
			            return { "id" : node.id };
					},
					"dataType" : "json"
				},
				'themes': {
		            'name': 'proton',
		            'responsive': true
		        },
		        'check_callback' : function (operation, node, node_parent, node_position, more) {
		            // operation can be 'create_node', 'rename_node', 'delete_node', 'move_node', 'copy_node' or 'edit'
		            // in case of 'rename_node' node_position is filled with the new node name
// 		            	alert("node_position="+node_position);
// 		            	alert("node="+node);
// 		            	alert("node_parent="+node_parent);
// 		            	alert("more="+more);

		            if(operation==='create_node'){
		            	 
		            	$.ajax({
		 					type:'post',
		 					url:'/breadcrumbs/NodeAdd.node',
		 					data: {"id":id,"state":state,"comment_cont":comment_cont}, 			
		 					/* data : query, */
		 					dataType:'text', 	 			
		 					success:function(result){
		 						$("#comment_cont").val("").focus();//내용 입력후 지우기
		 						
		 						alert(result)							
//		 						console.log("result: " + result);
		 						var obj = JSON.parse(result);		                     
		 						
		 				}});  // ajax() end
		            	
		            	return true;
		            }
		            
		            
		            
		            return operation === 'create_node' ? true : false;
		        }
			},
// 				'massload' : { //아직 활성화시킬 수 없다. 한꺼번에 로딩해야할 url을 다르게 하여 반응토록 만들 예정.
// 			        "url" : "api/nodes",
// 			        "data" : function (ids) {
// 			            return { "id" : ids.join(",") };
// 			        }
// 			    },
			"plugins" : [ "dnd", "contextmenu", "checkbox", "state", "massload","search" ,"types"]
				//플러그인 종류
				//dnd : drag and drop
				//contextmenu : 오른쪽 클릭 가능
				//checkvox : 체크박스 추가(다중 선택은 당연)
				//state : lazy loading에서 한번 연것은 그냥 브라우저에 저장시켜 로딩 시간 단축
				//massload : 특정노드는 로딩 시 한꺼번에 받을 수 있게.(게시판에서만 작동토록 할 것)
				//search : 검색기능
				//sort : 알파벳 순 정렬. 필요없어서 안넣음.
		});
		
		  $('#container').on("changed.jstree", function (e, data) {
			    console.log("The selected nodes are:");
			    console.log(data.selected);
		  });
	</script>

</body>
</html>
