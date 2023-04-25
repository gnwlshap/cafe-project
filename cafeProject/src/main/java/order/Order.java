package order;

import java.sql.Timestamp;

public class Order {
	private int orderCode, cafeCode;
	private String userId;
	private Timestamp orderTime;
	private boolean reviewWritten;
	
	public Order(int cafeCode, String userId) {
		this.cafeCode = cafeCode;
		this.userId = userId;
	}
	
	public Order(int orderCode, int cafeCode, String userId, Timestamp orderTime, boolean reviewWritten) {
		this.orderCode = orderCode;
		this.cafeCode = cafeCode;
		this.userId = userId;
		this.orderTime = orderTime;
		this.reviewWritten = reviewWritten;
	}
	
	public Order(OrderDto orderDto) {
		this.orderCode = orderDto.getOrderCode();
		this.cafeCode = orderDto.getCafeCode();
		this.userId = orderDto.getUserId();
	}

	public int getOrderCode() {
		return orderCode;
	}

	public int getCafeCode() {
		return cafeCode;
	}

	public String getUserId() {
		return userId;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public boolean isReviewWritten() {
		return reviewWritten;
	}

	public void setReviewWritten(boolean reviewWritten) {
		this.reviewWritten = reviewWritten;
	}
	
}
