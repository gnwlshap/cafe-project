function order(log) {
	if (log === 'null' || log === null || log === '' || log === undefined) {
		alert('로그인 화면으로 이동합니다.');
		location.href = 'login';
	} else {
		location.href = 'cafe';
	}
}

const toggleBtn = document.querySelector('.menu_btn');
const menu = document.querySelector('.navbar');

toggleBtn.addEventListener('click', () => {
	menu.classList.toggle('active');
});