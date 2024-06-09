<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/action_page.php"> 
	<!-- 비밀번호와 이메일만 받아서는 무엇을 수정해야하는지알수없다 그러니 히든타입으로 id를 받자 -->
		<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="Username">Username</label> 
			<input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
		</div>
		<div class="form-group">   
			<label for="Password">Password</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group">
			<label for="Email">Email</label> 
			<input type="email" value="${principal.user.email }" class="form-control" placeholder="Enter email" id="email">
		</div>
		
	</form>
	<button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>

<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>

