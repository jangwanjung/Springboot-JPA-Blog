<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<!DOCTYPE html>
<%--실험1 --%>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%-- js로 넘길려면 jquery script가 필요하다 --%>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/blog">JangWanJung Blog</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<%-- <c:choose>
				<!-- if문법이다 -->
				<c:when test="${empty sessionScope.principal}">
					<!-- 만약에 principa(세션)이 비어있으면일때실행 -->
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/blog/user/loginForm">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user/joinForm">회원가입</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<!-- 만약에 principa(세션)이비어있으면 실행 -->
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="/blog/board/writeForm">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user/userForm">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="/blog/user/logout">로그아웃</a></li>
					</ul>
				</c:otherwise>
			</c:choose> --%>




		</div>
	</nav>
	<br />