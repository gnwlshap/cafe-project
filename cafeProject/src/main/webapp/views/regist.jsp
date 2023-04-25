<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"></jsp:include>
<script type="text/javascript" src="../resources/idDuplCheck.js"></script>
<script type="text/javascript" src="../resources/phoneDuplCheck.js"></script>
<script type="text/javascript" src="../resources/passwordDuplCheck.js"></script>
</head>
<body>
	<section>
		<article>
			<div class="regist-banner"></div>
		</article>
		<article>
			<form method="POST" action="../service" class="regist">
				<input type="hidden" name="command" value="regist">
				<input type="hidden" id="idDuplication" value="idUnckeck">
				<input type="hidden" id="phoneDuplication" value="phoneUnckeck">
				<input type="hidden" id="passwordDuplication" value="passwordUnckeck">
				<table>
					<tr>
						<td>아이디</td>
						<td class="back-color"><input type="text" name="userId"
							id="userId" placeholder="알파벳 대/소문자, 숫자만 입력 가능"><span id="id-check"></span></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td class="back-color"><input type="password"
							name="userPassword" id="userPassword" placeholder="알파벳 대/소문자, 숫자, ! @ # 만 입력 가능"></td>
					</tr>
					<tr>
						<td>비밀번호 확인</td>
						<td class="back-color"><input type="password"
							name="checkUserPassword" id="checkUserPassword"><span id="password-check"></span></td>
					</tr>
					<tr>
						<td>이름</td>
						<td class="back-color"><input type="text" name="userName"
							id="userName"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td class="back-color">
							<div>
								<input type="text" id="sample4_postcode" name="address_postNum" placeholder="우편번호">
								<input type="button" id="postcode" onclick="execDaumPostcode()" value="우편번호"><br>
								<input type="text" id="sample4_roadAddress" name="address_roadNm" placeholder="도로명주소">
								<input type="text" id="sample4_jibunAddress" name="address_jibun" placeholder="지번주소">
								<span id="guide" style="color: #999; display: none"></span><br> 
								<input type="text" id="sample4_detailAddress" name="address_detail" placeholder="상세주소">
								<!--   <input type="text" id="sample4_extraAddress" name="address_extra" placeholder="참고항목"> -->
								<script
									src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
							</div>
						</td>
					</tr>
					<tr>
						<td>휴대전화</td>
						<td class="back-color"><input type="text" name="userPhone"
							id="userPhone" placeholder="- 포함해 주세요"><span id="phone-check"></span></td>
					</tr>
				</table>
				<div class="button">
					<input type="button" value="회원가입" onclick="checkValues(form)">
				</div>
			</form>
		</article>
	</section>

	<script type="text/javascript" src="../resources/user.js"></script>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>

