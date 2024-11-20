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
	
	<h1>LOGIN PAGE</h1>
	<form action="${pageContext.request.contextPath }/login" method="post">
		<div>
			<label>USERNAME : </label>
			<input type="text" name="username">
		</div>
		<div>
			<label>PASSWORD : </label>
			<input type="password" name="password">
		</div>
		
		<div>
			<input type="checkbox" name="remember-me" id="remember-me">
			<label>로그인 상태 유지</label>
		</div>
		
		<div>
			<input type="submit" value="LOGIN">
		</div>
		<div>
			<a href="${pageContext.request.contextPath }/join">회원가입</a>
		</div>
	
		<input type="hidden" name="_csrf" value="${_csrf.token}">
		
	</form>
	${message }
</body>
</html>