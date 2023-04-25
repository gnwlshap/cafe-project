function leaveCheck() {
	if (confirm("탈퇴하면 복구가 불가능합니다.\n 정말로 탈퇴하시겠습니까?")) {
		const userId = $("#userid").val();
		console.log(userId);
		location.href = `../service?command=leave&userId=${userId}`;
	} else {
		return;
		//location.href = "mypage";
	}
}


function removeNoticeCheck() {
	if (confirm("삭제하면 복구가 불가능합니다.\n 정말로 삭제하시겠습니까?")) {
		const noticeNumber = $("#notice_number").val();
		location.href = `../service?command=deletenotice&notice_number=${noticeNumber}`;
	} else {
		return;
	}
}
