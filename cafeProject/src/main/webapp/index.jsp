<%@page import="notice.Notice"%>
<%@page import="notice.controller.NoticeDao"%>
<%@page import="review.Review"%>
<%@page import="java.util.ArrayList"%>
<%@page import="review.controller.ReviewDao"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header"></jsp:include>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
</head>
<body>	
	<section>
		<%-- <jsp:include page="/popup"></jsp:include> --%>
		<article class="slide">
			<div class="swiper">
				<div class="swiper-wrapper">
					<div class="swiper-slide"><img src="resources/images/index-banner.jpg"></div>
					<div class="swiper-slide"><img src="resources/images/index-banner-2.jpg"></div>
					<div class="swiper-slide"><img src="resources/images/index-banner-3.jpg"></div>
					<div class="swiper-slide"><img src="resources/images/index-banner-4.jpg"></div>
				</div>
			</div>
		</article>
		<article class="main-content">
			<div class="main-notice">
				<h2>공지사항</h2>
				<div class="line"></div>
				<table>
				<tbody>
						<c:if test="${empty list}">
							<c:set var="noticeList" value="${NoticeDao.getInstance().getNoticeAll(0,5)}" />
						</c:if>
						<c:forEach var="notice" items="${noticeList}" >
						<tr>
							<td><a
								href="noticedetail?&notice_number=${notice.noticeNumber}">${notice.noticeTitle}</a></td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<div class="main-review">
				<h2>최신 리뷰</h2>
				<div class="line"></div>
				<table>
					<c:if test="${empty list}">
						<c:set var="reviewList" value="${ReviewDao.getInstance().getReviewAll(0,5)}" />
					</c:if>
					<c:forEach var="review" items="${reviewList}" >
						<tr>
							<td><a href="reviewdetail?&review_number=${review.reviewNumber}">${review.reviewTitle}</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</article>
		<article>
			<div class="index-content">
				<div class="content">
					<div>
					<a href="#" onclick="order('${sessionScope.log.userId}')">
						<img src="https://em-content.zobj.net/thumbs/120/mozilla/36/hot-beverage_2615.png">
						<br>
						주문하기</a>
					</div>
					<div>
					<a href="review">
						<img src="https://em-content.zobj.net/thumbs/120/mozilla/36/speech-balloon_1f4ac.png">
						<br>
						이용후기</a>
					</div>
					<div>
					<a href="notice">
						<img src="https://em-content.zobj.net/thumbs/120/mozilla/36/pushpin_1f4cc.png">
						<br>
						공지사항</a>
					</div>
				</div>
			</div>
		</article>
	</section>
	<jsp:include page="/footer"></jsp:include>
	<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
    <script src="resources/swiper.js"></script>
    <script src="resources/popup.js"></script>
</body>
</html>