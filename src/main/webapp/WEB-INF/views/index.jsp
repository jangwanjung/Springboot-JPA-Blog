<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-16"%>
<%@ include file="layout/header.jsp"%>
<div class="container">
<!-- items에 ${boards}를 넣으면 BoardController에 있는 boards를 받아온다 -->
<!-- boards는 리스트니깐 java와 똑같이 리스트가 전부돌때동안 for문이 돈다 -->
	<c:forEach var="board" items="${boards.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="#" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>

</div>
<%@ include file="layout/footer.jsp"%>

	