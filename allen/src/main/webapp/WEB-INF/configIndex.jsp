<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">配置中心</h1>
	<table border="1" align="center">
		<tr>
			<td>key</td>
			<td>value</td>
			<td>action</td>
		</tr>
		<c:forEach var="config" items="${configList }">
			<tr>
				<form action="http://localhost:8080/configInfo" method="post">
				<th><input type="text" name="key" value="${config.key }" /></th>
				<th><input type="text" name="value" value="${config.value }" /></th>
				<th><input type="submit" value="提交" /></th>
				<input type="hidden" name="id" id="id" value="${config.id }">
				</form>
			</tr>
		</c:forEach>
		<tr>
			<form action="http://localhost:8080/configInfo" method="post">
				<th><input type="text" name="key" /></th>
				<th><input type="text" name="value"  /></th>
				<th><input type="submit" value="提交" /></th> 
			</form>
		</tr>
	</table>

</body>
</html>