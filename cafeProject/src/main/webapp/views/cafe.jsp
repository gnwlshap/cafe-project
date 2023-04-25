<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
</head>
<body>
	<section>
		<div class="cafe-banner"></div>
		<div class="subject"><h2>매 장</h2></div>
		<div class="favorite-cafe">
			<h2>즐겨찾는 매장</h2>
			<table id="favorite-cafe-table" class="cafe-table">
			</table>
		</div>
		<div class="search">
			<select name="category" class="category">
				<option value="1">매장명</option>
				<option value="2">매장 주소</option>
			</select>
			<input type="text" name="keyword" placeholder="지역 or 매장 이름을 입력해 주세요.">
			<input type="submit" value="검색" onclick="searchCafe()">
		</div>
		<div class="all-cafe">
			<h2>전체 매장</h2>
			<table id="cafe-table" class="cafe-table">
				<tr>
					<th></th>
					<th>매장명</th>
					<th>매장 주소</th>
					<th>매장 번호</th>
				</tr>
			</table>
		</div>
	</section>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="../resources/cafe.js"></script>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>