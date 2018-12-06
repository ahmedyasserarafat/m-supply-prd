package com.plural.java8;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.print.DocFlavor.STRING;

import sun.java2d.x11.X11SurfaceDataProxy.Opaque;

public class fourthLamdaExpression {

	public static void main(String[] args) {
		List<Person> people= Arrays.asList(
				new Person("DA",56),
				new Person("BB",57),
				new Person("BB",54)
				);
		
		BiFunction<List, Person, List> bif=(list1,person)->{
			
			list1.add(person.getAge());return list1;
		};
		BinaryOperator<List> combiner=(i1,i2)->{i1.addAll(i2);return i1;};
		List<Integer> ff=people.parallelStream().reduce(new ArrayList(),bif, combiner);
		
		
	
		ArrayList<Integer> yy=new ArrayList<Integer>();
		
		Consumer<Integer> d1= o-> {yy.add(o);System.out.println(yy);};
		

		Consumer<? super Integer> d= System.out::println;
		
		ff.forEach(d);
		System.out.println("-------------");
			List<Integer> dd= Arrays.asList(1,2,3);
		
		Stream<Integer> ss=dd.stream();
		
		BinaryOperator<Integer> bf=(i1,i2)->i1>i2?i1:i2;;
	
		Integer res1=ss.reduce(0, bf);
		System.out.println(res1);
		
		
		
		//Optional
		List<Integer> dd1= Arrays.asList(1,8,4);
		
		Stream<Integer> ss1=dd1.stream();
		
		BinaryOperator<Integer> bf1=(i1,i2)->Math.max(i1, i2);
		
		Optional<Integer> res2=ss1.reduce(bf1);
		
		System.out.println(res2);
		
		Predicate<Integer> pf=Predicate.isEqual(2);
		Supplier<Integer> sp=()->9;
	System.out.println(res2.filter(pf).orElseGet(sp));
		
		
		
	/*	List<Person> list= Arrays.asList(
				new Person("DA",56),
				new Person("BB",57),
				new Person("BB",54)
				);
		
		Stream<String> hh=list.stream().filter(p1->p1.getAge()>54).map(p->p.getName());
		
		hh.forEach(s->System.out.println(s));*/
		
		//Stream<String> gg=list.stream().map(p->p.getName()).filter(p1->p1.length()>1);
		//gg.forEach(System.out::println);
		
		
		List<Person> list= Arrays.asList(
				new Person("DA",56),
				new Person("BB",57),
				new Person("BB",54)
				);
		
		Set<String> hh=list.stream().filter(p1->p1.getAge()>54).map(Person::getName).collect(Collectors.toSet());
		System.out.println(hh);
		//hh.forEach(System.out::println);
		
		
	
	}
	
	

}
