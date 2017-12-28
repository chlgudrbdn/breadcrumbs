<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>js-filter</title>

<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

<link rel="stylesheet" href="./jsfiler.css">
<script src="./jsfiler.js"></script>
<link rel="stylesheet" href="./style.min.css">

<script>
$('#filer-demo').jsfiler({
	  /* 1 - right-click menu, 2 - icon menu, 3 - both */
	  menuMode: 1, 
	  /* path to tree and menu icons */
	  iconPath: 'icons/', 
	  /* no tree checkboxes */
	  checkbox: true, 
	  /* allow drag & drop */
	  canDrag: true, 
	  /* allow multiple roots */
	  rootSingle: false, 
	  /* allow leafs for root node */
	  rootLeaf: true, 
	  /* root parent id */
	  rootParent: -1, 
	  /* save opened/selected state */
	  saveState: true, 
	  /* open the node on: 1 - click, 2 - dblclick, 3 - both 04.2017 */
	  selectOpen: 1, 
	  /* knots deletion: 0 - empty only, 1 - +copied, 2 - all */
	  knotRemove: 2, 
	  /* duplicate child names: 2 - allow, 1 - case-sensitive, 0 - no */
	  nameDupl: 0, 
	  /* name trim patterm (leading & trailing spaces */
	  nameTrim: /^\s+|\s+$/g, 
	  /* don't vali<a href="https://www.jqueryscript.net/time-clock/">date</a> */
	  nameValidate: false, 
	  /* user authorization token */
	  userAuth: null, 
	  /* ajax request url */
	  urlAjax: 'ajax.php' 
});
</script>

</head>

<body>
<div id="filer-demo"></div>

</body>
</html>
