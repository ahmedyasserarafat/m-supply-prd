package com.plural.java8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class collectorsExample {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)  {


		ArrayList<? super String> personName=new ArrayList();

		Consumer<String> d1= o-> {personName.add(o);System.out.println(personName);};


		Consumer<Person> d= System.out::println;


		/*	


		List<Person> list= Arrays.asList(
				new Person("DA",56),
				new Person("BB",57),
				new Person("BB",54)
				);

		List<String> hh=list.stream().filter(p1->p1.getAge()>54).map(p->p.getName()).collect(Collectors.toList());
		System.out.println(hh);*/
		//hh.forEach(System.out::println);
		ArrayList<Person> persons=new ArrayList<Person>();

		try(
				BufferedReader br=new BufferedReader(new InputStreamReader(new collectorsExample().getClass().getResourceAsStream("Persons.txt")));
				){
			
			Stream<String> sr=br.lines();
			
			
			Function<String, Person> mmap=line -> {
				String hh[]=line.split(" ");
				Person p=new Person(hh[0], Integer.parseInt(hh[1]));
				persons.add(p);
				return p;
				
			};
			
			sr.map(mmap).forEach(System.out::println);;
			
			
			System.out.println(persons.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stream<Person> personlist=persons.stream();
		
		Comparator<Person> pe=(p1,p2)->Integer.valueOf(p1.getAge()).compareTo(Integer.valueOf(p2.getAge()));
		//Optional<Person> opt4=personlist.filter(p->p.getAge()>26).max(Comparator.comparing(Person::getAge));
		Optional<Person> opt=personlist.filter(p->p.getAge()>26).min(pe);
		System.out.println(opt);
		
		Optional<Person> opt1=persons.stream().max(pe);
		System.out.println(opt1);
		
		Map<Integer,List<Person>> ps1=(Map<Integer, List<Person>>) persons.stream().collect(Collectors.groupingBy(
				Person::getAge
				));
		System.out.println(ps1);
		
		Map<Integer,Set<String>> ps=(Map<Integer, Set<String>>) persons.stream().collect(Collectors.groupingBy(
				Person::getAge,
				Collectors.mapping(Person::getName,Collectors.toCollection(()->new TreeSet()))
				));
		System.out.println(ps);
		
		
		
		Map<Integer, String> ps3=persons.stream().collect(Collectors.groupingBy(
				Person::getAge,
				Collectors.mapping(Person::getName,Collectors.joining())
				));
		System.out.println(ps3);

	}



}
