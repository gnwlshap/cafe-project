package order;

import java.sql.Timestamp;

public class OrderDto {
	private int orderCode, cafeCode;
	private String userId;
	private Timestamp orderTime;
	private boolean reviewWritten;
	
	public OrderDto(int cafeCode, String userId) {
		this.cafeCode = cafeCode;
		this.userId = userId;
	}
	
	public OrderDto(int orderCode, int cafeCode, String userId, Timestamp orderTime) {
		this.orderCode = orderCode;
		this.cafeCode = cafeCode;
		this.userId = userId;
		this.orderTime = orderTime;
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

	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}

	public void setCafeCode(int cafeCode) {
		this.cafeCode = cafeCode;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public boolean isReviewWritten() {
		return reviewWritten;
	}

	public void setReviewWritten(boolean reviewWritten) {
		this.reviewWritten = reviewWritten;
	}
	
}
