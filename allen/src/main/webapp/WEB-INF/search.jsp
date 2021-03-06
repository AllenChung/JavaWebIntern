<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Description</title>

<script type="text/javascript"> 
var atts;
$(document).ready(function () { 
  $("#selectCategory").bind("change",function(){ 
    if($(this).val()==0){
		alert("请选择");
		$("#description").empty();
		$("#confirm").empty();
		$("#search").empty();
    } else{
		$.getJSON(net + 'description/' + $("#selectCategory").val(),function(data){
			$("#description").empty();
			$("#confirm").empty();
			$("#search").empty();
			$("#description").append('<h2>规格描述：</h2>');

			atts = data.data.description.attribute;
			$.each(data.data.description.attribute,function(index,value){ 
				$("#description").append(value + ": " + '<input type="text" id="' + value +'"/>&nbsp;&nbsp;');
			}); 

			$("#confirm").append('<input type="button" value="search" id="confirmButton" onclick="myclick()" />')
			$("#search").append('<h2>搜索结果：</h2>');
			$("#search").append('<textarea rows="30" cols="150" wrap="soft" id="content" />');
		});
		
	}
  }); 
});

//function for sending data to server
function myclick() {
	$("#content").empty();
	var array = new Array();
	$("input[name='partItems']:checked").each(function () {
		array.push(this.value);
    });
	var array2=[];
	$.each(atts,function(index,value){
		var array3={};
		array3['key']=value;
		array3['value']=$('#'+value).val();
		array2.push(array3);
	}); 
	var data = {
					"category" : $("#selectCategory").val(),
					"attribute":array2,
				};
	
	$.ajax({
					type : 'POST',
					url : net + 'search',
					data : JSON.stringify(data),
					success : function(data) {
						
					},
					contentType : "application/json; charset=UTF-8",
					dataType : 'json',
					success : function(data, status) {
						//$("#content").append('<ul>');
						$.each(data.data.searchResultList,function(index,value){ 
							$("#content").append('物料：' + value.name );
							$("#content").append('\n');
							$.each(value.attribute, function(key, value) {
								$("#content").append('&nbsp;&nbsp;' + key + ": " + value );
								$("#content").append('\n');
							});
							$("#content").append('\n\n');
						});
						//$("#content").append("</ul>");
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
<h1 align="center">搜索</h1>
<h2>分类：</h2>
<select id="selectCategory">
  <option value=0>-请选择-</option>  
</select>

<p id="description"></p>

<p id="confirm"></p>
<p id="search"></p>
<p align="center"><a href="http://localhost:8080/partItem"><input type="button" value="返回" align="center"></input></a></p>
</body>
</html>