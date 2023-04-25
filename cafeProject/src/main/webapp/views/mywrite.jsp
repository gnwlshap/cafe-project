<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"></jsp:include>
</head>
<body>
	<c:set var="userId" value="${sessionScope.log.userId}" />
	<c:if test="${empty requestScope.list}">
		<c:redirect url="/service?command=mywritelist&userId=${userId}" />
	</c:if>

	<section>
		<div class="banner">
			<h2>내 작성글</h2>		
		</div>
		<table class="mywrite-table">
			<tr class="division">
				<td>제목</td>
				<td>작성 날짜</td>
			</tr>
			<c:forEach var="myReview" items="${list}">
				<c:set var="url"
					value="${'reviewdetail?&review_number='}${myReview.reviewNumber}" />
				<tr>
					<td><a href="${url}">${myReview.reviewTitle}</a></td>
					<td>${myReview.reviewPostDate}</td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>