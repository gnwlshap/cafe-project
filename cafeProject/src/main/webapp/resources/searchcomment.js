// 전역 변수 리뷰게시글 번호, 로그인한 아이디,
// seq : hidden 댓글고유번호, 수정버튼 클릭 여부

const reviewnumber = $("#review_number").val();
const logginId = $("#logginId").val();
let seq = '';
let updateStatus = 0;

// 기철형님 제안 [댓글 수정버튼 여러번 클릭시 이전 댓글 저장 버튼 전 댓글 내용 저장]
let previousComment = '';

$(document).ready(function() {
	commentWriteBox();

	$('#write-btn').click(createComment);

	searchComment();
})

function searchComment() {
	$.ajax({
		"url": `/service?command=getcomment&review_number=${reviewnumber}`,
		"method": "GET",
		"contentType": "application/x-www-form-urlencoded; charset=UTF-8",
		"timeout": 0,
	}).done(function(response) {
		$("#comment").empty();
		let trNum = 1;

		response.forEach(function(comment) {
			if (logginId === comment.userId || logginId === 'composecoffee') {
				const modifyComment = `<button onclick="updateComment(${trNum})">수정</button>`;
				const deleteComment = `<button onclick="deleteComment(${comment.commentSeq},${reviewnumber},${trNum})">삭제</button>`;

				$("#comment").append(
					`<tr id="tr${trNum}">
					<input type="hidden" name="commentSeq" value="${comment.commentSeq}">
					<td>${comment.commentContent}</td>
					<td>${comment.userId}</td>
					<td>${comment.commentPostDate.substring(0, 10)}</td>
					<td>${modifyComment}</td>
					<td>${deleteComment}</td>
					</tr>`)
			} else {
				$("#comment").append(
					`<tr id="tr${trNum}">
					<input type="hidden" name="commentSeq" value="${comment.commentSeq}">
					<td>${comment.commentContent}</td>
					<td>${comment.userId}</td>
					<td>${comment.commentPostDate.substring(0, 10)}</td>
					<td></td>
					<td></td>
					</tr>`)
			}
			trNum++;
		});
	});
}


// 삭제 버튼 메소드
// 파라미터 값으로 해당 댓글의 index와 리뷰게시글 번호, tablerow의 고유 id번호를 받음
// trNum을 받는 이유는 해당 tr을 삭제하기 위해 넘겨받음

function deleteComment(commentSeq, reviewnumber, trNum) {
	if (confirm("삭제하면 복구가 불가능합니다.\n 정말로 삭제하시겠습니까?")) {
		$.ajax({
			"url": `/service?command=deletecomment&comment_seq=${commentSeq}&review_number=${reviewnumber}`,
			"method": "GET",
			"contentType": "application/x-www-form-urlencoded; charset=UTF-8",
			"timeout": 0,
		}).done(() => {
			$(`#tr${trNum}`).remove();
		})
	} else {
		return;
	}
}


// 수정버튼 클릭시 수정버튼 활성화 메소드
// updateStatus를 이용하여 이전에 수정버튼이 활성화 되어 있다면 자동으로 저장 후
// 새롭게 클릭한 수정버튼의 댓글 수정 활성화 담당
// seq활용 이유
// tr안의 input태그는 cells로 인식을 못함. 그래서 전역변수로 commentSeq값을 따로 저장 함
// 그리고 appeand할 content 변수에 미리 input type="hidden"으로 값을 저장해줌

function updateComment(trNum) {
	if (updateStatus === 0) {
		updateStatus = trNum;

		seq = document.getElementById(`tr${trNum}`).querySelector('input[name="commentSeq"]').value;
		var x = document.getElementsByTagName("tr");
		let trCells = x[trNum - 1].cells;
		let content = ``;
		for (let i = 0; i < trCells.length; i++) {
			if (i == 0) {
				content += `<td><textarea id="updateTextarea-${trNum}">${trCells[i].innerText}</textarea></td>`;
				previousComment = trCells[i].innerText;
			} else if (i == 3) {
				content += `<td><button onclick="saveComment(${trNum})">저장</button></td>`;
			} else {
				content += `<td>${trCells[i].innerHTML}</td>`;
			}
		}
		content += `<input type="hidden" name="commentSeq" value="${seq}">`;

		$("#tr" + trNum).empty().append(content);
	} else {
		saveComment(updateStatus);
		updateComment(trNum);
	}
}


// 저장 메소드
// 저장시 updateStatus를 다시 0으로 복구 후 저장 작업 수행
// AJAX로 수정된 댓글 저장에 필요한 요소들을 파라미터 값으로 전송함
// 요청 완료시 결과값 result는 저장할 Comment객체를 반환

function saveComment(trNum) {
	let strText = document.getElementById(`updateTextarea-${trNum}`).value;
	let commentSeq = document.getElementById(`tr${trNum}`).querySelector('input[name="commentSeq"]').value;
	
	if (updateStatus !== 0) {
		strText = previousComment;
	}
	updateStatus = 0;

	$.ajax({
		"url": `/service?command=updatecomment&comment_seq=${commentSeq}&comment_content=${strText}`,
		"method": "GET",
		"contentType": "application/x-www-form-urlencoded; charset=UTF-8",
		"timeout": 0,
	}).done((result) => {
		if (result !== "null") {
			$(`#tr${trNum}`).empty();
			$(`#tr${trNum}`).append(
				`<td>${result.content}</td>
					<td>${result.userId}</td>
					<td>${result.postDate.substring(0, 10)}</td>
					<td><button onclick="updateComment(${trNum})">수정</button></td>
					<td><button onclick="deleteComment(${result.commentSeq},${result.reviewNumber},${trNum})">삭제</button></td>
					`);
			$(`#tr${trNum}`).append(`<input type="hidden" name="commentSeq" value="${result.commentSeq}">`);
		}
	})
}


// 댓글 입력 상자
function commentWriteBox() {
	$('.comment-write').append(
		`<h5>Leave a Comment:</h5>
		<div>
			<textarea id="comment_content" name="comment_content" rows="5" cols="100"
			 placeholder=" 댓글을 입력해 주세요."></textarea>
			<button id="write-btn">댓글 작성</button>
		</div>`
	)
}

//$('#write-btn').click(createComment);
// 등록 메서드

function createComment() {
	const number = $('input[id="review_number"]').val();
	const content = $('#comment_content').val();

	if (content.trim() === '') {
		alert("댓글 내용을 입력해주세요.");
		return;
	}

	$.ajax({
		"url": `/service?command=createcomment&review_number=${number}&comment_content=${content}`,
		"type": 'GET',
		"contentType": "application/x-www-form-urlencoded; charset=UTF-8",
		"timeout": 0,
	}).done(() => {
		$('#comment_content').val(''); // comment_content 필드 비우기
		searchComment(); // 댓글 다시 불러오기
	})
}












