<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"></jsp:include>
<script type="text/javascript" src="../resources/leave.js"></script>
<script type="text/javascript" src="../resources/user.js"></script>
</head>
<body>
	<section>
		<div class="banner">
			<h2>마이페이지</h2>
		</div>
		<div class="mypage">
			<h2>${sessionScope.log.userName}님환영합니다.</h2>
			<form method="POST" action="../service">
				<input type="hidden" id="userid" name="userid" value="${sessionScope.log.userId}">
				<input type="hidden" name="command" value="updatemypage">
				<table>
					<tr>
						<td>아이디</td>
						<td>${sessionScope.log.userId}</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td>${sessionScope.log.userPassword}</td>
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
				<div>
					<input type="button" value="내 작성글" onclick="location.href='mywrite'">
					<input type="button" value="내 주문" onclick="location.href='myorder'">
					<input type="button" value="비밀번호 변경" onclick="location.href='password'">
					<input type="button" value="탈퇴" onclick="leaveCheck()">
				</div>
			</form>
		</div>
	</section>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>