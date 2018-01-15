package com.breadcrumbs.breadcrumbs.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.breadcrumbs.breadcrumbs.dto.MemberTreeRelationDto;
import com.breadcrumbs.breadcrumbs.dto.NodeDto;
import com.breadcrumbs.breadcrumbs.dto.NodeDto2;
import com.breadcrumbs.breadcrumbs.dto.TypeDto;
import com.breadcrumbs.breadcrumbs.dto.UseraccountDto;
import com.breadcrumbs.breadcrumbs.node.service.NodeAction;



@Controller
@SessionAttributes("useraccount")
public class NodeController {
	
	@Autowired
	private NodeAction nodeAction;
	
//	@RequestMapping("/myTree.node")
//	public String myTreeAction() {
//		System.out.println("goMyTree");
//		return "tree/myTree";
//	}
	
	@RequestMapping("/makeTree.node")
	public String noticeWriteAction() {//트리 새로 만드는 페이지로 가는 경로 지정
		System.out.println("goMakeTree");
		return "tree/makeTree";
	}

	@RequestMapping("/treeMapView.node")
	public String treeMapView() {
		return "tree/treeMap";
	}
	
	@RequestMapping("/treeMain.node")
	public String treeMainView(@RequestParam("tree_no") String tree_no, Model model) {//사용자가 만든 트리 관리하는 페이지로 가는 경로 지정
		System.out.println("go treeMainView tree_no"+tree_no);
		
		model.addAttribute( "tree_no", tree_no);
		return "tree/treeMain";
	}
	
	public List<NodeDto2> nodeListConvertToNodeDto2List( List<NodeDto> node) {//사용자가 만든 트리 관리하는 페이지로 가는 경로 지정
		List<NodeDto2> nodeListForJson = new ArrayList<NodeDto2>();
		for (NodeDto n : node) {
			NodeDto2 n2=new NodeDto2();
			n2.setId(n.getId());
			n2.setParent((n.getParent() ));
			n2.setState((n.getState()));
			n2.setText( (n.getText()));

//			TypeDto t = new TypeDto();
			if(n.getLi_attr() != null) {
				String[] arr = n.getLi_attr().split(":");
				n2.setType(arr[1]);
			}else {
				n2.setType("default");
			}

			
			nodeListForJson.add(n2);
		}
		return nodeListForJson;
	}
	
	@RequestMapping("/treeMapGet.node")
	@ResponseBody
	public List<NodeDto2> treeMapGet(@RequestParam("tree_no") String tree_no,
			@RequestParam("id") String id,
			HttpServletResponse response, Model model) {//사용자가 만든 트리 관리하는 페이지로 가는 경로 지정
		
		System.out.println("id="+id);//실상 id의 하위 노드를 불러오게 된다.
		System.out.println("tree_no="+tree_no);//최초 루트노드 불러올때 기준삼으려고.
		List<NodeDto> nodeList = new ArrayList<NodeDto>();
		if(id.equals("#")) {
			nodeList = this.nodeAction.getRootNode(tree_no);//일단 다 끌고 오게 해보자. jstree는 기본 jstree형식이 parent같은건 잘 지원하지 않는 모양이다.
			System.out.println("getRootNode=\n"+nodeList);
			return nodeListConvertToNodeDto2List(nodeList);
		}else {// 현재 데이터베이스 구조로는 그냥 일괄로 부르는 방법 밖에 없다. 이하 구문은 무의미.
//			nodeList = this.nodeAction.getRootNode(tree_no);
			nodeList = this.nodeAction.getNodeList(id) ;
			System.out.println("getNodeList=\n"+nodeList);
			return nodeListConvertToNodeDto2List(nodeList);
		}
//		model.addAttribute( "nodeList", nodeList);
	}
	
	@RequestMapping("/checkRecommendCategory.node")
	@ResponseBody
//	public @ResponseBody List checkRecommendCategory(@RequestParam("value") String value,HttpServletResponse response) throws Exception {
	public List<String> checkRecommendCategory(@RequestParam("value") String value,
			HttpServletResponse response) throws Exception {
//		PrintWriter out = response.getWriter();
		List<String> result = this.nodeAction.getRecommendCategoryList(value);
		System.out.println("result="+result);
//		out.println(result);
		return result;
	}
	
	@RequestMapping("/makeRootNode.node")
	public String makeRootNode(MultipartHttpServletRequest request, Model model) {
		System.out.println("mkRootnode");
		//할일 : m_t_relation 테이블에 트리 시퀀스+1, 추천수는 0, 카테고리, 세션에 있는 멤버 객체(아마도 useraccount)이메일 넣는다.
		//       t_n_relation에는 루트 노드로 하나 넣는다. 30자가 넘지 않도로 해야하는데 seq는 27자 까지가 한계. 절충해서 대충 20자 정도만 넣을 수 있게하자.
		int tree_no=this.nodeAction.makeTreeNo(request);//루트 노드 파트만 건든다.
		//    node에도 해당 root 노드 추가 시킨다. id, parent, state, text, li_attr 등. 선택지인 text는 일단 비워둔다.
		//    비슷하게 파일노드도 하나 넣는다. 이때 node의 li_attr에 type을 file로 정해둘 것.
		//   새로운 카테고리의 경우 추가하는 메소드가 필요하다.
		model.addAttribute( "tree_no", tree_no);
//		return "tree/treeMain";
		return "redirect:/treeMain.node?tree_no="+tree_no;
	}
	
	@RequestMapping("/treeMap.node")
	public String treeMapView(@RequestParam("id") String id) {//사용자가 만든 트리 관리하는 페이지로 가는 경로 지정
		return "tree/treeMap";
	}
	
	
	//노드 추가-노드와 연동된 코드 추가와는 다르다.
	@RequestMapping("/NodeAdd.node")
	@ResponseBody //이게 있어야 json으로 반환되는데 이건 숫자만 반환하므로 없어도 됨.
	public boolean NodeAdd(@ModelAttribute NodeDto node,
			HttpServletResponse response) throws Exception {
		System.out.println("insert this node"+node);
		boolean result = this.nodeAction.insertNode(node);//루트 노드 파트만 건든다.

		return result;
	}

	@RequestMapping("/executeCode.node")
	@ResponseBody
	public List<String> noticeWriteView(@RequestParam("codes[]") List<String> codes,
			HttpServletResponse response) throws REXPMismatchException, REngineException {
		System.out.println("executeCode"+codes);
		List<String> result=this.nodeAction.executeCode(codes);
		return result;
	}
	
	@RequestMapping("/myTree.node")//사용자가 만든 트리 관리하는 페이지로 가는 경로 지정
//	@ResponseBody
	public String myTreeList(HttpSession session, Model model) {//사용자가 만든 트리 관리하는 페이지로 가는 경로 지정
		UseraccountDto member = (UseraccountDto) session.getAttribute("member");
		System.out.println( member.getEmail());
		List<MemberTreeRelationDto> treeList = this.nodeAction.getTreeList( member.getEmail() );
		model.addAttribute( "treeList", treeList );
		return "tree/myTree";
	}
}
