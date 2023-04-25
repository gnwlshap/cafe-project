<%@page import="cafe.Cafe"%>
<%@page import="cafe.controller.CafeDao"%>
<%@page import="user.User"%>
<%@page import="order.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="order.controller.OrderDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
<script type="text/javascript" src="../resources/user.js"></script>
</head>
<body>
	<%-- <c:if test="${empty requestScope.list}"> --%>
	<%-- empty를 사용하게 되었을 경우 list가 null이거나 비어있는 경우 [ list.size()가 0인 경우 모두
	조건식이 true가 되어 무한 루프에 빠지게 된다. dao에서 list에 값을 add시킬때 초기 선언을 null로 해놓는 것이 아니라
	new 해서 새로운 리스트를 만드므로 list에 특정 객체가 담기지 않아도 길이가 0이 되므로 null값을 가질때만 true로 해주게
	설정을 바꿈]--%>
	<c:if test="${requestScope.list == null}">
		<c:redirect url="/service?command=orderlist&userId=${sessionScope.log.userId}"/>
	</c:if>

	<section>
		<div class="banner">
			<h2>내 주문</h2>
		</div>
		<table class="review-table">
			<tr>
				<td class="review-number">번호</td>
				<td class="review-title">주문 코드</td>
				<td class="review-writer">주문 지점</td>
				<td class="review-date">주문 날짜</td>
				<td>리뷰</td>
			</tr>
			<c:forEach var="orderlist" items="${list}" varStatus="status" end="${list.size()}">
				<c:set var="url" value="writereview?order_code=${orderlist.orderCode}&user_id=${sessionScope.log.userId}" />
				<tr>
					<td class="order-count">${status.end - status.index}</td>
					<td class="order-code">${orderlist.orderCode}</td>
					<td class="order-cafe">
						<a href="orderDetail?orderCode=${orderlist.orderCode}">${orderlist.cafeName}</a></td>
					<td class="order-date">${orderlist.orderTime}</td>
					<c:choose>
						<c:when test="${not orderlist.reviewWritten}">
							<td><button onclick="location.href='${url}'">리뷰 작성</button></td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</section>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>