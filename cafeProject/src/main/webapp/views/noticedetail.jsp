<%@page import="user.User"%>
<%@page import="notice.Notice"%>
<%@page import="notice.controller.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
<script type="text/javascript" src="../resources/leave.js"></script>
</head>
<body>
	<c:if test="${empty Notice}">
		<c:redirect url="/service?command=readnotice&notice_number=${param.notice_number}" />
	</c:if>

	<c:set var="updateNotice" value="location.href='updatenotice?notice_number=${param.notice_number}'" />
	
	<c:set var="deleteNotice"
		value="location.href='../service?command=deletenotice&notice_number=${param.notice_number}'" />
	<section>
		<input type="hidden" id="notice_number" name="notice_number" value="${param.notice_number}">
		<div class="notice-banner"></div>
		<div class="subject">
			<h2>공 지 사 항</h2>
		</div>
		<div class="notice-content">
			<div class="noticedetail-title">${Notice.noticeTitle}</div>
			<ul class="noticedetail">
				<li>${Notice.noticePostDate}</li>
				<li>조회수 : ${Notice.noticeViewCount}</li>
				<li class="noticedetail-right">${Notice.userId}</li>
			</ul>
			<div class="noticedetail-content">
				<p>${Notice.noticeContent}</p>
			</div>
			<div class="notice-button">
				<c:if test="${sessionScope.log.userId eq 'composecoffee'}">
					<input type="button" value="수정" class="notice-btn"
						onclick="${updateNotice}">
					<input type="button" value="삭제" class="notice-btn"
						onclick="removeNoticeCheck()">
					<input type="button" value="공지사항 작성" onclick="location.href='writenotice'" class="notice-btn">
				</c:if>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/footer" />
</html>