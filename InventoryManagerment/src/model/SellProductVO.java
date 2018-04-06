package model;

public class SellProductVO {
	private int sp_code;
	private int sp_tracenumber;
	private String sp_name;
	private String sp_productname;
	private int sp_price;
	private int sp_kg;
	private String sp_date;
	private long sp_sum;
	
	
	public SellProductVO() {
		super();
	}


	public SellProductVO(int sp_code, int sp_tracenumber, String sp_name, String sp_productname, int sp_price,
			int sp_kg, String sp_date) {
		super();
		this.sp_code = sp_code;
		this.sp_tracenumber = sp_tracenumber;
		this.sp_name = sp_name;
		this.sp_productname = sp_productname;
		this.sp_price = sp_price;
		this.sp_kg = sp_kg;
		this.sp_date = sp_date;
	}
	
	
	
	

	public SellProductVO(int sp_code, int sp_tracenumber, String sp_name, String sp_productname, int sp_price,
			int sp_kg, String sp_date, long sp_sum) {
		super();
		this.sp_code = sp_code;
		this.sp_tracenumber = sp_tracenumber;
		this.sp_name = sp_name;
		this.sp_productname = sp_productname;
		this.sp_price = sp_price;
		this.sp_kg = sp_kg;
		this.sp_date = sp_date;
		this.sp_sum = sp_sum;
	}


	public SellProductVO(int sp_tracenumber, String sp_name, String sp_productname, int sp_price, int sp_kg,
			String sp_date) {
		super();
		this.sp_tracenumber = sp_tracenumber;
		this.sp_name = sp_name;
		this.sp_productname = sp_productname;
		this.sp_price = sp_price;
		this.sp_kg = sp_kg;
		this.sp_date = sp_date;
	}

	
	public SellProductVO(int sp_tracenumber, String sp_name, String sp_productname, int sp_price, int sp_kg,
			String sp_date, long sp_sum) {
		super();
		this.sp_tracenumber = sp_tracenumber;
		this.sp_name = sp_name;
		this.sp_productname = sp_productname;
		this.sp_price = sp_price;
		this.sp_kg = sp_kg;
		this.sp_date = sp_date;
		this.sp_sum = sp_sum;
	}


	public SellProductVO(int sp_tracenumber, int sp_kg) {
		super();
		this.sp_tracenumber = sp_tracenumber;
		this.sp_kg = sp_kg;
	}
	
	

	public SellProductVO(String sp_productname, int sp_kg) {
		super();
		this.sp_productname = sp_productname;
		this.sp_kg = sp_kg;
	}


	public SellProductVO(String sp_name, String sp_productname, int sp_price, int sp_kg, String sp_date) {
		super();
		this.sp_name = sp_name;
		this.sp_productname = sp_productname;
		this.sp_price = sp_price;
		this.sp_kg = sp_kg;
		this.sp_date = sp_date;
	}


	public int getSp_code() {
		return sp_code;
	}


	public void setSp_code(int sp_code) {
		this.sp_code = sp_code;
	}


	public int getSp_tracenumber() {
		return sp_tracenumber;
	}


	public void setSp_tracenumber(int sp_tracenumber) {
		this.sp_tracenumber = sp_tracenumber;
	}


	public String getSp_name() {
		return sp_name;
	}


	public void setSp_name(String sp_name) {
		this.sp_name = sp_name;
	}


	public String getSp_productname() {
		return sp_productname;
	}


	public void setSp_productname(String sp_productname) {
		this.sp_productname = sp_productname;
	}


	public int getSp_price() {
		return sp_price;
	}


	public void setSp_price(int sp_price) {
		this.sp_price = sp_price;
	}


	public int getSp_kg() {
		return sp_kg;
	}


	public void setSp_kg(int sp_kg) {
		this.sp_kg = sp_kg;
	}


	public String getSp_date() {
		return sp_date;
	}


	public void setSp_date(String sp_date) {
		this.sp_date = sp_date;
	}


	public long getSp_sum() {
		return sp_sum;
	}


	public void setSp_sum(long sp_sum) {
		this.sp_sum = sp_sum;
	}


	
}
