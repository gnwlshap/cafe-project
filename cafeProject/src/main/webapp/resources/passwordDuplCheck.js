$(document).ready(function() {
	$('#userPassword,#checkUserPassword').on('change', function() { // password 입력창에서 포커스가 벗어났을 때
		const pwRegex = /^[a-zA-Z0-9!@#$%^&*]{8,20}$/;
		const password = $('#userPassword').val(); // 입력된 password 값 가져오기
		const passwordCheck = $('#checkUserPassword').val(); // 입력된 password 값 가져오기

		if (password !== null && passwordCheck !== null) { // 입력된 password 값이 있을 경우에만
			if (pwRegex.test(password)) {
				if (password === passwordCheck) {
					$('#password-check').text('비밀번호가 일치합니다');
					$("#passwordDuplication").val("passwordckeck");
				} else {
					$('#password-check').text('비밀번호가 일치하지 않습니다');
					$("#passwordDuplication").val("passwordUnckeck");
				}
			} else {
				$('#password-check').text('비밀번호(8~20자리) 알파벳 대소문자,숫자,특수문자만 입력가능');
				$("#passwordDuplication").val("passwordUnckeck");
			}
		} else {
			$(this).val("").text('비밀번호가 입력되지 않았습니다.');
		}
	})
})