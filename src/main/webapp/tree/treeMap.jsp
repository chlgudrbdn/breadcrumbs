<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>jstree Map application</title>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jstree/3.3.3/themes/default/style.min.css" /> -->
<link rel="stylesheet" href="dist/themes/default/style.min.css" />
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script> -->
<script src="dist/jstree.min.js"></script>

<script>

</script>

</head>

<body>
<div id="container"></div>
<script>
$(function() {
  $('#container').jstree({
    'core' : {
      'data' : {
        "url" : "//www.jstree.com/fiddle/?lazy",
        "data" : function (node) {
          return { "id" : node.id };
        }
      }
    }
  });
});
</script>

</body>
</html>
