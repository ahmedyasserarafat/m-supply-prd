package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class genericCompExamp {

	public static void main(String[] args) {
		Person one=new Person("DA",56);
		Person two=new Person("BB",57);
		Person three=new Person("CC",54);
		
		List<Person> list=new ArrayList() {{
			add(one);add(two);add(three);}};
			
			
			
			//Collections.sort(list,Comparator.comparing(Person::getAge));
			Comparator<Person> pe=(Person p1,Person p2)->Integer.valueOf(((Person) p1).getAge()).compareTo(Integer.valueOf(((Person) p2).getAge()));
			Collections.sort(list,pe);
			System.out.println(list);
			
			List<Integer> id=Arrays.asList(3,2,1);
			Collections.sort(id,Integer::compare);
		
			System.out.println(id);
			
			Collections.sort(list,new ReverseComp<Person>(new ageComp()));
			
			System.out.println(list);
	}

}
