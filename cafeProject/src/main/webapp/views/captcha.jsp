<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"></jsp:include>
<!-- async와 defer를 따로 사용하지 않고, src 속성에 직접 추가 -->
</head>
<body>
	<section>
		<article>
			<div class="login-banner"></div>
		</article>
		<article>
			<form method="post" action="../service" class="login" id="demo-form">
				<table id="captcha">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="userId" id="userId"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="userPassword"
							id="userPassword"></td>
					</tr>
				</table>
				<div id="warning-message"></div>
				<div class="button">
					<input type="button" onclick="check_recaptcha()" value="로그인">
				</div>
			</form>
		</article>
	</section>
	<script type="text/javascript" src="../resources/login.js"></script>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>