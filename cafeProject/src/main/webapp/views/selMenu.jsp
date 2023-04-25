<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"></jsp:include>
</head>
<body>
	<section>
		<div class="cafe-banner"></div>
		<div class="subject"><h2>메 뉴</h2></div>
		<div id="cafe" data-value="${param.cafeCode}">
			<ul class="tabnav">
				<li><a href="#allMenu">전체메뉴</a></li>
				<li><a href="#coffee">커피</a></li>
				<li><a href="#dutch-coffee">더치커피</a></li>
				<li><a href="#juice">주스</a></li>
				<li><a href="#non-coffee">논커피</a></li>
				<li><a href="#smoothie">스무디</a></li>
				<li><a href="#tea">티</a></li>
			</ul>
				<div class="gap"></div>
			<div id="pop-container"></div>
			<div class="tabcontent"></div>
		</div>
	</section>
	<jsp:include page="/footer" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="../resources/order.js"></script>
</body>
</html>