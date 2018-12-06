package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class genericCompExamp_2 {

	public static void main(String[] args) {
		Person one=new Person("DA",56);
		Person three=new Person("CC",53);
		Person two=new Person("BB",57);
		
		List<Person> list=new ArrayList() {{ add(one);add(two);add(three); }};
			
		Person ct=min(list,new ageComp<Person>());
	
			System.out.println(ct);
			
	}
	
	public static <T> T min(List<T> values,Comparator<T> ct) {
		
		T val=values.get(0);
		
		for(int i=0;i<values.size();i++) {
			
			T val1=values.get(i);
			if(ct.compare(val1, val)<0) {
				val=val1;
			}
			
		}
		
		return (T)val;
	}

}
