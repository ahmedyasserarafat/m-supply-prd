package generics;

import java.time.LocalDate;

public class Partner extends Person {

	public Partner(String name, int age, LocalDate dob) {
		super(name, age, dob);
		// TODO Auto-generated constructor stub
	}
	
	public Partner(String name, int age) {
		super(name,age);
		
	}

	@Override
	public String toString() {
		return "Partner [getName()=" + getName() + ", getAge()=" + getAge() + ", getDob()=" + getDob() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}


