<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/loginProc" method="post">    
		<div class="form-group">
			<label for="Username">Username</label>
			<input type="username" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="Password">Password</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<button id="btn-login" class="btn btn-primary">·Î±×ÀÎ</button>
		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=d27d743084ed545af0a2180cdb8c3af7&redirect_uri=http://localhost:8000/auth/kakao/callback"><img height=41px src=/image/kakao_login_button.png></a>
	</form>
</div>
<%@ include file="../layout/footer.jsp"%>

