<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Essay</title>
</head>
<body>
	<h1 align="center">文章列表</h1>

	<ul id="list">
		<!--<c:forEach var="essay" items="${essayList }">
			<li><h2><a href="http://localhost:8080/getEssay/${essay.id }">${essay.title }</a></h2></li>
		</c:forEach>-->
	</ul>
<center><a href="http://localhost:8080/createEssay"><input type="button" value="新建"></input></a></center>

<script language="JavaScript"> 
	var xmlHttp; 
	var obj;
	function createXMLHttp() { 
		if (window.XMLHttpRequest) { 
			xmlHttp = new XMLHttpRequest(); 
		} else { 
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP" ); 
		} 
	} 
	function showList() { 
		createXMLHttp(); 
		xmlHttp.open( "get" , "http://localhost:8080/essayList" ); 
		xmlHttp.onreadystatechange = showMsgCallback; 
		xmlHttp.send( null ); 
	} 
	function showMsgCallback() { 
		if (xmlHttp.readyState == 4) {  
	    	if (xmlHttp.status == 200) { 
	    		var text = xmlHttp.responseText;  
	    		obj = JSON.parse(text);
				var str = "";
				for (var i=0;i<obj.length;i++){ 
					str = str + "<li><h3><a href=\"http://localhost:8080/showEssay/"+ obj[i].id +"\">" + obj[i].title + "</a>  <a href=\"http://localhost:8080/deleteEssay/"+ obj[i].id +"\" style=\"display:block;text-align:right; float:right;\">删除</a></h3></li>"
				}
				document.getElementById('list').innerHTML = str;
    		} 
		} 
	} 
	window.onload = showList;
</script>
</body>
</html>