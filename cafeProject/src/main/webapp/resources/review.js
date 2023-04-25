
$(document).ready(function() {
	let page = 1;

	const searchPage = location.search.substring(1).split('&');
	searchPage.forEach(e => {
		const info = e.split('=');
		if (info[0] === 'page')
			page = parseInt(info[1]);
	});

	searchReview(page);
})

function searchReview(page) {
	var category = $('select[name="category"] option:selected').val();
	var keyword = $('input[name="keyword"]').val();
	$.ajax({
		url: `/service?command=searchreview&category=${category}&keyword=${keyword}&page=${page}`,
		type: 'GET',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		"timeout": 0,
	}).done((response) => {
		$('#review-board').empty();
		appendHead();
		response.list.forEach((review, index) => {
			const postNumber = response.status.count - (response.status.currentPage - 1) * 10 - index;
			$('#review-board').append(
				`<tr>
					<td class="review-number">${postNumber}</td>
					<td class="review-title"><a href="reviewdetail?&review_number=${review.reviewNumber}">${review.reviewTitle}</a></td>
					<td class="review-writer">${review.userId}</td>
					<td class="review-date">${review.reviewPostDate}</td>
					<td class="review-views">${review.reviewViewCount}</td>
				</tr>`
			);
		});
		const currentPage = response.status.currentPage;
		const totalPages = response.status.totalPages;
		renderPageNumber(currentPage,totalPages);
	});
}

function appendHead() {
	$('#review-board').append(
		`<tr class="title-back">
			<td class="review-number">No</td>
			<td class="review-title">제목</td>
			<td class="review-writer">작성자</td>
			<td class="review-date">날짜</td>
			<td class="review-views">조회 수</td>
		</tr>`
	)
}

function renderPageNumber(currentPage, totalPages) {
	$('#page-box').empty();
	for (let i = 1; i <= totalPages; i++) {
		if (i === currentPage) {
			$('#page-box').append(`
				<span onclick='searchReview(${i})' class="active">${i}</span> &nbsp &nbsp
			`);
		}
		else {
			$('#page-box').append(`
				<span onclick='searchReview(${i})'>${i}</span> &nbsp &nbsp
			`);
		}
	}
}