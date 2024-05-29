<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form> 
		<div class="form-group">
			<label for="Email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		<div class="form-group">
			<label for="Password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
		
	</form>
	<button type="btn-login" class="btn btn-primary">·Î±×ÀÎ</button>
</div>
<script src="/blog/js/user.js"></script> 
<%@ include file="../layout/footer.jsp"%>

