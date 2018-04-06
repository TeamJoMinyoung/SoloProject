package model;

public class SellCompanyVO {
	private int sc_code;
	private String sc_name;
	private int sc_businessnumber;
	private String sc_ceo;
	private String sc_cphone;
	private String sc_address;
	private String sc_manager;
	private String sc_managerphone;
	
	
	public SellCompanyVO() {
		super();
	}


	public SellCompanyVO(int sc_code, String sc_name, int sc_businessnumber, String sc_ceo, String sc_cphone,
			String sc_address, String sc_manager, String sc_managerphone) {
		super();
		this.sc_code = sc_code;
		this.sc_name = sc_name;
		this.sc_businessnumber = sc_businessnumber;
		this.sc_ceo = sc_ceo;
		this.sc_cphone = sc_cphone;
		this.sc_address = sc_address;
		this.sc_manager = sc_manager;
		this.sc_managerphone = sc_managerphone;
	}


	public SellCompanyVO(String sc_name, int sc_businessnumber, String sc_ceo, String sc_cphone, String sc_address,
			String sc_manager, String sc_managerphone) {
		super();
		this.sc_name = sc_name;
		this.sc_businessnumber = sc_businessnumber;
		this.sc_ceo = sc_ceo;
		this.sc_cphone = sc_cphone;
		this.sc_address = sc_address;
		this.sc_manager = sc_manager;
		this.sc_managerphone = sc_managerphone;
	}


	public SellCompanyVO(String sc_name) {
		super();
		this.sc_name = sc_name;
	}


	public SellCompanyVO(String sc_name, int sc_businessnumber) {
		super();
		this.sc_name = sc_name;
		this.sc_businessnumber = sc_businessnumber;
	}


	public int getSc_code() {
		return sc_code;
	}


	public void setSc_code(int sc_code) {
		this.sc_code = sc_code;
	}


	public String getSc_name() {
		return sc_name;
	}


	public void setSc_name(String sc_name) {
		this.sc_name = sc_name;
	}


	public int getSc_businessnumber() {
		return sc_businessnumber;
	}


	public void setSc_businessnumber(int sc_businessnumber) {
		this.sc_businessnumber = sc_businessnumber;
	}


	public String getSc_ceo() {
		return sc_ceo;
	}


	public void setSc_ceo(String sc_ceo) {
		this.sc_ceo = sc_ceo;
	}


	public String getSc_cphone() {
		return sc_cphone;
	}


	public void setSc_cphone(String sc_cphone) {
		this.sc_cphone = sc_cphone;
	}


	public String getSc_address() {
		return sc_address;
	}


	public void setSc_address(String sc_address) {
		this.sc_address = sc_address;
	}


	public String getSc_manager() {
		return sc_manager;
	}


	public void setSc_manager(String sc_manager) {
		this.sc_manager = sc_manager;
	}


	public String getSc_managerphone() {
		return sc_managerphone;
	}


	public void setSc_managerphone(String sc_managerphone) {
		this.sc_managerphone = sc_managerphone;
	}
	
	

}
