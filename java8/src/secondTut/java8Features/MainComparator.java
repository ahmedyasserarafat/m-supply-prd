package secondTut.java8Features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import secondTut.java8Features.Comparator;
import java.util.List;
import java.util.function.Function;

import com.sun.org.apache.bcel.internal.classfile.Code;

public class MainComparator {
	
	public static void main(String[] args) {

		List<Person> list= Arrays.asList(
				new Person("B1", "BLZ", 26),
				new Person("A1", "ALZ", 28),
				new Person("C1", "CLZ", 25),
				new Person("D1", "DLZ", 29)
				);
	/*ArrayList<Person> al = new ArrayList<Person>() {{
		addAll(list);
		}};*/
				
		java.util.Comparator<Person> dd=new java.util.Comparator<Person>() {
			
			@Override
			public int compare(Person o1, Person o2) {
				// TODO Auto-generated method stub
				//System.out.println("lLLLL "+(o1.getAge()-o2.getAge()));
				return ((Integer)o1.getAge()).compareTo((Integer)o2.getAge());
			}
		};
		Collections.sort(list,dd);
		
		//System.out.println(list);
		
		
		Comparator<Person> c1=(p1,p2) ->p1.getAge()-p2.getAge();
		Collections.sort(list,c1);
	
		
		Comparator<Person> c2=(Person p1,Person p2) ->Integer.valueOf(p1.getAge()).compareTo(Integer.valueOf(p2.getAge()));
		
		Comparator<Person>  c3=(p1,p2)->p1.getFirstName().compareTo(p2.getFirstName());
		
		
		Function<Person,Integer> fp1= Person::getAge;
		Function<Person,String> fp2= p1->p1.getFirstName();
		Function<Person,String> fp3= Person::getLastName;
			
		Function<Person,Integer> fp4= p1->p1.getAge();

		
		Comparator<Person> cmpPersonAge=Comparator.comparing1(fp2);
	     Comparator<Person> cmpPersonDirstName=cmpPersonAge.thenComparingCustom(Person::getLastName);
		
		Collections.sort(list,cmpPersonDirstName);
		
	
		list.forEach(p->System.out.println(p.getAge() +"---"+p.getFirstName() +"---"+p.getLastName()));
				
	}

}
