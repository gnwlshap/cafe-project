package cafe;

import java.sql.Timestamp;

public class CafeDto {
	private int cafeCode;
	private String cafeName;
	private String cafeAddress;
	private String cafePhone;
	private Timestamp cafeRegistDate;

	public CafeDto(int cafeCode, String cafeName, String cafeAddress, String cafePhone) {
		this.cafeCode = cafeCode;
		this.cafeName = cafeName;
		this.cafeAddress = cafeAddress;
		this.cafePhone = cafePhone;
	}

	public CafeDto(int cafeCode, String cafeName, String cafeAddress, String cafePhone, Timestamp cafeRegistDate) {
		this.cafeCode = cafeCode;
		this.cafeName = cafeName;
		this.cafeAddress = cafeAddress;
		this.cafePhone = cafePhone;
		this.cafeRegistDate = cafeRegistDate;
	}

	public int getCafeCode() {
		return cafeCode;
	}

	public void setCafeCode(int cafeCode) {
		this.cafeCode = cafeCode;
	}

	public String getCafeName() {
		return cafeName;
	}

	public void setCafeName(String cafeName) {
		this.cafeName = cafeName;
	}

	public String getCafeAddress() {
		return cafeAddress;
	}

	public void setCafeAddress(String cafeAddress) {
		this.cafeAddress = cafeAddress;
	}

	public String getCafePhone() {
		return cafePhone;
	}

	public void setCafePhone(String cafePhone) {
		this.cafePhone = cafePhone;
	}

	public Timestamp getCafeRegistDate() {
		return cafeRegistDate;
	}

	public void setCafeRegistDate(Timestamp cafeRegistDate) {
		this.cafeRegistDate = cafeRegistDate;
	}
}