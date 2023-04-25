<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
<script type="text/javascript" src="../resources/write.js"></script>
</head>
<body>
	<section>
	<div class="banner"><h2>공지사항 작성</h2></div>
	<div class="writenotice">
		<form method="post" action="../service">
			<input type="hidden" id="user_id" name="user_id" value="composecoffee">
			<input type="hidden" id="command" name="command" value="writenotice">
			<div class="writenotice-title">
				제목 : <input type="text" id="notice_title" name="notice_title">
			</div>
			<div class="writenotice-date">
			</div>
			<div class="writenotice-content">
				내용 : <br> <br>
				<textarea rows="20" cols="100" id="notice_content"
					name="notice_content" style="padding: 5px;"></textarea>
			</div>
			<input type="button" value="작성" onclick="checkNoticeWrite(form)">
		</form>
	</div>
	</section>
	<jsp:include page="/footer" />
</body>
</html>