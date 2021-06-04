<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.Tashizan" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
ログインした社員名:<%=bean.getShainName() %>


<form  method="POST" action="ShainListServlet">
社員一覧を表示する<br>
	<input type="submit" value="社員一覧表示">
</form>


</body>
</html>