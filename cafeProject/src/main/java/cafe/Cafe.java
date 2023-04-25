package cafe;

import java.sql.Timestamp;

public class Cafe {
	private int cafeCode;
	private String cafeName;
	private String cafeAddress;
	private String cafePhone;
	private Timestamp cafeRegistDate;

	public Cafe(int cafeCode, String cafeName, String cafeAddress, String cafePhone) {
		this.cafeCode = cafeCode;
		this.cafeName = cafeName;
		this.cafeAddress = cafeAddress;
		this.cafePhone = cafePhone;
	}

	public Cafe(int cafeCode, String cafeName, String cafeAddress, String cafePhone, Timestamp cafeRegistDate) {
		this.cafeCode = cafeCode;
		this.cafeName = cafeName;
		this.cafeAddress = cafeAddress;
		this.cafePhone = cafePhone;
		this.cafeRegistDate = cafeRegistDate;
	}

	public Cafe(CafeDto cafeDto) {
		this.cafeCode = getCafeCode();
		this.cafeName = getCafeName();
		this.cafeAddress = getCafeAddress();
		this.cafePhone = getCafePhone();
	}

	public int getCafeCode() {
		return cafeCode;
	}

	public String getCafeName() {
		return cafeName;
	}

	public String getCafeAddress() {
		return cafeAddress;
	}

	public String getCafePhone() {
		return cafePhone;
	}

	public String getCafeRegistDate() {
		return cafeRegistDate.toString().substring(0,19);
	}
}
