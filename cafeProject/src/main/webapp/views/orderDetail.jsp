<%@page import="orderdetail.controller.OrderDetailDao"%>
<%@page import="user.User"%>
<%@page import="item.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="item.controller.ItemDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
</head>
<body>
	<c:if test="${empty requestScope.list}">
		<c:redirect url="/service?command=orderdetaillist&orderCode=${param.orderCode}"></c:redirect>
	</c:if>
	<section>
		<c:set var="orderCode" value="${orderCode}" />
		<div class="banner"><h2>주문 상세</h2></div>
		<table class="review-table">
			<tr>
				<td class="item-number">번호</td>
				<td class="item-name">메뉴</td>
				<td class="item-count">수량</td>
				<td class="item-total-price">가격</td>
			</tr>
			<c:forEach var="order" items="${list}" varStatus="status">
				<tr>
					<td class="item-number">${status.index + 1}</td>
					<td class="item-name">${order.menuName}</td>
					<td class="item-count">${order.itemCount}</td>
					<td class="item-total-price">${order.totalPrice}원</td>
				</tr>
			</c:forEach>
			<tr>
				<td>총 금액</td>
				<td> </td>
				<td> </td>
				<td class="total-price">${totalPrice}원</td>
			</tr>
		</table>
	</section>
		<jsp:include page="/footer" />
</body>
</html>