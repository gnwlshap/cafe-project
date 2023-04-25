<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
</head>
<body>
	<section>
		<div class="notice-banner"></div>
		<div>
			<div class="subject"><h2>공 지 사 항</h2></div>
		</div>
		<div class="notice-table">
			<c:if test="${sessionScope.log.userId eq 'composecoffee'}">
			</c:if>
			
			<table id="notice-board"></table>
				<input type="button" value="공지사항 작성" class="admin-write"
					onclick="location.href='writenotice'">
			<div id="page-box"></div>
		</div>
		<div class="search">
			<select name="category" class="category">
				<option value="1">제목</option>
				<option value="2">내용</option>
				<option value="3">제목 + 내용</option>
			</select>
			<input type="text" name="keyword">
			<input type="submit" value="검색" onclick="searchNotice(1)">
		</div>
	</section>
	<script src="../resources/notice.js"></script>
	<jsp:include page="/footer" />
</body>
</html>