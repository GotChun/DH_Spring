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
			
		<form action="${pageContext.request.contextPath}/memo/add" method="post">
		<div>
			<div>
			<label>id : </label><span>${id }</span> <br>
				<input name="id"/>
			</div>	
			
			<div>
			<label>text : </label><span>${text }</span> <br>
			<textarea name="text" > </textarea> 
			</div>
			
			<div>
			<label>writer : </label><span>${writer }</span> <br>
				<input name="writer"/>
			</div>
			
			<div>	
		 	<label>CreateAt </label><span>${createAt }</span> <br>
				<input type="datetime-local" name="createAt"/>
			</div> 
			
		 	<label>dateTest(customFormat) </label><span></span> <br>
				<input type="text" name="dateTest" placeholder="yyyyMMdd "/>
			</div> 
			
			<div>
				<input type="submit" value="메모쓰기">
			</div>
			
		</div>
		</form>
		
</body>
</html>