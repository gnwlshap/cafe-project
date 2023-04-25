/*$('#submit-reviewbtn').click(e => {
	//checkReviewWrite($('form'));
	checkReviewWrite($('form')[0]);
	console.log(10);
	//$('#submit-reviewbtn').off('click');
});
*/
/*$('#submit-noticebtn').click(e => {
	//checkNoticeWrite($('form'));
	checkReviewWrite($('form')[0]);
	console.log(10);
	//$('#submit-noticebtn').off('click');
});*/

$(document).ready(function() {
	date = new Date().toLocaleDateString();
	$('.writereview-date').append(
		`${date}`
	)
	$('.writenotice-date').append(
		`${date}`
	)
})


function checkNoticeWrite(htmlForm){
	const title = $("#notice_title").val();
	const content = $("#notice_content").val();
	
	if ((title == '' || title == undefined || title == null || title == 'null')) {
		alert("제목이 입력되지 않았습니다.");
		return 
	} else if ((content == '' || content == undefined || content == null || content == 'null')) {
		alert("내용이 입력되지 않았습니다.");
		return 
	} else {
		htmlForm.submit();
	}	
}

function checkReviewWrite(htmlForm){
	const title = $("#review_title").val();
	const content = $("#review_content").val();
	
	if ((title == '' || title == undefined || title == null || title == 'null')) {
		alert("제목이 입력되지 않았습니다.");
		return 
	} else if ((content == '' || content == undefined || content == null || content == 'null')) {
		alert("내용이 입력되지 않았습니다.");
		return 
	} else {
		htmlForm.submit();
	}	
}
