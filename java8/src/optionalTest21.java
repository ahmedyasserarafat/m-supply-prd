import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class optionalTest21 {
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
	
	List<personEmp> list=new ArrayList();
	
	list.add(p1);list.add(p2);list.add(p3);
	
	
	empService1.getEmpNames(list);
	
	
	
	
	
	
}
}

class empService1 {
	
	public static void getEmpNames(List<personEmp> list){
		//List<Optional<String>> optList=list1.stream().map(p->p.getEp()).map(p->p.flatMap(por->Optional.of(por.getEmpname()))).collect(Collectors.toList());
		list.parallelStream().map(mapper->{
			//System.out.println(mapper);
			Optional<String> empName=Optional.of(mapper).flatMap(personEmp::getEp).flatMap(emp->Optional.of(emp.getEmpname()));
			//System.out.println(empName);
			return empName.get();
		}).forEach(System.out::println);
		//System.out.println(dd);
	//System.out.println(empName.get());
}
}


	
	
	

	
	
