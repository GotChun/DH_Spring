<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>FILE UPLOAD</h1>
	
	<h2>단일 파일 전송 - 동기방식</h2>
	<form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data">
		<input type="file"  name="file" />
		<input type="submit" value="단일파일전송" />
 	</form>
 	<hr>
 		
	<h2>다중 파일 전송 - 동기방식</h2>
	<form action="${pageContext.request.contextPath}/file/multiUpload" method="post" enctype="multipart/form-data">
		<input type="file"  name="files" multiple/>
		<input type="submit" value="단일파일전송" />
 	</form>
	
	<hr>
	
	<h2>다중 파일 전송(DTO) - 동기방식</h2>
	<form action="${pageContext.request.contextPath}/file/multiUpload_dto" method="post" enctype="multipart/form-data">
		<input type="text" name="id" />
		<input type="text" name="category" />
		<input type="text" name="price" />
		<input type="file"  name="files" multiple/>
		<input type="submit" value="단일파일전송" />
 	</form>	
 	
 	<hr>
 	
 	<style>
 		#d4{
 			width:350px; height:350px; border : 1px solid;
 		}
 	</style>
 	
 	<div id="d4"></div><a href="javascript:void(0)" class="image-btn">전송</a>
 	
 	<!-- AXIOS CDN -->
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.7.7/axios.min.js" integrity="sha512-DdX/YwF5e41Ok+AI81HI8f5/5UsoxCVT9GKYZRIzpLxb8Twz4ZwPPX+jQMwMhNQ9b5+zDEefc+dcvQoPWGNZ3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
 	<script>
 	
			const formData = new FormData();
		    const d4tg = document.querySelector('#d4');
		    // image.addEventListener('dragstart',(e)=>{
		    //     console.log("dragstart",e)
		    // })
		
		    // image.addEventListener('dragend',(e)=>{
		    //     console.log("dragend",e)
		    // })
		
		
		    d4tg.addEventListener('dragover',(e)=>{
		        console.log("dragover",e)
		        e.preventDefault();    
		    })
		    d4tg.addEventListener('drop',(e)=>{
		        console.log("drop",e)
		        console.log("drop",e.dataTransfer)
		        console.log("drop",e.dataTransfer.files)
		        console.log("drop",e.dataTransfer.files[0])
		        const file = e.dataTransfer.files[0];
		
		        if(file.type.startsWith("image/")){
		            console.log("IMAGE파일..")
		            
		    		//기존 img 태그 삭제
		    		while(d4tg.firstChild){
		    			d4tg.removeChild(d4tg.firstChild);
		    		}
		            
		            const imgTg=document.createElement('img')
		            imgTg.src=URL.createObjectURL(file)
		            imgTg.setAttribute("style","width:200px;height:200px")
		            d4tg.appendChild(imgTg)
		
		            formData.append("file",file);
		        }
		        e.preventDefault();
		
		    })
		    
		    
		    //이미지 업로드 버튼 클릭 이벤트
		    const path='${pageContext.request.contextPath}';
		    const imageBtn = document.querySelector('.image-btn');
		    imageBtn.addEventListener("click",function(e){
				axios
				.post(
					path+'/file/upload' ,
					formData, 
					{ headers:{'Content-Type' :'multipart/form-data'} }
				)
				.then(resp=>{ console.log(resp); })
				.catch(err=>{ console.log(err); } )
				
				
			});
		    
 	
 	</script>
	
</body>
</html>