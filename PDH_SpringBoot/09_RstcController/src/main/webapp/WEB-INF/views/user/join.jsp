<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<h1>MEMO PAGE</h1>
			${Memo1}

		<form action="${pageContext.request.contextPath}/user/join" method="post">
		<div>
			<div>
			<label>username : </label><span>${username }</span> <br>
				<input name="username"/>
			</div>

			<div>
			<label>password : </label><span>${password }</span> <br>
			<textarea name="password" > </textarea>
			</div>

			<div>
			<label>role : </label><span>${role }</span> <br>
				<input name="role"/>
			</div>


			<div>
				<input type="submit" value="가입">
			</div>

		</div>
		</form>

</body>
</html>