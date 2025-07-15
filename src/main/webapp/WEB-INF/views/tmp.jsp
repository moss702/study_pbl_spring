<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
	<form method="post">
		<div class="item">
			<input type="text" name="uuid" placeholder="uuid">
			<input type="text" name="origin" placeholder="origin">
			<input type="text" name="image" placeholder="image">
			<input type="text" name="path" placeholder="path">
			<input type="text" name="odr" placeholder="odr">
		</div>
		<div class="item">
			<input type="text" name="uuid" placeholder="uuid">
			<input type="text" name="origin" placeholder="origin">
			<input type="text" name="image" placeholder="image">
			<input type="text" name="path" placeholder="path">
			<input type="text" name="odr" placeholder="odr">
		</div>
		<input type="hidden" name="encodedStr" value="">
		<button>전송</button>
	</form>
	
	<script>
	//전송 버튼 눌렀을때 위 정보들을 객체로
	$(function(){
		$("form").submit(function() {
			event.preventDefault();
			
			const data = [];
			
			$(".item").each(function() {
				const obj = {};
				$(this).find('input').each(function() {
					obj[$(this).attr("name")] = this.value;
				});
				data.push(obj);
			})
			$("[name='encodedStr']").val(JSON.stringify(data));
			this.submit();
/* 			console.log(data); */
/* 			console.log({uuid : this.uuid.value}); */
		})
	})
	</script>
</body>
</html>