package model;

public class InventoryManagermentVO {
	private int im_code;
	private int im_tracenumber;
	private String im_productname;
	private int im_kg;
	private String im_date;
	
	
	
	public InventoryManagermentVO() {
		super();
	}


	public InventoryManagermentVO(int im_code, int im_tracenumber, String im_productname, int im_kg, String im_date) {
		super();
		this.im_code = im_code;
		this.im_tracenumber = im_tracenumber;
		this.im_productname = im_productname;
		this.im_kg = im_kg;
		this.im_date = im_date;
	}


	public InventoryManagermentVO(int im_tracenumber, int im_kg) {
		super();
		this.im_tracenumber = im_tracenumber;
		this.im_kg = im_kg;
	}
	
	


	public InventoryManagermentVO(int im_kg) {
		super();
		this.im_kg = im_kg;
	}

	
	public InventoryManagermentVO(int im_tracenumber, String im_productname, int im_kg, String im_date) {
		super();
		this.im_tracenumber = im_tracenumber;
		this.im_productname = im_productname;
		this.im_kg = im_kg;
		this.im_date = im_date;
	}
	


	public InventoryManagermentVO(int im_tracenumber, String im_productname, int im_kg) {
		super();
		this.im_tracenumber = im_tracenumber;
		this.im_productname = im_productname;
		this.im_kg = im_kg;
	}


	public int getIm_code() {
		return im_code;
	}


	public void setIm_code(int im_code) {
		this.im_code = im_code;
	}


	public int getIm_tracenumber() {
		return im_tracenumber;
	}


	public void setIm_tracenumber(int bp_tracenumber) {
		this.im_tracenumber = bp_tracenumber;
	}


	public String getIm_productname() {
		return im_productname;
	}


	public void setIm_productname(String im_productname) {
		this.im_productname = im_productname;
	}


	public int getIm_kg() {
		return im_kg;
	}


	public void setIm_kg(int im_kg) {
		this.im_kg = im_kg;
	}


	public String getIm_date() {
		return im_date;
	}


	public void setIm_date(String im_date) {
		this.im_date = im_date;
	}


	

	
	
}
