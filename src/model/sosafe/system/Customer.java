package model.sosafe.system;

/**
 * @author Group 16
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Project
 * Date of Submission: June 11th 2017
 * 
 * Model class to represent Customer
 */
public class Customer {
	
	
	
	private String name;
	private String bldgName;
	private String username;
	private String password;
	private String phno;
	private String email;
	private String emergencyPhno;
	private String emergencyPhno2;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String responsecode;
	private Building bldg;
	private BillGenerate bill;
	private String SvceContractId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBldgName() {
		return bldgName;
	}

	public void setBldgName(String bldgName) {
		this.bldgName = bldgName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Building getBldg() {
		return bldg;
	}

	public void setBldg(Building bldg) {
		this.bldg = bldg;
	}

	public BillGenerate getBill() {
		return bill;
	}

	public void setBill(BillGenerate bill) {
		this.bill = bill;
	}

	public String getSvceContractId() {
		return SvceContractId;
	}

	public void setSvceContractId(String svceContractId) {
		SvceContractId = svceContractId;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergencyPhno() {
		return emergencyPhno;
	}

	public void setEmergencyPhno(String emergencyPhno) {
		this.emergencyPhno = emergencyPhno;
	}

	public String getEmergencyPhno2() {
		return emergencyPhno2;
	}

	public void setEmergencyPhno2(String emergencyPhno2) {
		this.emergencyPhno2 = emergencyPhno2;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getResponseCode() {
		return responsecode;
	}

	public void setResponseCode(String responsecode) {
		this.responsecode = responsecode;
	}
}
