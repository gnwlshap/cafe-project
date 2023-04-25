// 전체매장 불러오기
loadAllCafe();
function loadAllCafe(){
	$.ajax({
	  url: '/service?command=readallcafe',
	  type: 'GET',
	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	}).done((response) => {
	    response.forEach((item) => {
	        let cafeCode = item.cafeCode;
	        let cafeName = item.cafeName;
	        let cafeAddress = item.cafeAddress;
	        let cafePhone = item.cafePhone;
	        
	        $('#cafe-table').append(
				`<tr id='${cafeCode}'>
					<td class="heart"><input type="checkbox" name="checker"
						id="checker${cafeCode}"> <label
						for="checker${cafeCode}"></label></td>
					<td class="cafeName" id="${cafeCode}"onclick="selCafe(${cafeCode})" data-value="${cafeCode}">${cafeName}</td>
					<td class="address">${cafeAddress}</td>
					<td class="number">${cafePhone}</td>
				</tr>`
			);
	    });
	});
}

// 즐겨찾기
// 즐겨찾기 불러오기	
loadScrap();
function loadScrap(){
	$('#favorite-cafe-table').empty();
	$.ajax({
	  url: '/service?command=readScrap',
	  type: 'GET',
	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	}).done((response) => {
	    response.forEach((item) => {
			
			let cafeCode = item.cafeCode;
	        const cafe = $('.cafe-table').find(`.cafeName[data-value="${cafeCode}"]`).closest('tr');
	        
	        cafe.find('input[name="checker"]').prop('checked', true);
	        const clonedCafe = cafe.clone().show();
	        $('#favorite-cafe-table').append(clonedCafe);
	    });
	});
}
// 즐겨찾기 추가 제거
$(document).on('click', 'input[name="checker"]', function() {
	const cafe = $(this).closest('tr');
	let cafeCode = cafe.find('.cafeName').data('value');
	// 즐겨찾기 추가
	if ($(this).is(':checked')) {
		$.ajax({
			url: `/service?command=createscrap&cafeCode=${cafeCode}`,
			type: 'GET',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		}).done(() => {
			loadScrap();
		});
	}
	// 즐겨찾기 제거
	else {
		$.ajax({
			url: `/service?command=deletescrap&cafeCode=${cafeCode}`,
			type: 'GET',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		}).done(() => {
			loadScrap();
			$(`tr[id="${cafeCode}"]`).find('input[name="checker"]').prop('checked', false);
		});
	}

});

// 매장 검색
function searchCafe(){
	$('#cafe-table tr').hide();
	var category = $('select[name="category"] option:selected').val();
	var keyword = $('input[name="keyword"]').val();
	$.ajax({
	  url: `/service?command=searchcafe&category=${category}&keyword=${keyword}`,
	  type: 'GET',
	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	}).done((response) => {
	    response.forEach((item) => {
	        let searchCafeCode = item.cafeCode;
	        $(`tr[id="${searchCafeCode}"]`).show();
	    });
	});
}

// 카페 선택
function selCafe(code){
	// 장바구니 확인
	$.ajax({
		url: '/service?command=readCart',
		type: 'GET',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	}).done((response) => {
		if (response.length === 0 || response[0].cafeCode === code) {
			location.href = `selMenu?cafeCode=${code}`;
		}
		else if (confirm("장바구니에 담긴 메뉴를 취소하고\n새로운 지점에서 주문하시겠습니까?")) {
			clearCart();
			location.href = `selMenu?cafeCode=${code}`;
		}
	});
}

// 장바구니 비우기
function clearCart(){
	$.ajax({
	url: `service?command=clearCart`,
	type: 'POST',
	contentType: "application/x-www-form-urlencoded; charset=UTF-8"
	}).done(() => {
		$(".menuDetail").empty();
		readCart();
	});
}