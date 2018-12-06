package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class genericCompExamp_1 {

	public static void main(String[] args) {
		Person one=new Person("DA",56);
		Person two=new Person("BB",57);
		Person three=new Person("CC1",53);
		Person four=new Person("CC",54);
		Person five=new Person("DD",50);
		
		List<Person> list=new ArrayList() {{
			add(one);add(two);add(three);}};
			
		Person ct=(Person)min(list,new ageComp());
			System.out.println(ct);
			
	}
	
	public static Object min(List values,Comparator ct) {
		
		Object val=values.get(0);
		
		for(int i=0;i<values.size();i++) {
			
			Object val1=values.get(i);
			if(ct.compare(val1, val)<0) {
				val=val1;
			}
		}
		
		return val;
	}

}
