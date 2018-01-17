<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>jstree Map application</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jstree/3.3.3/themes/default/style.min.css" /> -->
<!-- <link rel="stylesheet" href="dist/themes/default/style.min.css" />  기본 테마인데 구림. 위에건 CDN-->
<link rel="stylesheet" href="/breadcrumbs/tree/dist/themes/proton/style.min.css" />
<!-- 출처 : https://github.com/orangehill/jstree-bootstrap-theme -->

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script> CDN-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/breadcrumbs/tree/dist/jstree.min.js"></script>


</head>

<body>
<div>
	<form id="s">
	  <input type="search" id="q" class="form-control d-inline-block" placeholder="Search" />
	  <button type="submit" class="btn btn-default d-inline-block" >Search</button>
	  <button type="button" class="btn btn-default d-inline-block" >file upload</button>
	</form>
</div>

	<div id="container"></div>
	<div><span id="systemAlert"></span></div>
	<script>
	var tree_no = '${tree_no}';
	var node;
		$('#container').jstree({
			"types" : {
			    "default" : {
// 			      "icon" : "glyphicon glyphicon-folder-close"
			    },
			    "diabled" : { //li 태그에 <li data-jstree='{"opened":true, "type":"demo"}'>같은 느낌으로 type이 부여되야함.
			      "icon" : "glyphicon glyphicon-er"
			    },
			    "file" : { //type 부여는 li_attr컬럼에 '{"type":"demo"}' 형식으로 넣어야할 것이다.
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
					"url" : "/breadcrumbs/treeMapGet.node?tree_no="+tree_no,
			        "data" : function (node) {
// 			        	 $(this).each(node,function(index,item){
				        	console.log(JSON.stringify(node))
// 				        	console.log(JSON.stringify(node.parent))
// 				        	console.log(JSON.stringify(node.state))
// 				        	console.log(JSON.stringify(item))
// 				        	console.log(JSON.stringify($(this)))
				            return { "id" : node.id , "parent":node.parent, "state":node.state, "text":node.text, "li_attr":node.li_attr};
// 			        	 })
					},
					"dataType" : "json"
				},
				'themes': {
		            'name': 'proton',
		            'responsive': true
		        },
		        'check_callback' : function (operation, node, node_parent, node_position, more) {
		            // in case of 'rename_node' node_position is filled with the new node name
		            	console.log("operation="+operation);
		            	console.log("node_position="+node_position);
		            	console.log("node="+JSON.stringify(node));
		            	console.log("node_parent="+JSON.stringify(node_parent));
		            	console.log("more="+JSON.stringify(more));

		            if(operation==='create_node'){
		            	var depth =parseInt(node_parent.id.split("-")[1])+1;
		            	var newNodeId = tree_no+"-"+depth+"-"+"New node";
						console.log("node_position="+node_position)
		            	$.ajax({
		 					type:'post',
		 					url:'/breadcrumbs/NodeAdd.node',
		 					data: {"id" : newNodeId
		 						, "parent": node_parent.id , "state":"undetermined", "text":"New node", "li_attr":""}, 			
		 					/* data : query, */
		 					dataType:'text', 	 			
		 					success:function(result){
		 						console.log("result: " + result);
		 						console.log("now created node : "+node)
		 						$("#systemAlert").html("New Node is not allowed when same depth has same name.");
		 						$("#codeTypingArea").focus();//지우고 커서 옮기기
		 						
// 		 						var n = result.obj;
// 		 						n.attr("id", newNodeId);
// 		 						console.log("node="+JSON.stringify(node));
		 						
		 						if(result===true){
			 						return true;
				            	}else{
									return false;
				            	}
		 					},
		 					error: function (error) {
		 					    console.log('error; ' + eval(error));
								return false;
		 					}
		 				});  // ajax() end
		            }
		            if(operation==='rename_node'){
		            	
		            	var code_piece=$("#codeTypingArea").val();
		            	$.ajax({
		 					type:'post',
		 					url:'/breadcrumbs/NodeTextUpdate.node',
		 					data: {"id" : node.id, "text": node_position, "code_piece": code_piece, "node_parent_id" : node_parent.id}, 			
		 					/* data : query, */
		 					dataType:'json', 	 			
		 					success:function(result){
		 						console.log("rename result: " + result);
		 						$("#codeTypingArea").focus();//지우고 커서 옮기기
				            	if(result===true){
			 						return true;
				            	}else{
									return false;
				            	}
		 					},
		 					error: function (error) {
		 					    console.log('error; ' + JSON.stringify(eval(error)));
								return false;
		 					}
		 				});  // ajax() end
		 				
// 		 				location.reload();//일단 방법이 없다. 다시 refresh해서 반영 여부 넣어야 한다. 임시일 뿐 이거 빼고도 실시간으로 id가 갱신되어야 한다.
		            	return true;
		            }
		            if(operation==='delete_node'){
		            	
		            	return true;
		            }
		            if(operation==='move_node'){
		            	
		            	return true;
		            }
		            if(operation==='copy_node'){
		            	return true;
		            }
// 		            return operation === 'create_node' ? true : false;
		        }
			},
// 			'massload' : { //아직 활성화시킬 수 없다. 한꺼번에 로딩해야할 url을 다르게 하여 반응토록 만들 예정.
// 			    "url" : "/breadcrumbs/treeMapGet.node?tree_no="+tree_no,
// 			    "data" : function (ids) {
// 				return { "id" : ids.join(",") };
// 			    }
// 			},
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
		 var SelectedOneNode;
// 			    var selectedNode = $('#container').jstree().get_selected(true)[0];
		  $('#container').on("select_node.jstree", function (e, data) {
// 			    console.log(JSON.stringify(selectedNode));
			    console.log("The selected nodes are:");
			    console.log(data.selected);
// 			    console.log($('#container').jstree().get_selected(true)[0]);
			    SelectedOneNode= data.selected[0];
			    console.log("SelectedOneNode="+ SelectedOneNode);
			    
			    if(SelectedOneNode !== undefined){
				    var path = data.instance.get_path(data.node,'/');
				    console.log('Selected path: ' + path); 
				    
				    $.ajax({
	 					type:'post',
	 					url:'/breadcrumbs/getChoice.node',
	 					data: {"id" : SelectedOneNode, "path":path}, 			
	 					/* data : query, */
	 					dataType:'json', 	 			
	 					success:function(result){//Map형태로 리턴.
	 						console.log("result.choice : "+ result.choice);
	 					
// 	 						request.setRequestHeader("code_piece_list", result.choice);
	 						console.log("result.code_piece_list : "+ JSON.stringify(result.code_piece_list));
	 						
// 	 						request.setRequestHeader("code_piece_list_cnt", result.choice);
	 						console.log("result.code_piece_list_cnt : "+ JSON.stringify(result.code_piece_list_cnt));
	
	//  						$("#systemAlert").val("New Node라는 이름으로 내버려 두면 같은 depth의 노드에 New Node라는 이름의 디렉토리는 저장되지 않습니다.");
	 						$("#codeTypingArea").val(result.choice).focus();//지우고 커서 옮기기
	 						$("#accumlatedCodes").empty();//지운뒤 추가하는 방식으로 간다.

// 	 						var obj = JSON.parse(result);	
	 						var output='';
	 						for(var i = 0; i < result.code_piece_list.length; i++) {
	 							output+= "<p>" +result.code_piece_list[i] + "</p>";
	 						}
	 						console.log(output);
	 						$("#accumlatedCodes").append(output); // 코드를 추가.
	 						return true;
	 					},
	 					error: function (error) {
	 					    alert('error; ' + eval(error));
							return false;
	 					}
	 				});  // ajax() end
			    }
// 			    console.log($("#container").jstree(true).get_selected('full', true) );
		  });
		  
		  $('#container').on('rename_node.jstree', function (node,obj) {
			  console.log("rename_node.jstree");
          	  console.log("node="+JSON.stringify(node));
          	  console.log("node="+JSON.stringify(obj));
		  });

	</script>
</body>
</html>
