<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<style>
.wrapper {
	display: flex;
	justify-content: left;
}

.wrapper>div {
	width: 200px;
	height: 500px;
	border: 1px solid;
	margin: 15px;
	text-align: center;
	overflow: auto;
}

.wrapper>div.result {
	width: 300px;
}

.wrapper>div>h1 {
	border: 1px solid;
	margin-bottom: 15px;
}

.wrapper>div>div {
	border: 1px solid;
	margin: 5px;
	padding: 0px;
	opacity: .8;
}

.wrapper>div>div:hover {
	background-color: lightgray;
	opacity: 1;
}

input {
	width: 55px;
}
</style>
</head>
<body>
	<h1>REST TEST PAGE</h1>

	<div class="wrapper">

		<!--  -->
		<div class="xhr-block">
			<h1>XHR</h1>
			<div class="get">
				<h6>[ADD]동기 GET 요청</h6>
				<form method="get"
					action="${pageContext.request.contextPath}/add_get">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" />
					<button>전송</button>
				</form>
			</div>
			<div class="get">
				<h6>[ADD]비동기 GET 요청</h6>
				<form name="xhrAsyncGetForm" method="" action=""
					onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" />
					<button class="xhrAsyncGetBtn">전송</button>
				</form>
			</div>
			<div class="post">
				<h6>[ADD]비동기 POST 요청</h6>
				<form name="xhrAsyncPostForm" method="" action=""
					onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" />
					<button class="xhrAsyncPostBtn">전송</button>
				</form>
			</div>
			<div class="put">
				<h6>[UPDATE]비동기 PUT 요청</h6>
				<form name="xhrAsyncPutForm" method="" action=""
					onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" />
					<button class="xhrAsyncPutBtn">전송</button>
				</form>
			</div>
			<div class="petch">
				<h6>[UPDATE]비동기 PETCH 요청</h6>
				<form method="" action="" onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" />
					<button>전송</button>
				</form>
			</div>
			<div class="delete">
				<h6>[DELETE]비동기 DELETE 요청</h6>
				<form name="xhrAsyncDeleteForm" method="" action=""
					onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" />
					<button class="xhrAsyncDeleteBtn">전송</button>
				</form>
			</div>

		</div>

		<!--  -->
		<div class="ajax-block">
			<h1>AJAX</h1>
			<div></div>
		</div>

		<!--  -->
		<div class="fetch-block">
			<h1>FETCH</h1>
			<div></div>
		</div>


		<!--  -->
		<div class="axios-block">
			<h1>AXIOS</h1>
			<div class="get">
				<h6>[ADD]동기 POST 요청</h6>
				<form method="post"
					action="${pageContext.request.contextPath}/memo/add">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" /> <input name="writer" placeholder="text" />

					<button>전송</button>
				</form>
			</div>
			<div class="get">
				<h6>[ADD]비동기 GET 요청</h6>
				<form name="axiosAsyncGetForm" onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" /> <input name="writer" placeholder="text" />
					<button class="axiosAsyncGetBtn">전송</button>
				</form>
			</div>
			<div class="post">
				<h6>[ADD]비동기 POST 요청</h6>
				<form name="axiosAsyncPostForm" method="" action=""
					onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" /> <input name="writer" placeholder="text" />
					<button class="axiosAsyncPostBtn">전송</button>
				</form>
			</div>
			<div class="put">
				<h6>[UPDATE]비동기 PUT 요청</h6>
				<form name="axiosAsyncPutForm" method="" action=""
					onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" /> <input name="writer" placeholder="text" />
					<button class="axiosAsyncPutBtn">전송</button>
				</form>
			</div>
			<div class="petch">
				<h6>[UPDATE]비동기 PETCH 요청</h6>
				<form name="axiosAsyncPatchForm" method="" action=""
					onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" /> <input name="writer" placeholder="text" />
					<button class="axiosAsyncPatchBtn">전송</button>
				</form>
			</div>
			<div class="delete">
				<h6>[DELETE]비동기 DELETE 요청</h6>
				<form name="axiosAsyncDeleteForm" method="" action=""
					onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" />
					<button class="axiosAsyncDeleteBtn">전송</button>
				</form>
			</div>
			<div class="select">
				<h6>[SELECT]비동기 SELECT 요청</h6>
				<form name="axiosAsyncSelectForm" method="" action=""
					onsubmit="return false">
					<input name="id" placeholder="id" /> <input name="text"
						placeholder="text" />
					<button class="axiosAsyncSelectBtn">전송</button>
				</form>
			</div>
		</div>


		<!-- SELECT BLOCK -->
		<div class="result">
			<h1>RESULT</h1>
			<div class="body"></div>
		</div>
	</div>


	<!--  
		XHR
	-->
	<script>
	
	</script>


	<!-- 
		AJAX
	 -->


	<!-- AXIOS -->

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.7.7/axios.js"
		integrity="sha512-luUnkeG+uabiT2pZdi5ME5uADvq+FpDs2fK5V0nVXrHCND9F077fKaE9W//oJvGnWSmwQmCau63A6s3um1NZmg=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>


	<script>
		const projectPath = '${pageContext.request.contextPath}';
	
		const btn1 = document.querySelector('.axiosAsyncGetBtn');
		
		
		btn1.addEventListener('click',function(){
			const form1 = document.axiosAsyncGetForm;
			const id = form1.id.value;
			const text = form1.text.value;
			const writer = form1.writer.value;
			console.log(id,text,writer);
			console.log('clicked');
			
			
			axios.get(projectPath+'/memo/add_get',{params:{
				id : id,
				text : text,
				writer : writer
				
			}})
			.then( resp=>{
				console.log(resp);
				const resultBlock = document.querySelector('.result .body')
				while(resultBlock.firstChild){
					/* resultBlock.removeChild(resultBlock.firstChild); */
				}
				resultBlock.innerHTML = resp.data;
			})
			.catch( err=>{console.log(err); })
		})
		
		
		
		//비동기 POST
		const btn2 = document.querySelector('.axiosAsyncPostBtn');
		
		
		btn2.addEventListener('click',function(){
			const form1 = document.axiosAsyncPostForm;
			const id = form1.id.value;
			const text = form1.text.value;
			const writer = form1.writer.value;
			console.log(id,text,writer);
			console.log('clicked');
			const data = {id:id,text:text,writer:writer};
			const headers = {"Content-type":"application/json"};
			axios.post(projectPath+'/memo/add_post',data,{headers,})
			.then( resp=>{
				console.log(resp);
				const resultBlock = document.querySelector('.result .body')
				while(resultBlock.firstChild){
					 resultBlock.removeChild(resultBlock.firstChild); 
				}
				resultBlock.innerHTML = "비동기 POST...!" +resp.data;
			})
			.catch( err=>{console.log(err); })
		})
		
		const btn3 = document.querySelector('.axiosAsyncPutBtn');
		
		
		btn3.addEventListener('click',function(){
			const form1 = document.axiosAsyncPutForm;
			const id = form1.id.value;
			const text = form1.text.value;
			const writer = form1.writer.value;
			console.log(id,text,writer);
			console.log('clicked');
			const data = {id:id,text:text,writer:writer};
			const headers = {"Content-type":"application/json"};
			axios.put(projectPath+'/memo/put',data,{headers,})
			.then( resp=>{
				console.log(resp);
				const resultBlock = document.querySelector('.result .body')
				while(resultBlock.firstChild){
					 resultBlock.removeChild(resultBlock.firstChild);
				}
				resultBlock.innerHTML = "비동기 Put...!" +resp.data;
			})
			.catch( err=>{console.log(err); })
		})
		
		const btn4 = document.querySelector('.axiosAsyncPatchBtn');
		
		
		btn4.addEventListener('click',function(){
			const form1 = document.axiosAsyncPatchForm;
			const id = form1.id.value;
			const text = form1.text.value;
			const writer = form1.writer.value;
			console.log(id,text,writer);
			console.log('clicked');
			const data = {id:id,text:text,writer:writer};
			const headers = {"Content-type":"application/json"};
			axios.patch(projectPath+'/memo/patch',data,{headers,})
			.then( resp=>{
				console.log(resp);
				const resultBlock = document.querySelector('.result .body')
				while(resultBlock.firstChild){
					 resultBlock.removeChild(resultBlock.firstChild);
				}
				resultBlock.innerHTML = " 비동기 Patch...!" +resp.data;
			})
			.catch( err=>{console.log(err); })
		})
		
		const btn5 = document.querySelector('.axiosAsyncDeleteBtn');
		
		
		btn5.addEventListener('click',function(){
			const form1 = document.axiosAsyncDeleteForm;
			const id = form1.id.value;
			const text = form1.text.value;
			console.log(id,text);
			console.log('clicked');
			const data = {id:id,text:text};
			console.log(data);
			const headers = {"Content-type":"application/json"};
			axios.delete(projectPath+'/memo/delete',data,{headers,})
			.then( resp=>{
				console.log(resp);
				const resultBlock = document.querySelector('.result .body')
				while(resultBlock.firstChild){
					 resultBlock.removeChild(resultBlock.firstChild);
				}
				resultBlock.innerHTML = " 비동기 삭제요청...!" +resp.data;
			})
			.catch( err=>{console.log(err); })
		})

		
	</script>


	<script>
		//xhr 를 이용 비동기 요청
		
		//ajax를 이용 비동기 요청
		
		//fetch 를 이용 비동기 요청
		
		//axios 를 이용 비동기 요청
	
	</script>



</body>
</html>