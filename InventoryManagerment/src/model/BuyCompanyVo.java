package model;

public class BuyCompanyVo {
	private int bc_code;
	private String bc_name;
	private int bc_businessnumber;
	private String bc_ceo;
	private String bc_cphone;
	private String bc_address;
	private String bc_manager;
	private String bc_managerphone;
	
	
	public BuyCompanyVo() {
		super();
	}
	
	


	public BuyCompanyVo(int bc_code, String bc_name, int bc_businessnumber, String bc_ceo, String bc_cphone,
			String bc_address, String bc_manager, String bc_managerphone) {
		super();
		this.bc_code = bc_code;
		this.bc_name = bc_name;
		this.bc_businessnumber = bc_businessnumber;
		this.bc_ceo = bc_ceo;
		this.bc_cphone = bc_cphone;
		this.bc_address = bc_address;
		this.bc_manager = bc_manager;
		this.bc_managerphone = bc_managerphone;
	}




	public BuyCompanyVo(String bc_name, int bc_businessnumber, String bc_ceo, String bc_cphone, String bc_address,
			String bc_manager, String bc_managerphone) {
		super();
		this.bc_name = bc_name;
		this.bc_businessnumber = bc_businessnumber;
		this.bc_ceo = bc_ceo;
		this.bc_cphone = bc_cphone;
		this.bc_address = bc_address;
		this.bc_manager = bc_manager;
		this.bc_managerphone = bc_managerphone;
	}


	


	public int getBc_code() {
		return bc_code;
	}


	public void setBc_code(int bc_code) {
		this.bc_code = bc_code;
	}


	public String getBc_name() {
		return bc_name;
	}


	public void setBc_name(String bc_name) {
		this.bc_name = bc_name;
	}


	public int getBc_businessnumber() {
		return bc_businessnumber;
	}


	public void setBc_businessnumber(int bc_businessnumber) {
		this.bc_businessnumber = bc_businessnumber;
	}


	public String getBc_ceo() {
		return bc_ceo;
	}


	public void setBc_ceo(String bc_ceo) {
		this.bc_ceo = bc_ceo;
	}


	public String getBc_cphone() {
		return bc_cphone;
	}


	public void setBc_cphone(String bc_cphone) {
		this.bc_cphone = bc_cphone;
	}


	public String getBc_address() {
		return bc_address;
	}


	public void setBc_address(String bc_address) {
		this.bc_address = bc_address;
	}


	public String getBc_manager() {
		return bc_manager;
	}


	public void setBc_manager(String bc_manager) {
		this.bc_manager = bc_manager;
	}


	public String getBc_managerphone() {
		return bc_managerphone;
	}


	public void setBc_managerphone(String bc_managerphone) {
		this.bc_managerphone = bc_managerphone;
	}
	
	
}
