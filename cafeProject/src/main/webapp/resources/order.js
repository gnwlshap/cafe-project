// 메뉴 리스트
loadAllMenu();
function loadAllMenu(){
	$.ajax({
	  url: '/service?command=readallmenu',
	  type: 'GET',
	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	}).done((response) => {
	    response.forEach((item) => {
	        let menuCode = item.menuCode;
	        const menuName = item.menuName;
	        const category = item.category;
	        $('.tabcontent').append(
				`<div id="${category}">
					<div class="menuCode" data-value="${menuCode}">
						<h4 class="title">
							${menuName}
						</h4>
					<div class="menuBack"></div>
						<img class="menu-img"
							src="../resources/images/${menuName}.jpg">
					</div>
				</div>`
			);
	    });
		// 메뉴 이름표시
		$(".menuCode").hover(
			function() {
				$(this).find(".title").show();
			}, function() {
				$(this).find(".title").hide();
			}
		);
		// 클릭시 팝업 창
		$(".menuCode").click(function() {
			const menuName = $(this).find(".title").text();
			const menuCode = $(this).data("value");
			const cafeCode = $('#cafe').data("value");
			openPopup(menuName, menuCode, cafeCode);
			$('#pop-container').show();
		});
	
		// 팝업창 밖 클릭시 팝업창 닫기
		$(document).mouseup(function(e) {
			const container = $('#pop-up');
			if (!container.is(e.target) && container.has(e.target).length === 0) {
				$('#overlay').remove();
				$(".menuCode").css("pointer-events", "");
				$("#pop-up").remove();
			}
		});
	});
}

// 메뉴 카테고리 탭
$('.tabnav a').click(function() {
    if (this.hash === '#allMenu') {
        $('.tabcontent > div').hide().fadeIn();
    } else {
        $('.tabcontent > div').hide().filter(this.hash).fadeIn();
    }
    $('.tabnav a').removeClass('active');	// css tab 이벤트때문에 추가해주는 class
    $(this).addClass('active');
    return false;
}).filter(':eq(0)').click();



// 주문 팝업 창 내용
function openPopup(menuName,menuCode,cafeCode) {
	$('body').append('<div id="overlay"></div>'); // 팝업창 띄울시 배경색 
	$(".menuCode").css("pointer-events", "none"); // 팝업창 띄울시 외부 이벤트 차단
	$('#pop-container').append(
		`<div id="pop-up">
			<form action="/service">
				<input type="hidden" name="command" value="order"> 
				<input type="hidden" id="cafeCode" name="cafeCode" value="${cafeCode}">
				<input type="hidden" id="menuCode" name="menuCode" value="${menuCode}">
				<h2>ORDER</h2>
				<br>
				<h4>${menuName}</h4>
				<div id="order">
					<div class="button">
						<input type="button" id="minus" value="-">
						<input type="number" id="menuCount" name="menuCount" min="1" value="1">
						<input type="button" id="plus" value="+">
					</div>
					
					<div id="total">
					</div>
					
					<div class="button">
						<input type="button" id="add" value="추가" onclick="addCart()">
					</div>
				</div>
				
				<div id="table-container">
					<table id="cart">
						<tr>
							<td>메뉴</td>
							<td>수량</td>
							<td>소계</td>
							<td>   </td>
						</tr>
					</table>
				</div>
				
				<div id="cart-total">
				</div>
				
				<div class="button">
					<input type="button" id="clear" value="전체삭제" onclick="clearCart()">
					<input type="button" id="order" value="주문" onclick="getOrder(form)">
				</div>
			</form>
		</div>`
	);
	// 수량 증가
	$("#plus").click(function(e) {
		e.preventDefault();
	    let quantity = $("#menuCount");
	    quantity.val(parseInt(quantity.val()) + 1);
	});
	// 수량 감소
	$("#minus").click(function(e) {
		e.preventDefault()
	    let quantity = $("#menuCount");
	    if (parseInt(quantity.val()) > 1) {
	        quantity.val(parseInt(quantity.val()) - 1);
	    }
	});
	
	readCart();
}

//장바구니 추가
function addCart() {
	const cafeCode = $("#cafeCode").val();
	const menuCode = $("#menuCode").val();
	const menuCount = $("#menuCount").val();
		
	$.ajax({
	url: `service?command=addCart&cafeCode=${cafeCode}&menuCode=${menuCode}&menuCount=${menuCount}&`,
	type: 'POST',
	contentType: "application/x-www-form-urlencoded; charset=UTF-8"
	}).done(() => {
		$(".menuDetail").empty();
		readCart();
	});
}
// 장바구니 보기
function readCart(){
	$.ajax({
	  url: '/service?command=readCart',
	  type: 'GET',
	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	}).done((response) => {
		for(let i=0; i<response.length; i++){
		$('#cart').append(
				`<tr class="menuDetail">
					<td>${response[i].menuName}</td>
					<td>${response[i].menuCount}</td>
					<td>${response[i].menuCount * response[i].menuPrice} 원</td>
					<td><input type="button" value="취소" onclick="removeCart(event)"></td>
				</tr>`
		)}
		$("#cart-total").empty();
		readCartTotal();
	});
}
// 장바구니 총 가격
function readCartTotal(){
	$.ajax({
	  url: '/service?command=readTotalPrice',
	  type: 'GET',
	  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	}).done((response) => {
		$('#cart-total').append(
			`소계 ${response.totalPrice} 원`
		)
	});
}

// 장바구니 취소
function removeCart(e){
	const cafeCode = $("#cafeCode").val();
	const tr = e.target.parentElement.parentElement;
	const menuName = tr.children[0].textContent;
	const menuCount = tr.children[1].textContent;
		
	$.ajax({
	url: `service?command=removeCart&cafeCode=${cafeCode}&menuName=${menuName}&menuCount=${menuCount}&`,
	type: 'POST',
	contentType: "application/x-www-form-urlencoded; charset=UTF-8"
	}).done(() => {
		$(".menuDetail").empty();
		readCart();
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

// 주문
function getOrder(htmlForm){
	$.ajax({
		url: '/service?command=readCart',
		type: 'GET',
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	}).done((response) => {
		if (response.length === 0)
			alert("메뉴를 선택해주세요.")
		else if (confirm("주문 하시겠습니까?")) {
			alert("주문이 완료되었습니다.")
			htmlForm.submit();
		}
	});
}


