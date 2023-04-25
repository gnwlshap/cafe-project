// captcha
appendcaptcha();
function appendcaptcha() {
	$('head').append(`<script src="https://www.google.com/recaptcha/api.js" async defer></script>`);
	$('#captcha').append(
		`<tr>
			<td>Captcha</td>
			<td>
				<div class="g-recaptcha" data-sitekey="6Le7QK4lAAAAANdfkKnO7Ux-YJjR8BczBwAU7S05"
					data-callback='recaptcha' data-action='submit'></div>
			</td>
		</tr>`
	);
}

function check_recaptcha(){
	var checkcaptcha = grecaptcha.getResponse();
	if (checkcaptcha.length ==0) {
		alert ("'로봇이 아닙니다.'를 체크해주세요.");
	} else {
		login();
	}
}

let logincount = 1;
function login() {
	const id = $("#userId").val();
	const password = $("#userPassword").val();
	if (logincount < 4){
		if (id === '' ) {
			alert('아이디가 입력되지 않았습니다.');
			$("#userId").focus();
		} else if (password === '') {
			alert('비밀번호가 입력되지 않았습니다.');
			$("#userPassword").focus();
		} else {
			$.ajax({
			url: `service?command=login&userId=${id}&userPassword=${password}&`,
			type: 'POST',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8"
			}).done((response) => {
				if(!response.success){
					logincount++;
					alert ("아이디나 비밀번호가 일치하지 않습니다.");
				} else {
					location.href="/";
					logincount = 1;
				}
			});
		}
	}
	else {
		location.href="/captcha";
	}
}