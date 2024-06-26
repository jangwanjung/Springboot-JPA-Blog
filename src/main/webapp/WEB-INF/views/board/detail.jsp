<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-16"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<!-- onclick="history.back()"을 사용하면 눌렷을때 이전페이지로 돌아갈수있다 -->
	<butten class="btn btn-secondary" onclick="history.back()">돌아가기</butten>
	<c:if test="${board.user.id == principal.user.id}">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<butten id="btn-delete" class="btn btn-danger">삭제</butten>
	</c:if>
	<br> <br>
	<div>
		글 번호 : <span id="id"><i>${board.id}</i></span> 작성자 : <span><i>${board.user.username} </i></span>

	</div>
	<br>
	<div>
		<h3>${board.title}</h3>
	</div>
	<hr>
	<div>
		<div>${board.content}</div>
	</div>
	<hr>

</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

