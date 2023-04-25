<%@page import="cafe.Cafe"%>
<%@page import="user.User"%>
<%@page import="scrap.Scrap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="scrap.controller.ScrapDao"%>
<%@page import="cafe.controller.CafeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
</head>
<body>
	<%
	User log = (User) session.getAttribute("log");
	String userId = log.getUserId();
	CafeDao cafeDao = CafeDao.getInstance();
	ScrapDao scrapDao = ScrapDao.getInstance();
	ArrayList<Scrap> scrapList = scrapDao.getScrapByUserId(userId);
	ArrayList<Cafe> allList = cafeDao.getCafeAll();
	pageContext.setAttribute("scrapList", scrapList);
	pageContext.setAttribute("allList", allList);
	%>
	<section>
		<article>
			<img class="banner" src="resources/images/order-banner.jpg"
				alt="banner image">
		</article>
		<article>
			<form>
				<div>
					<h2>즐겨찾는 매장</h2>
					<ul>
						<c:forEach var="scrap" items="${scrapList}">
							<li><a id="cafe" href="selMenu?cafe=${scrap.cafeCode}">
									${cafeDao.getCafeByCode(scrap.cafeCode).cafeName} </a></li>
						</c:forEach>
					</ul>
				</div>
				<div>
					<h2>전체 매장</h2>
					<ul>
						<c:forEach var="all" items="${allList}">
							<li><a id="cafeCode" href="selMenu?cafeCode=${all.cafeCode}">
									${all.cafeName} </a></li>
						</c:forEach>
					</ul>
				</div>
			</form>
			<div class="move-page"></div>
		</article>
	</section>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="../resources/order.js"></script>
	<jsp:include page="/footer" />
</body>
</html>