package com.plural.java8;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class thirdLamdaExpression {

	public static void main(String[] args) {
		
		List<String> dd= Arrays.asList("1","3","2");
		
		Stream<String> ss=dd.stream();
		
		ArrayList yy=new ArrayList();
		
		Consumer<String> d1= o-> {yy.add(o);};
		

		Consumer<String> d= System.out::println;
		
		
		//ss.forEach(d1.andThen(d));
		
		//System.out.println("ss "+yy.size());
		
		
		
		
		/*Stream<String> fu=Stream.of("one","two","three","four","five","six");
		
		Predicate<String> pu=s->s.length()<4;
		
		Predicate<String> st=Predicate.isEqual("two");
		Predicate<String> st1=Predicate.isEqual("three");
		
		//fu.forEach(d);
		//fu.filter(st.or(st1)).forEach(d);
		//fu.filter(st.or(st1));

		//fu.peek(d).filter(st.or(st1)).peek(d1);
		//System.out.println("yy "+yy.size());
		

		fu.peek(d).filter(st.or(st1)).forEach(d1);
		
		System.out.println("for each "+yy.size());
		*/
		
		
		List<Person> list11= Arrays.asList(
				new Person("AA",56),
				new Person("BB",57)
				);
		
		Stream<Person> str=list11.stream();
		Consumer<Person> strc= System.out::println;
		
		Stream<String> strmap=str.map(p->p.getName());
		strmap.forEach(d);
		
		List<Integer> list1=Arrays.asList(1,2,3,4,5,6,7);
		List<Integer> list2=Arrays.asList(2,3,4);
		List<Integer> list3=Arrays.asList(4,8);
		
		List<List<Integer>> list= Arrays.asList(
				list1,list2,list3);
		
		//System.out.println(list);
		
		Function<List<Integer>, Integer> mmap=s->s.size();
		
		Stream<List<Integer>>  sl=list.stream();
		
		sl.map(mmap).forEach(System.out::println);
		System.out.println("--");
		//FlatMap
		
		Function<List<Integer>, Stream<Integer>> mmap1=l->l.stream();
		list.stream().flatMap(mmap1).forEach(System.out::println);
		
		
	}
	
	

}
