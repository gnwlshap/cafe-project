function chk() {
	var req = document.form.req.checked;
	var num = 0;
	if (req == true) {
		num = 1;
	}
	if (num == 1) {
		document.form.submit();
	} else {
		alert("개인정보 약관에 동의하셔야 합니다.");
	}
}

function nochk() {
	alert("동의하지 않으면 가입하실 수 없습니다.");
	location.href = "../agree";
}