package com.plural.java8;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class optionalTest {
public static void main(String[] args) {
/*	  Optional<String> gender = Optional.of("MALE");
      String answer1 = "Yes";
      String answer2 = null;

      System.out.println("Non-Empty Optional:" + gender);
      System.out.println("Non-Empty Optional: Gender value : " + gender.get());
      System.out.println("Empty Optional: " + Optional.empty());

      System.out.println("ofNullable on Non-Empty Optional: " + Optional.ofNullable(answer1));
      System.out.println("ofNullable on Empty Optional: " + Optional.ofNullable(answer2));

     
      System.out.println("ofNullable on Non-Empty Optional: " + Optional.of(answer2));*/
	
	
	Employee emp1=new Employee("1", "FullStack");
	personEmp p1=new personEmp(Optional.of(emp1), "33");
	
	Employee emp2=new Employee("2", "UI");
	personEmp p2=new personEmp(Optional.of(emp2), "32");
	
	Employee emp3=new Employee("3", "JAVA");
	personEmp p3=new personEmp(Optional.of(emp3), "34");
	

	Employee emp4=new Employee("4", "FullStack");
	personEmp p4=new personEmp(Optional.of(emp4), "34");
	
	List<personEmp> list=new ArrayList();
	
	list.add(p1);list.add(p2);list.add(p3);list.add(p4);
	
	empService.getEmpNames(list);
	
	
	
}
}

class empService {
	
	public static void getEmpNames(List<personEmp> list1){
		//List<Optional<String>> optList=list1.stream().map(p->p.getEp()).map(p->p.flatMap(por->Optional.of(por.getEmpname()))).collect(Collectors.toList());
		
		//System.out.println(optList);
		//optList.forEach(op->System.out.println(op.get()));
		//map(Employee::getEmpname).forEach(System.out::println);
		
		Map<Object, Object>  kk=
				list1.stream().collect(Collectors.toMap(op->op.getAge(), op->op.getEp(),(op, op1)->{
					return op;
				}));
		
		//Map<Object, String>  kk1=
				//list1.stream().collect(Collectors.groupingBy(op->op.getAge(),Collectors.mapping(op1->op1.getEp(),Collectors.toCollection(()->new TreeSet()))));
		  List<Item> items = Arrays.asList(
	                new Item("apple", 10, new BigDecimal("9.99")),
	                new Item("banana", 20, new BigDecimal("19.99")),
	                new Item("orang", 10, new BigDecimal("29.99")),
	                new Item("watermelon", 10, new BigDecimal("29.99")),
	                new Item("papaya", 20, new BigDecimal("9.99")),
	                new Item("apple", 10, new BigDecimal("9.99")),
	                new Item("banana", 10, new BigDecimal("19.99")),
	                new Item("apple", 20, new BigDecimal("9.99"))
	        );

	        Map<String, Long> counting = items.stream().collect(
	                Collectors.groupingBy(Item::getName, Collectors.counting()));

	        System.out.println(counting);
	        
	        Map<Object, Long> kk1 =
	                list1.stream().collect(
	                        Collectors.groupingBy(op->op.getAge(),Collectors.counting()));
	                

		//System.out.println(kk1);
		
		   Map<Object, Set<Object>> kk2 =
	                list1.stream().collect(
	                        Collectors.groupingBy(op->op.getAge(),Collectors.mapping(op->op.getEp(),Collectors.toSet())));
		   System.out.println(kk2);
		   
/*		   Map<Object, Set<Object>> kk2 =
	                list1.stream().collect(
	                        Collectors.groupingBy(op->op.getAge(),Collectors.mapping(op->op.getEp(),
	                        		Collectors.toCollection(()->new ArrayList()))));*/
		   List<String> list = Arrays.asList("Mukesh", "Vishal", "Amar");
		    String result = list.parallelStream().collect(StringBuilder::new,
		    		(response, element) -> {
		    			 
		    				response.append(" ").append(element);
		    			},
		    		(response1, response2) -> { 
		    			
		    		response1.append(",").append(response2.toString());
		    		})
		    		.toString();
		    System.out.println("Result: " + result);
		
}
}




