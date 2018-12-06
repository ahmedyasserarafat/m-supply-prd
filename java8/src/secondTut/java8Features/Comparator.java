package secondTut.java8Features;

import java.util.function.Function;

@FunctionalInterface
public interface Comparator<T> extends java.util.Comparator<T>{

	int compare(T o1,T o2);
  
	@SuppressWarnings("unchecked")
	static <U,K> Comparator<U> comparing(Function<U, ? extends Comparable<K>> fp1) {
		
		return (U p1, U p2)-> { 
			//System.out.println(p1+"--"+p2);
			return fp1.apply(p1).compareTo((K) fp1.apply(p2)) ;
			};
	}

	@SuppressWarnings("unchecked")
	static <T,K> Comparator<T> comparing1(Function<T, ? extends Comparable<K>> fp1) {
		
		return (p1, p2)-> { 
			//System.out.println(p1+"--"+p2);
			return fp1.apply(p1).compareTo((K) fp1.apply(p2)) ;
			};
			}

	
	static  Comparator<Person> comparingOld(Function<Person, String> fp1) {
		
		return ( p1,  p2)-> { 
			//System.out.println(p1+"--"+p2);
			return fp1.apply(p1).compareTo(fp1.apply(p2)) ;
			};
			}

	
	default  Comparator<T> comparing2(Comparator<T> ct3) {
		
		 return (p1,p2) ->  { 
			 System.out.println("p1");
			return  compare(p1,p2)==0 ? ct3.compare(p1,p2):compare(p1,p2);
		 };
		 
			 
	}
	
	default  <U extends Comparable<? super Number>> Comparator<T> comparing3(Function<T, ? super U> fp1,Comparator<T> ct3) {
		
		 return (p1,p2) ->  { 
			 System.out.println("p1");
			return  compare(p1,p2)==0 ? ct3.compare(p1,p2):compare(p1,p2);
		 };
		 
			 
	}


	default <K> Comparator<T> thenComparingCustom(Function<T, ? extends Comparable<K>> fp2){
		
		return comparing2(comparing1(fp2));
		
	}
	}

