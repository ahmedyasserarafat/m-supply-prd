package com.plural.java8;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;



public class secondLamdaExpression {

	public static void main(String[] args) {
		
	
		//Comparator<String> cl=(s1,s2) -> s1.compareTo(s2);
		Comparator<String> c2=new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			
		};
		
		Comparator<String> cl= String::compareTo;
		
		List<String> dd= Arrays.asList("1","3","2","");
		Collections.sort(dd,cl.reversed());
		BiFunction<String, String,Integer> mmap=(s1,s2)->s1.compareTo(s2);
		
		
		Collections.sort(dd,Comparator.nullsLast((s1,s2)->s1.toString().trim().compareTo(s2.toString().trim())));
		
		for(String ff:dd) {
			System.out.println("dd "+ff);
		}
		
		
		List<Person> list= Arrays.asList(
				new Person("DA",56),
				new Person("BB",57),
				new Person("BB",54)
				);
		Comparator<Person> pe=(p1,p2)->Integer.valueOf(p2.getAge()).compareTo(Integer.valueOf(p1.getAge()));
		Collections.sort(list,pe);
		/**------------------------Method inference ----------------------------------**/
		
		//Collections.sort(list,Comparator.comparing(Person::getAge));
		for(Person ff:list) {
			System.out.println("dd "+ff);
		}
		
		ArrayList yy=new ArrayList();
		Consumer<String> d= System.out::println;
		d.accept("dd");
		Consumer<String> d1= o->{
			
		yy.add(o);
		};
		
		dd.forEach(d.andThen(d1));
		System.out.println("yy "+yy);
		
		}
		
		
		
	}
	
	


