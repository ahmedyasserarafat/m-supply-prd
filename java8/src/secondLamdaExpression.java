import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.sun.org.apache.xml.internal.security.c14n.helper.C14nHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;



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
		//Collections.sort(dd,cl.reversed());
		//Function<String, String,Integer> mmap=(s1,s2)->s1.compareTo(anotherString);
		
		
		Collections.sort(dd,Comparator.nullsLast((s1,s2)->s1.toString().trim().compareTo(s2.toString().trim())));
		
		for(String ff:dd) {
			System.out.println("dd "+ff);
		}
		
		
		List<Person> list= Arrays.asList(
				new Person("DA",56),
				new Person("BB",57),
				new Person("BB",54)
				);
		Comparator<Person> pe=(Person p1,Person p2)->Integer.valueOf(((Person) p2).getAge()).compareTo(Integer.valueOf(((Person) p1).getAge()));
		Collections.sort(list,pe);
		
		for(Object ff:list) {
			System.out.println("dd "+(Person)ff);
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
	
	


