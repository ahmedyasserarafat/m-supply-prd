package com.polaris.canvas.properties.reader;
import java.util.Map;





/**
 * Base class for all Control Data pages search criteria.
 * 
 * @author ctsuser1
 *
 */
public  class ControlDataSearchCriteria {
	public String ivUser;
	protected String region="dd";
	protected String salesOrg;
	protected String roSegment;
	protected String salesDistrict;
	protected String accountId;
	protected String hqId;
	protected String accountName;
	protected String hqName;
	protected String IV_ACTION="SEARCH";
	public ControlDataSearchCriteria(){}
	public ControlDataSearchCriteria(Map httpParams){
	}
	
	protected String getRegion() {
		return region;
	}
	protected void setRegion(String region) {
		this.region = region;
	}

	public String getRoSegment() {
		return roSegment;
	}
	public void setRoSegment(String roSegment) {
		this.roSegment = roSegment;
	}

	public String getSalesOrg() {
		return salesOrg;
	}
	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}
	public String getSalesDistrict() {
		return salesDistrict;
	}
	public void setSalesDistrict(String salesDistrict) {
		this.salesDistrict = salesDistrict;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getHqId() {
		return hqId;
	}
	public void setHqId(String hqId) {
		this.hqId = hqId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getHqName() {
		return hqName;
	}

	public void setHqName(String hqName) {
		this.hqName = hqName;
	}
	public String getIvUser() {
		return ivUser;
	}
	public void setIvUser(String ivUser) {
		this.ivUser = ivUser;
	}
	@Override
	public String toString() {
		return "accountId=" + accountId
				+ ", accountName=" + accountName + ", hqId=" + hqId
				+ ", hqName=" + hqName + ", ivUser=" + ivUser + ", region="
				+ region + ", roSegment=" + roSegment + ", saleDistrict="
				+ salesDistrict + ", saleOrg=" + salesOrg + " ";
	}
}
