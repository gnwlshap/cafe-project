$(document).ready(function() {
	$('#userId').on('change', function() { // ID 입력창에서 포커스가 벗어났을 때
		const idRegex = /^[a-zA-Z0-9]{5,20}$/; // ID 조건
		const id = $('#userId').val(); // 입력된 ID 값 가져오기
		if (id && idRegex.test(id)) { // 입력된 ID 값이 있을 경우에만
			$.ajax({
				"url": `../service?command=duplidcheck&id=${id}`,
				"method": "POST",
				"timeout": 0,
			}).done(function(response) {
				console.log(typeof response);
				if(response === 'true') {
					 $('#id-check').text('이미 사용 중인 아이디입니다.');
					 $("#idDuplication").val("idUnckeck");
					 console.log($("#idDuplication").val())
				} else {
					$("#idDuplication").val("idcheck");
					$('#id-check').text('사용 가능한 아이디입니다.');
				}
			});
		}else{
			$('#id-check').text('아이디(5~20자리) 알파벳 대소문자, 숫자만 입력가능');
			$("#idDuplication").val("idUnckeck");
			console.log($("#idDuplication").val())
		}
	});
});