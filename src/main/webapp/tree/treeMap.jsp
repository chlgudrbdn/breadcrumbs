<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Spacetree - Tree Animation</title>
<!-- 출처 http://philogb.github.io/jit/index.html 와 https://github.com/philogb/jit -->
<!-- CSS Files -->
<link type="text/css" href="../Jit/Examples/css/base.css" rel="stylesheet" />
<link type="text/css" href="../Jit/Examples/css/Spacetree.css" rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src="../Jit/jit.js"></script>

<!-- Example File -->
<script language="javascript" type="text/javascript" src="../Jit/Examples/Spacetree/example1.js"></script>
</head>

<body onload="init();">
<div id="container">

<!-- <div id="left-container"> -->



<!-- <div id="id-list"></div> -->


<!-- <div style="text-align:center;"><a href="example1.js">See the Example Code</a></div>             -->
<!-- </div> -->

<div id="center-container">
    <div id="infovis"></div>    
</div>


<div id="left-container" >

<h4>Tree Orientation</h4>
<table>
    <tr>
        <td>
            <label for="r-left">Left </label>
        </td>
        <td>
            <input type="radio" id="r-left" name="orientation" checked="checked" value="left" />
        </td>
    </tr>
    <tr>
         <td>
            <label for="r-top">Top </label>
         </td>
         <td>
            <input type="radio" id="r-top" name="orientation" value="top" />
         </td>
    </tr>
    <tr>
         <td>
            <label for="r-bottom">Bottom </label>
          </td>
          <td>
            <input type="radio" id="r-bottom" name="orientation" value="bottom" />
          </td>
    </tr>
    <tr>
          <td>
            <label for="r-right">Right </label>
          </td> 
          <td> 
           <input type="radio" id="r-right" name="orientation" value="right" />
          </td>
    </tr>
</table>

<h4>Selection Mode</h4>
<table>
    <tr>
        <td>
            <label for="s-normal">Normal </label>
        </td>
        <td>
            <input type="radio" id="s-normal" name="selection" checked="checked" value="normal" />
        </td>
    </tr>
<!--     <tr> -->
<!--          <td> -->
<!--             <label for="s-root">Set as Root </label> -->
<!--          </td> -->
<!--          <td> -->
<!--             <input type="radio" id="s-root" name="selection" value="root" /> -->
<!--          </td> -->
<!--     </tr> -->
</table>

</div>
<div id="log"></div>
</div>
</body>
</html>
