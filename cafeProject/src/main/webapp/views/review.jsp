<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
</head>
<body>
	<section class="review">
		<div class="review-banner"></div>
		<div class="subject"><h2>이 용 후 기</h2></div>
		<table class="review-table" id="review-board"></table>
		<div id="page-box"></div>
		
		<div class="search">
			<select name="category" class="category">
				<option value="1">제목</option>
				<option value="2">내용</option>
				<option value="3">작성자</option>
				<option value="4">제목 + 내용</option>
			</select> 
			<input type="text" name="keyword">
			<input type="submit" value="검색" onclick="searchReview(1)">
		</div>
	</section>
	<script src="../resources/review.js"></script>
	<jsp:include page="/footer" />
</body>
</html>