<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Check or Modify Essay</title>
</head>
<body>

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
		<input type="button" value="提交" id="submitButton" /> <a
			href="http://localhost:8080/"><input type="button" value="返回"></input></a>
	</p>
	<input type="hidden" name="id" id="id" value="${id }">


	<script language="JavaScript">
		var xmlHttp;
		var id = document.getElementById('id').value;
		var obj;
		function createXMLHttp() {
			if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		function showEssay() {
			createXMLHttp();
			xmlHttp.open("get", "http://localhost:8080/essaySingle/" + id);
			xmlHttp.onreadystatechange = showMsgCallback;
			xmlHttp.send(null);
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

		$(document).ready(function() {
			$("#submitButton").click(function() {
				var data = {
					"title" : $("input[name='title']").val(),
					"author" : $("input[name='author']").val(),
					"date" : $("input[name='date']").val(),
					"content" : $("textarea[name='content']").val()
				};

				$.ajax({
					type : 'POST',
					url : net + 'essay',
					data : JSON.stringify(data),
					success : function(data) {
						alert('data: ' + data);
					},
					contentType : "application/json; charset=UTF-8",
					dataType : 'json',
					success : function(data, status) {
						window.location.href = net;
					}
				});

			});
		});
	</script>
</body>
</html>