<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rule</title>

<script type="text/javascript"> 
var id;
$(document).ready(function () { 
  $("#selectCategory").bind("change",function(){ 
    if($(this).val()==0){
		alert("请选择");
		$("#confirm").empty()
		$("#selectAttribute").empty()
    } else{
		$.getJSON(net + 'categoryAttributes/' + $("#selectCategory").val(),function(data){ 
			$("#selectAttribute").empty()
			$("#selectAttribute").append("<h2>规则:</h2>")
			$("#confirm").empty()
			$.each(data.data.categoryAttributeList,function(index,value){ 
				if (value == 0) {
					$("#selectAttribute").append('<p><input type="checkbox" name="attributes" value="' + index + '" />' + index + '</p>');
				} else {
					$("#selectAttribute").append('<p><input type="checkbox" name="attributes" value="' + index + '" checked/>' + index + '</p>');
				}
			}); 
			$("#showId").empty()
			$("#showId").append("<input type='hidden' name='id' id='id' value='" + data.data.id + "'/>")
			id = data.data.id;
			
			$("#confirm").append('<input type="button" value="submit" id="confirmButton" onclick="myclick()" />')
		});
		
	}
  }); 
});

//function for sending data to server
function myclick() {
	var array = new Array();
	$("input[name='attributes']:checked").each(function () {
		array.push(this.value);
    });
	
	var data = {
					"category" : $("#selectCategory").val(),
					"attributes" : array,
					"id":id
				};
				$.ajax({
					type : 'POST',
					url : net + 'rule',
					data : JSON.stringify(data),
					success : function(data) {
						alert('data: ' + data);
					},
					contentType : "application/json; charset=UTF-8",
					dataType : 'json',
					success : function(data, status) {
						window.location.href = "./index.html";
					}
				});
};
window.onload = windowOnload;

//the function active when the page load
function windowOnload() {  
	$.getJSON(net + 'categoryName',function(data){ 
		$.each(data.data.categoryNameList,function(index,value){ 
			$("#selectCategory").append('<option>'+value + '</option>');
		}); 
	}); 
} 
</script>
</head>
<body >
<h1 align="center">新增或修改规则</h1>
<h2>分类：</h2>
<select id="selectCategory">
  <option value=0>-请选择-</option>  
</select>

<p id="selectAttribute"></p>
<p id="confirm"></p>
<p id="showId"></p>
<p align="center"><a href="http://localhost:8080/partItem"><input type="button" value="返回" align="center"></input></a></p>
</body>
</html>