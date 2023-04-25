<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="shortcut icon" href="../resources/images/favicon.ico">
<title>컴포즈커피</title>
<link rel="stylesheet" href="resources/reset.css">
<link rel="stylesheet" href="resources/style.css">
</head>
<body>
	<header>
		<div class="menu_btn">
			<a href="#"><i class="fa-solid fa-bars" style="color: #403c3b;"></i>
			</a>
		</div>
		<div class="logo">
			<h1>
				<a href="/"><img src="resources/images/logo-images.png"
					alt="logo-images"></a>
			</h1>
		</div>
		<div class="navbar">
			<div class="menu">
				<ul>
					<li><a href="#" onclick="order('${sessionScope.log.userId}')">ORDER</a></li>
					<li><a href="review">REVIEW</a></li>
					<li><a href="notice">NOTICE</a></li>
				</ul>
			</div>
			
			<div class="side_menu">
				<ul>
					<c:if test="${sessionScope.log.userId != null}">
						<li><a href="../service?command=logout">로그아웃</a></li>
						<li><a href="mypage">마이페이지</a></li>
					</c:if>
					<c:if test="${sessionScope.log.userId eq 'composecoffee'}">
						<li><a href="userList">회원목록</a></li>
					</c:if>
					<c:if test="${sessionScope.log.userId == null}">
						<li><a href="login">로그인</a></li>
						<li><a href="agree">회원가입</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</header>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="https://kit.fontawesome.com/412ff5bbec.js"
		crossorigin="anonymous"></script>
	<script src="../resources/header.js"></script>
</body>
</html>