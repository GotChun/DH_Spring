<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>USER PAGE</h1>
	
	<form action="${pageContext.request.contextPath }/user/join" method="post">
		<div>
			<div>
			<label>userid : </label><span>${userid}</span> <br>
				<input name="userid" type="text"/>
			</div>	
			
			<div>
			<label>passowrd : </label><span>${password}</span> <br>
				<input name="password" type="password">
			</div>
				
			<div>
			<label>rePassword : </label><span>${rePassowrd}</span> <br>
				<input name="rePassword" type="text"/>
			</div>
				
			<div>
			<label>username : </label><span>${username}</span> <br>
				<input name="username" type="text"/>
			</div>
				
			<div>
			<label>phone : </label><span>${phone}</span> <br>
				<input name="phone" type="text"/>
			</div>
				
			<div>
			<label>zipcode : </label><span${zipcode}></span> <br>
				<input name="zipcode" type="text"/>
			</div>
				
			<div>
			<label>addr1 : </label><span>${addr1}</span> <br>
				<input name="addr1" type="text"/>
			</div>	
			
			<div>
			<label>addr2 : </label><span>${addr2}</span> <br>
				<input name="addr2" type="text"/>
			</div>	
			
			<div>
			<label>birthday : </label><span>${birthday}</span> <br>
				<input name="birthday" type="datetime-local"/>
			</div>	
			
			<div>
				<input type="submit" value="회원가입">
			</div>
			
		</div>
	
	</form>	

	
</body>
</html>