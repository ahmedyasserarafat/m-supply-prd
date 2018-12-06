package secondTut.java8Features;

@FunctionalInterface
public interface Predicate<T> {

	boolean test(T t);

	default Predicate<T> and(Predicate<T> p2){
		
		return (pr)->(test(pr) && p2.test(pr));
			
	}

	static <U> Predicate<U> isEquals(U string) {
		
	return (U s)->s.equals(string);
		
	}
	
}
