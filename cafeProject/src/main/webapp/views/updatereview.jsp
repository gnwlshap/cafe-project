<%@page import="user.User"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="review.Review"%>
<%@page import="review.controller.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"/>
<script type="text/javascript" src="../resources/write.js"></script>
</head>
<body>
<c:set var="reviewNumber" value="${param.review_number}" />
<c:if test="${requestScope.review == null}">
	<c:redirect url="/service?command=modifyreview&review_number=${reviewNumber}" />
</c:if>
	<section>
	<div class="banner"><h2>리뷰 수정</h2></div>
		<form method="post" action="../service">
			<input type="hidden" id="user_id" name="user_id" value="${sessionScope.log.userId}">
			<input type="hidden" id="review_number" name="review_number" value="${reviewNumber}">
			<input type="hidden" id="command" name="command" value="updatereview">
			<div>
				제목 : <input type="text" id="review_title" name="review_title" value="${review.reviewTitle}">
			</div>
			<div>${now}</div>
			<div>
				내용 : <br> <br>
				<textarea rows="20" cols="100" id="review_content"
					name="review_content" style="padding: 5px;">${review.reviewContent}</textarea>
			</div>
			<input type="button" value="수정하기" onclick="checkReviewWrite(form)">
		</form>
	</section>
	<jsp:include page="/footer"/>
</body>
</html>