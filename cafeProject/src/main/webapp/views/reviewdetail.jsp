<%@page import="user.User"%>
<%@page import="review.Review"%>
<%@page import="review.controller.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
</head>
<body>
	<c:set var="reviewNumber" value="${param.review_number}" />
	<c:if test="${empty review}">
		<c:redirect
			url="/service?command=readreview&review_number=${reviewNumber}" />
	</c:if>
	<c:set var="updateReview"
		value="location.href='updatereview?review_number=${reviewNumber}'" />
	<c:set var="deleteReview"
		value="location.href='../service?command=deletereview&review_number=${reviewNumber}'" />

	<section>
		<input type="hidden" id="review_number" name="review_number"
			value="${reviewNumber}">
		<c:if test="${not empty sessionScope.log.userId}">
			<input type="hidden" id="logginId" name="logginId"
				value="${sessionScope.log.userId}">
		</c:if>
		<div class="review-banner"></div>
		<div class="subject">
			<h2>이 용 후 기</h2>
		</div>
		<div class="review-content">
			<div class="reviewdetail-title">${review.reviewTitle}
				<div class="edit-button">
					<c:if
						test="${sessionScope.log.userId eq board.userId || sessionScope.log.userId eq 'composecoffee'}">
						<input type="button" value="수정" class="notice-btn"
							onclick="${updateReview}">
						<input type="button" value="삭제" class="notice-btn"
							onclick="${deleteReview}">
					</c:if>
				</div>
			</div>
			<ul class="reviewdetail">
				<li>${review.reviewPostDate}</li>
				<li>조회수 : ${review.reviewViewCount}</li>
				<li class="reviewdetail-right">${review.userId}</li>
			</ul>
			<div class="reviewdetail-content">
				<p>${review.reviewContent}</p>
			</div>
		</div>
		<div class="comment">
			<h4>Comment</h4>
			<table id="comment"></table>
			<c:if test="${not empty sessionScope.log.userId}">
				<div class="comment-write">
				</div>
			</c:if>
		</div>
	</section>
	<script src="../resources/searchcomment.js"></script>
	<jsp:include page="/footer" />
</body>
</html>