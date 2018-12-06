package generics;

import java.time.LocalDate;

public class Employee extends Person {


	public Employee(String name, int age) {
		super(name,age);
		
	}

	public Employee(String name, int age, LocalDate dob) {
		super(name, age, dob);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [getName()=" + getName() + ", getAge()=" + getAge() + ", getDob()=" + getDob()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

	
}
