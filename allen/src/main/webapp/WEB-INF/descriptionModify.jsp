<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Description</title>
<script type="text/javascript"> 
var id;
var atts;
$(document).ready(function () { 
  $("#selectCategory").bind("change",function(){ 
    if($(this).val()==0){
		alert("è¯·éæ©");
		$("#selectDescription").empty();
		$("#description").empty();
		$("#partItem").empty();
		$("#confirm").empty();
    } else{
		$.getJSON(net + 'descriptionName/' + $("#selectCategory").val(),function(data){
			$("#selectDescription").empty();
			$("#description").empty();
			$("#partItem").empty();
			$("#confirm").empty();
			$("#selectDescription").append('<option value=0>-请选择-</option>');
			$.each(data.data.descriptionNameList,function(index,value){ 
				$("#selectDescription").append('<option>'+value + '</option>');
			}); 
		});		
	}
  }); 
});

$(document).ready(function () { 
  $("#selectDescription").bind("change",function(){ 
    if($(this).val()==0){
		alert("请选择");
		$("#description").empty();
		$("#partItem").empty();
		$("#confirm").empty();
		
    } else{
		$.getJSON(net + 'description/' + $("#selectCategory").val() + '/' + $("#selectDescription").val(),function(data){
			$("#description").empty();
			$("#partItem").empty();
			$("#confirm").empty();
			
			$("#description").append('<h2>规格描述：</h2>');
			$("#partItem").append('<h2>关联物料：</h2>');
			
			id = data.data.description.id;
			atts=[];
			$.each(data.data.description.attribute,function(key,value){ 
				atts.push(value.key);
				$("#description").append(value.key + ": " + '<input type="text" id="' + value.key +'" value="' + value.value+'"/>&nbsp;&nbsp;');
			}); 
			$.each(data.data.description.partItem,function(key,value){ 
				if (value == 0) {
					$("#partItem").append('<p><input type="checkbox" name="partItems" value="' + key + '" />' + key + '</p>');
				} else {
					$("#partItem").append('<p><input type="checkbox" name="partItems" value="' + key + '" checked/>' + key + '</p>');
				}
			});
			
			$("#confirm").append('<input type="button" value="submit" id="confirmButton" onclick="myclick()" />')
			
			$("#showId").empty()
			$("#showId").append("<input type='hidden' name='id' id='id' value='" + id + "'/>")
		});
		
	}
  }); 
});


//function for sending data to server
function myclick() {
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
					"partItem" : array,
					"attribute":array2,
					"descriptionName":$("#selectDescription").val(),
					"id": id
				};

	$.ajax({
					type : 'POST',
					url : net + 'description',
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
<h1 align="center">修改规格描述</h1>
<h2>分类：</h2>
<select id="selectCategory">
  <option value=0>-请选择-</option>  
</select>

<h2>规格描述名称：</h2>
<select id="selectDescription">
  
</select>

<p id="description"></p>

<p id="partItem"></p>
<p id="confirm"></p>
<p id="showId"></p>
<p align="center"><a href="http://localhost:8080/partItem"><input type="button" value="返回" align="center"></input></a></p>
</body>
</html>