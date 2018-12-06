package generics;
import java.time.LocalDate;

public class Person {
	
	private String name;
	private int age;
	private LocalDate dob;
	

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public Person(String name, int age, LocalDate dob) {
		super();
		this.name = name;
		this.age = age;
		this.dob = dob;
	}
	public Person(String name, String name1) {
		super();
		this.name = name;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", dob=" + dob + "]";
	}
	

	
}
