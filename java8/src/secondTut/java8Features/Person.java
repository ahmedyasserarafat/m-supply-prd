package secondTut.java8Features;
import java.time.LocalDate;

public class Person {
	
	private String firstName;
	private String lastName;
	private int age;
	private LocalDate dob;
	
	public Person(String firstName, String lastName, int age) {
		// TODO Auto-generated constructor stub
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	public Person(String firstName, String lastName, int age, LocalDate dob) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.dob = dob;
	}
	

	public Person(String firstName, int age) {
		this.firstName = firstName;
		this.age = age;
	}
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", dob=" + dob + "]";
	}

	
}
