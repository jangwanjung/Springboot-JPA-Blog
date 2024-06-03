<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="#" method="post">    
		<div class="form-group">
			<label for="Username">Username</label>
			<input type="username" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="Password">Password</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> 
			<input name="remember" class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
		<button id="btn-login" class="btn btn-primary">·Î±×ÀÎ</button>
	</form>
</div>
<%@ include file="../layout/footer.jsp"%>

