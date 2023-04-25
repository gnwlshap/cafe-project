<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"/>
<script type="text/javascript" src="../resources/user.js" ></script>
</head>
<body>
	<section>
		<div class="banner"><h2>비밀번호 변경</h2></div>
		<div class="password-page">
			<h2>${sessionScope.log.userName}님 환영합니다.</h2>
			<form method="POST" action="../service">
				<input type="hidden" name="command" value="updatemypage">
				<table>
					<tr>
						<td>아이디</td>
						<td>${sessionScope.log.userId}</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="password" value="${sessionScope.log.userPassword}" required></td>
					</tr>
					<tr>
						<td>이름</td>
						<td>${sessionScope.log.userName}</td>
					</tr>
					<tr>
						<td>휴대전화</td>
						<td>${sessionScope.log.userPhone}</td>
					</tr>
					<tr>
						<td>주소</td>
						<td>${sessionScope.log.userAddress}</td>
					</tr>
					<tr>
						<td>가입일</td>
						<td>${sessionScope.log.userRegistDateOnlyDate}</td>
					</tr>
				</table>
				<div class="password-page-button">
					<input type="button" value="변경" onclick="passwordUpdate(form)">
					<input type="button" value="취소" onclick="passwordCancel(form)">
				</div>
			</form>
		</div>
	</section>
	<jsp:include page="/footer" />
</body>
</html>