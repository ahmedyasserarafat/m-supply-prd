package com.plural.java8;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.imageio.plugins.common.InputStreamAdapter;
import com.sun.xml.internal.ws.wsdl.parser.InaccessibleWSDLException;

import jdk.internal.org.xml.sax.InputSource;

public class TestLamdAndColl {
	
	
	public static <T> void main(String[] args) throws IOException {
	
/*	File f=new File("/Users/administrator/eclipse-workspace/Sample/src/");
	
	FileFilter ff=pathName -> pathName.getName().endsWith(".java");
	
	File fl[] =f.listFiles(ff);
	
	for(File tt:fl) {
		System.out.println(tt);
	}*/
		
		//Second 
		
		List<String> dd= Arrays.asList("1","3","2","");
		
		//Consumer<String> cs1=()
				
		Comparator<String> cp=(s1,s2)->s1.toString().trim().compareTo(s2.toString().trim());
				
		Collections.sort(dd,cp);
		//Collections.sort(dd,cp);
		for(String bb:dd) {
			//System.out.println(bb);
		}
		
		
		
		List<Person> list= Arrays.asList(
				new Person("DA",56),
				new Person("BB",57),
				new Person("BB",54)
				);
		
		//Comparator<Person> cpe=(s1,s2)->Integer.valueOf(s1.getAge()).compareTo(Integer.valueOf(s2.getAge()));
		
		
		
		Collections.sort(list,Comparator.comparing(Person::getAge).reversed());
		//Collections.sort(dd,cp);
		for(Person bb:list) {
			//System.out.println(bb);
		}
		
		
		ArrayList yy=new ArrayList();
		Consumer<Person>  vv=System.out::println;
		Consumer<Person>  vv1=yy::add;
     //list.forEach(vv.andThen(vv1));
	//System.out.println(yy.size());
		
		Stream<Person>  sm=list.stream();
		
		System.out.println(sm.count());
		
		Stream<String> fu=Stream.of("one","two","three","fourr","five","six");
		
		Predicate<String>  ps=Predicate.isEqual("two");
		Predicate<Integer>  ps2=Predicate.isEqual(2);
		Predicate<String>  ps1=(p)->p.length()>4;
		
		//fu.filter(ps1).filter(ps).forEach(System.out::println);
		
		
		fu.peek(sw->{
			System.out.println("sw  "+sw);
		}
	).filter(ps1).forEach(System.out::println);
		
		
		List<Person> list11= Arrays.asList(
				new Person("AA",56),
				new Person("BB",57)
				);
		
		Stream<Person> sld=list11.stream();
		
		Function<Person, String> mmap=Person::getName;
		Stream<String> kk=sld.filter(p->p.getAge()>56).map(mmap);
		kk.forEach(System.out::println);
		
		List<Integer> list1=Arrays.asList(1,2,3,4,5,6,7);
		List<Integer> list2=Arrays.asList(2,3,4);
		List<Integer> list3=Arrays.asList(4,8);
		
		List<List<Integer>> listd= Arrays.asList(
				list1,list2,list3);
		
		Function<List<Integer>, Integer> mmap1=s->s.size();
		Function<List<Integer>, Stream<Integer>> fap1=s->s.stream();
		Stream<List<Integer>> oo=listd.stream();
		//oo.map(mmap1).forEach(System.out::println);
		//oo.filter(p->p.contains(2)).flatMap(fap1).forEach(System.out::println);
		Stream<Integer> hh=oo.filter(p->p.contains(2)).flatMap(fap1);
		hh.filter(Predicate.isEqual(2)).forEach(System.out::println);
		
		BinaryOperator<Integer> bf=(i1,i2)->Math.max(i1,i2);
		//BinaryOperator<Integer> uu= (p1,Person p2)->p1.getAge()+p2.getAge();
		
		Predicate<Integer> pf=Predicate.isEqual(7);
		Supplier<Integer> sp=()->9;
		Integer gl=list1.stream().reduce(0,bf);
		System.out.println(gl);
		
		
		
	
		ArrayList persons=new ArrayList();
		try(	BufferedReader gg=new BufferedReader(new InputStreamReader(TestLamdAndColl.class.getResourceAsStream("persons.txt")))){
			
			Stream<String> dds=gg.lines();
			Function<String, Person> hhj=line->{
				String h[]=line.split(" ");
				Person p=new Person(h[0], Integer.parseInt(h[1]));
				persons.add(p);
				return p;
	
			};
			
			dds.map(hhj).forEach(vv1);
			System.out.println(yy.size());
			
			Optional<Person> opt1=yy.stream().max(Comparator.comparing(Person::getAge));
			System.out.println(opt1);
			
			Set<Person> kkd=(Set<Person>) yy.stream().collect(Collectors.toSet());
			System.out.println(kkd);
			
			Map<Integer,Set<String>> lop=(Map<Integer,Set<String>>)yy.stream().collect(
					Collectors.groupingBy(Person::getAge,Collectors.mapping(Person::getName,Collectors.joining()))
		);
			System.out.println(lop);
			
		}
		
		
}
}
