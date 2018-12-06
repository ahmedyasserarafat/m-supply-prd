package secondTut.java8Features;

public class MainPredicate {
	
	public static void main(String[] args) {
		
		Predicate<String> p1=s1->s1.length()<20;
		Predicate<String> p2=s1->s1.length()>5;
		
		Predicate<String> p3=p1.and(p2);
		System.out.println(p3.test("Arafat"));
		
		Predicate<String> p5=Predicate.isEquals("yes");
		System.out.println(p5.test("yes"));
	}

}
