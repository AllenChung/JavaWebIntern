<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Check or Modify Essay</title>
</head>
<body>
<form action="http://localhost:8080/postEssay" method="post">
		<h1 align="center">
			Title: <input type="text" name="title" id="title" />
		</h1>
		<h5 align="center">
			Author: <input type="text" name="author" id="author" />&nbsp;&nbsp;&nbsp;&nbsp;
			Date: <input type="text" name="date" id="date" />
		</h5>
		<p align="center">
			<textarea name="content" rows="30" cols="150" wrap="soft" id="content"></textarea>
		</p>
		<p align="center">
			<input type="submit" value="提交"/>
			<a href="http://localhost:8080/"><input type="button" value="返回"></input></a>
		</p>
	<input type="hidden" name="id" id="id" value="${id }"> 
</form>

<script language="JavaScript"> 
	var xmlHttp; 
	var id = document.getElementById('id').value;
	var obj;
	function createXMLHttp() { 
		if (window.XMLHttpRequest) { 
			xmlHttp = new XMLHttpRequest(); 
		} else { 
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP" ); 
		} 
	} 
	function showEssay() { 
		createXMLHttp(); 
		xmlHttp.open( "get" , "http://localhost:8080/essaySingle/" + id ); 
		xmlHttp.onreadystatechange = showMsgCallback; 
		xmlHttp.send( null ); 
	} 
	function showMsgCallback() { 
		if (xmlHttp.readyState == 4) { 
			if (xmlHttp.status == 200) { 
				var text = xmlHttp.responseText; 
				obj = JSON.parse(text);
				document.getElementById('title').value = obj.title;
				document.getElementById('author').value = obj.author;
				document.getElementById('date').value = obj.date;
				document.getElementById('content').value = obj.content;
			} 
		} 
	} 
	
	showEssay();
</script>
</body>
</html>