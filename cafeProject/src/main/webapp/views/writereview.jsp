<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
<script type="text/javascript" src="../resources/write.js"></script>
</head>
<body>
	<c:set var="orderCode" value="${param.order_code}" />
	<div class="banner"><h2>리뷰 작성</h2></div>
	<div class="writereview">
	<form method="post" action="../service">
		<input type="hidden" id="user_id" name="user_id" value="${sessionScope.log.userId}">
		<input type="hidden" id="order_code" name="order_code"value="${orderCode}">
		<input type="hidden" id="command" name="command" value="writereview">
		<div class="writereview-title">
			제목 : <input type="text" id="review_title" name="review_title">
		</div>
		<div class="writereview-date">
		</div>
		<div class="writereview-content">
			내용 : <br> <br>
			<textarea rows="20" cols="100" id="review_content"
				name="review_content" style="padding: 5px;"></textarea>
		</div>
		<input type="button" value="작성" onclick="checkReviewWrite(form)">
	</form>
	</div>
	<jsp:include page="/footer" />
</body>
</html>