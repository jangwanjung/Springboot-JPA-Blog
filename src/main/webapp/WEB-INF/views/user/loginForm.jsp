<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form> 
		<div class="form-group">
			<label for="Username">Username</label> <input type="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="Password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
		
	</form>
	<button id="btn-login" class="btn btn-primary">�α���</button>
</div>
<script src="/blog/js/user.js"></script> 
<%@ include file="../layout/footer.jsp"%>

