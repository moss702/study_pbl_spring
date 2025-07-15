<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/head.jsp" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">
	<input name="id" value="sae0">
	<input type="password" name="pw" value="1234">
	<button>로그인</button>
	<p class="text-danger">${msg}</p>
</form>
</body>
</html>