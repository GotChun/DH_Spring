<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>join PAGE</h1>
	<form action="${pageContext.request.contextPath }/join" method="post">
		<div>
			<label>USERNAME : </label>
			<input type="text" name="username">
		</div>
		<div>
			<label>PASSWORD : </label>
			<input type="password" name="password">
		</div>
		<div>
			<input type="submit" value="LOGIN">
		</div>
	
		<input type="hidden" name="_csrf" value="${_csrf.token}">
		
	</form>
</body>
</html>