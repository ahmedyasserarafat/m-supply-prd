package com.plural.java8;
import java.util.Optional;

public class personEmp {
	
	private Optional<Employee> ep;
	private String age;
	
	
	public Optional<Employee> getEp() {
		return ep;
	}
	public void setEp(Optional<Employee> ep) {
		this.ep = ep;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public personEmp(Optional<Employee> ep, String age) {
		super();
		this.ep = ep;
		this.age = age;
	}
	@Override
	public String toString() {
		return "personEmp [ep=" + ep + ", age=" + age + "]";
	}
	
	

	
	
}
