<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>REDIRECT RESULT	</h1>
	/param/redirect r1 :  ${param.r1 }<br>
	/param/redirect r2 :  ${param.r2 }<br>   <!-- 쿼리스트링 파라미터값 불러옴 -->
	
	
	/param/redirect r1 flush:  ${r1_flush }<br>
	/param/redirect r2 flush:  ${r2_flush }<br>  <!-- 세션값 불러옴 -->
</body>
</html>