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

	<h1>FILE LIST</h1>
	ROOT DIR = ${root }<br>
	<hr>
	<c:forEach var="sub_dir" items="${root.listFiles() }">
	SUB DIRECTORY = ${sub_dir }
	
	<hr>
	<c:forEach var="file_path" items="${sub_dir.listFiles() }">
		<a href="javascript:void(0)" data-file="${file_path }" class="download-btn">
				FILEPATH = ${file_path }	
		</a>
	<hr>
	
		</c:forEach>
		
	</c:forEach>	
	
<script >
	const projectPath = '${pageContext.request.contextPath}';
	const els = document.querySelectorAll('.download-btn');
	els.forEach(el=>{
		el.addEventListener('click',function(e){		//클릭했을 때 반응
			console.log('clicked...',e.target);
			const t = e.target;
			const filePath = t.getAttribute("data-file");
			console.log(filePath);
			location.href=projectPath+"/file/download?filepath="+ encodeURIComponent(filePath);
		})
		
	})	
</script>	

</body>
</html>