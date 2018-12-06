package com.plural.java8;

public class Employee {
	
	private String empId;
	private String empname;
	
	
	public Employee(String empId, String empname) {
		super();
		this.empId = empId;
		this.empname = empname;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empname=" + empname + "]";
	}
	
	
}
