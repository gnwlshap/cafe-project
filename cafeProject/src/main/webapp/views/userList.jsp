<%@page import="util.PasswordEncryptor"%>
<%@page import="user.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.controller.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"></jsp:include>
</head>
<body>
	<section>
		<div class="banner">
			<h2>회원목록조회</h2>
		</div>
		<div>
			<table class="userlist">
				<tr class="title-back">
					<td>회원아이디</td>
					<td>비밀번호</td>
					<td>성명</td>
					<td>주소</td>
					<td>휴대전화</td>
					<td>가입일자</td>
				</tr>
				<c:set var="list" value="${UserDao.getInstance().getUserAll()}" />
				<c:forEach var="userList" items="${list}">
					<tr>
						<c:set var="password" value="${PasswordEncryptor.Encryptor(userList.userPassword)}" />
						<td>${userList.userId}</td>
						<td>${password}</td>
						<td>${userList.userName}</td>
						<td>${userList.userAddress}</td>
						<td>${userList.userPhone}</td>
						<td>${userList.userRegistDate.toString().substring(0,10)}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>