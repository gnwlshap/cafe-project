<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"/>
<script type="text/javascript" src="../resources/write.js"></script>
</head>
<body>
<c:set var="noticeNumber" value="${param.notice_number}" />
<c:if test="${requestScope.notice == null}">
	<c:redirect url="/service?command=modifynotice&notice_number=${noticeNumber}" />
</c:if>
	<section>
	<div class="banner"><h2>공지사항 수정</h2></div>
		<form method="post" action="../service">
			<input type="hidden" id="user_id" name="user_id" value="composecoffee">
			<input type="hidden" id="notice_number" name="notice_number" value="${noticeNumber}">
			<input type="hidden" id="command" name="command" value="updatenotice">
			<div>
				제목 : <input type="text" id="notice_title" name="notice_title" value="${notice.noticeTitle}">
			</div>
			<div>${now}</div>
			<div>
				내용 : <br> <br>
				<textarea rows="20" cols="100" id="notice_content"
					name="notice_content" style="padding: 5px;">${notice.noticeContent}</textarea>
			</div>
			<input type="button" value="수정하기" id="submit-noticebtn" onclick="checkNoticeWrite(form)">
		</form>
	</section>
	<jsp:include page="/footer"/>
</body>
</html>