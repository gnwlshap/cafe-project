$(document).ready(function() {
	$('#userPhone').on('change', function() { // Phone 입력창에서 포커스가 벗어났을 때
		const phone = $('#userPhone').val(); // 입력된 Phone 값 가져오기
		console.log(phone);
		console.log(phone && /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/.test(phone));
		if(phone && /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/.test(phone)){ // 입력된 Phone 값이 있을 경우에만
			$.ajax({
				"url": `../service?command=duplphonecheck&phone=${phone}`,
				"method": "POST",
				"timeout": 0,
			}).done(function(response) {
				console.log(typeof response);
				console.log("response",response);
				if(response === 'true') {
					 $('#phone-check').text('이미 사용 중인 번호입니다.');
					 $("#phoneDuplication").val("phoneUnckeck");
					 console.log($("#phoneDuplication").val())
				} else {
					$("#phoneDuplication").val("phonecheck");
					$('#phone-check').text('사용 가능한 번호입니다.');
					console.log($("#phoneDuplication").val())
				}
			});
		}else{
			$('#phone-check').text('사용이 불가능한 번호입니다.');
			$("#phoneDuplication").val("phoneUnckeck");
		}
	});
});