<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-16"%>
<%@ include file="layout/header.jsp"%>
<div class="container">
	<!-- items에 ${boards}를 넣으면 BoardController에 있는 boards를 받아온다 -->
	<!-- boards는 리스트니깐 java와 똑같이 리스트가 전부돌때동안 for문이 돈다 -->
	<c:forEach var="board" items="${boards.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
	<!--  justify-content-center를 입력해주면 가운데로 정렬한다 부트스트랩의 문법이다	 -->
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${boards.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>
		<!-- 페이지를 다음장으로 넘기기위해서는 page타입에 무슨값이 무엇인지를 알아야한다  -->
	</ul>

</div>
<%@ include file="layout/footer.jsp"%>

