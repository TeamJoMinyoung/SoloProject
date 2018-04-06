package model;

public class BuyProductVO {
	
	private int bp_tracenumber;
	private int bp_code;
	private String bp_name;
	private String bp_productname;
	private int bp_price;
	private int bp_kg;
	private String bp_date;
	private long bp_sum;
	
	
	public BuyProductVO() {
		super();
	}


	public BuyProductVO(int bp_tracenumber, int bp_code, String bp_name, String bp_productname, int bp_price, int bp_kg,
			String bp_date) {
		super();
		this.bp_tracenumber = bp_tracenumber;
		this.bp_code = bp_code;
		this.bp_name = bp_name;
		this.bp_productname = bp_productname;
		this.bp_price = bp_price;
		this.bp_kg = bp_kg;
		this.bp_date = bp_date;
	}
	
	


	public BuyProductVO(int bp_tracenumber, int bp_code, String bp_name, String bp_productname, int bp_price, int bp_kg,
			String bp_date, long bp_sum) {
		super();
		this.bp_tracenumber = bp_tracenumber;
		this.bp_code = bp_code;
		this.bp_name = bp_name;
		this.bp_productname = bp_productname;
		this.bp_price = bp_price;
		this.bp_kg = bp_kg;
		this.bp_date = bp_date;
		this.bp_sum = bp_sum;
	}

	
	public BuyProductVO(int bp_tracenumber, String bp_name, String bp_productname, int bp_price, int bp_kg,
			String bp_date, long bp_sum) {
		super();
		this.bp_tracenumber = bp_tracenumber;
		this.bp_name = bp_name;
		this.bp_productname = bp_productname;
		this.bp_price = bp_price;
		this.bp_kg = bp_kg;
		this.bp_date = bp_date;
		this.bp_sum = bp_sum;
	}


	public BuyProductVO(int bp_tracenumber, String bp_name, String bp_productname, int bp_price, int bp_kg,
			String bp_date) {
		super();
		this.bp_tracenumber = bp_tracenumber;
		this.bp_name = bp_name;
		this.bp_productname = bp_productname;
		this.bp_price = bp_price;
		this.bp_kg = bp_kg;
		this.bp_date = bp_date;
	}


	public BuyProductVO(int bp_tracenumber, int bp_kg) {
		super();
		this.bp_tracenumber = bp_tracenumber;
		this.bp_kg = bp_kg;
	}


	public int getBp_tracenumber() {
		return bp_tracenumber;
	}


	public void setBp_tracenumber(int bp_tracenumber) {
		this.bp_tracenumber = bp_tracenumber;
	}


	public int getBp_code() {
		return bp_code;
	}


	public void setBp_code(int bp_code) {
		this.bp_code = bp_code;
	}


	public String getBp_name() {
		return bp_name;
	}


	public void setBp_name(String bp_name) {
		this.bp_name = bp_name;
	}


	public String getBp_productname() {
		return bp_productname;
	}


	public void setBp_productname(String bp_productname) {
		this.bp_productname = bp_productname;
	}


	public int getBp_price() {
		return bp_price;
	}


	public void setBp_price(int bp_price) {
		this.bp_price = bp_price;
	}


	public int getBp_kg() {
		return bp_kg;
	}


	public void setBp_kg(int bp_kg) {
		this.bp_kg = bp_kg;
	}


	public String getBp_date() {
		return bp_date;
	}


	public void setBp_date(String bp_date) {
		this.bp_date = bp_date;
	}


	public long getBp_sum() {
		return bp_sum;
	}


	public void setBp_sum(long bp_sum) {
		this.bp_sum = bp_sum;
	}

	
	
}
