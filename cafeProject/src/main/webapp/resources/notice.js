
$(document).ready(function() {
	let page = 1;
	const searchPage = location.search.substring(1).split('&');
	searchPage.forEach(e => {
		const info = e.split('=');
	 	if (info[0] === 'page')
	 		page = parseInt(info[1]);
	});
	searchNotice(page);
})

function searchNotice(page) {
	var category = $('select[name="category"] option:selected').val();
	var keyword = $('input[name="keyword"]').val();
	$.ajax({
		url: `/service?command=searchnotice&category=${category}&keyword=${keyword}&page=${page}`,
		type: 'GET',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		"timeout": 0,
	}).done((response) => {
		$('#notice-board').empty();
		appendHead();
		response.list.forEach((notice, index) => {
			const postNumber = response.status.count - (response.status.currentPage - 1) * 10 - index;
			$('#notice-board').append(
				`<tr>
					<td class="notice-number">${postNumber}</td>
					<td><a href="noticedetail?&notice_number=${notice.noticeNumber}">${notice.noticeTitle}</a></td>
					<td>${notice.userId}</td>
					<td>${notice.noticePostDate}</td>
					<td>${notice.noticeViewCount}</td>
				</tr>`
			);
		});
		
		const currentPage = response.status.currentPage; 
		const totalPages = response.status.totalPages; 
		renderPageNumber(currentPage,totalPages);
	});
}

function appendHead() {
	$('#notice-board').append(
		`<tr class="title-back">
			<td class="notice-number">No</td>
			<td class="notice-title">제목</td>
			<td class="notice-id">작성자</td>
			<td class="notice-date">날짜</td>
			<td class="notice-views">조회 수</td>
		</tr>`
	)
}

function renderPageNumber(currentPage, totalPages) {
	$('#page-box').empty();
	for (let i = 1; i <= totalPages; i++) {
		if (i === currentPage) {
			$('#page-box').append(`
				<span onclick='searchNotice(${i})' class="active">${i}</span> &nbsp &nbsp
			`);
		}
		else {
			$('#page-box').append(`
				<span onclick='searchNotice(${i})'>${i}</span> &nbsp &nbsp
			`);
		}
	}
}