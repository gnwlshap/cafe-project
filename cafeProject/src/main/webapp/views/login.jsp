<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"></jsp:include>
</head>
<body>
	<section>
		<article>
			<div class="login-banner"></div>
		</article>
		<article>
			<form method="post" action="../service" class="login">
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="userId" id="userId"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="userPassword" id="userPassword"></td>
					</tr>
				</table>
				<div id="warning-message"></div>
				<div class="button">
					<input type="button" onclick="login()" value="로그인">
				</div>
			</form>
		</article>
	</section>
	<script type="text/javascript" src="../resources/login.js"></script>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>