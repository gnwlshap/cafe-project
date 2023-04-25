
function checkValues(htmlForm) {

	const id = $("#userId").val();
	const password = $("#userPassword").val();
	const passwordCheck = $("#checkUserPassword").val();
	const name = $("#userName").val();
	const address_postNum = $("#sample4_postcode").val();
	const address_roadNm = $("#sample4_roadAddress").val();
	const address_jibun = $("#sample4_jibunAddress").val();
	const address_detail = $("#sample4_detailAddress").val();
	const phone = $("#userPhone").val();

	const id_dupl = $("#idDuplication").val();
	const phone_dupl = $("#phoneDuplication").val();
	const password_dupl = $("#passwordDuplication").val();


	if (id.value === '' ) {
		alert('아이디가 입력되지 않았습니다.');
		id.focus();
	}
	else if (id_dupl === "idUnckeck") {
		alert('이미 사용중인 아이디입니다.');
		id.focus();
	}
	else if (password.value === "") {
		alert('비밀번호가 입력되지 않았습니다.');
		password.focus();
	}
	else if (password_dupl === "passwordUnckeck") {
		alert('비밀번호가 일치하지 않습니다.');
		passwordCheck.focus();
	}
	else if (name.value === "") {
		alert('이름이 입력되지 않았습니다.');
		name.focus();
	}
	else if (address_postNum === "") {
		alert('우편번호 입력되지 않았습니다.');
		address_postNum.focus();
	}
	else if (address_roadNm === "") {
		alert('도로명 주소가 입력되지 않았습니다.');
		address_roadNm.focus();
	}
	else if (address_jibun === "") {
		alert('주소가 입력되지 않았습니다.');
		address_jibun.focus();
	}
	else if (address_detail === "") {
		alert('상세주소가 입력되지 않았습니다.');
		address_detail.focus();
	}
	else if (phone.value === "") {
		alert('전화번호가 입력되지 않았습니다.');
		phone.focus();
	}
	else if (phone_dupl === "phoneUnckeck") {
		alert('이미 사용중인 번호입니다.');
		phone.focus();
	}
	else if (confirm("회원가입 하시겠습니까?")) {
		htmlForm.submit();
	}
}


//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function execDaumPostcode() {
	new daum.Postcode(
		{
			oncomplete: function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var roadAddr = data.roadAddress; // 도로명 주소 변수
				var extraRoadAddr = ''; // 참고 항목 변수

				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== ''
					&& /[동|로|가]$/g
						.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== ''
					&& data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', '
						+ data.buildingName
						: data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' ('
						+ extraRoadAddr
						+ ')';
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document
					.getElementById('sample4_postcode').value = data.zonecode;
				document
					.getElementById("sample4_roadAddress").value = roadAddr;
				document
					.getElementById("sample4_jibunAddress").value = data.jibunAddress;

				// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
				if (roadAddr !== '') {
					document
						.getElementById("sample4_extraAddress").value = extraRoadAddr;
				} else {
					document
						.getElementById("sample4_extraAddress").value = '';
				}

				var guideTextBox = document
					.getElementById("guide");
				// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
				if (data.autoRoadAddress) {
					var expRoadAddr = data.autoRoadAddress
						+ extraRoadAddr;
					guideTextBox.innerHTML = '(예상 도로명 주소 : '
						+ expRoadAddr
						+ ')';
					guideTextBox.style.display = 'block';

				} else if (data.autoJibunAddress) {
					var expJibunAddr = data.autoJibunAddress;
					guideTextBox.innerHTML = '(예상 지번 주소 : '
						+ expJibunAddr
						+ ')';
					guideTextBox.style.display = 'block';
				} else {
					guideTextBox.innerHTML = '';
					guideTextBox.style.display = 'none';
				}
			}
		}).open();
}

function passwordUpdate(htmlForm) {
	if (confirm('변경하시겠습니까?')) {
		alert('변경되었습니다.');
		htmlForm.submit();
	}
}

function passwordCancel(htmlFrom) {
	if (confirm('취소하시겠습니까?')) {
		alert('취소되었습니다.');
		location.href = 'mypage';
	}
}