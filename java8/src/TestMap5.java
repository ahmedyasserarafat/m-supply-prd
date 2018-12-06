import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMap5 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<Person> persons=new ArrayList<Person>();
		
try(
				
				BufferedReader reader=new BufferedReader(new InputStreamReader(TestMap5WithReader.class.getResourceAsStream("persons.txt")));
				
				){
			Stream<String> st=reader.lines();
			
			 
			  
			  st.close();
			  st=reader.lines();
			  st.map(line->{
				   String h[]=line.split(" ");
					Person p=new Person(h[0], Integer.parseInt(h[1]));
					persons.add(p);
					return p;
			   }).collect(Collectors.toSet());
			  
			  List<Person> l1=persons.subList(0, 3);
			  List<Person> l2=persons.subList(3, persons.size());
			  Map<Integer,List<Person>> map1=getMapList(l1);
					  map1.forEach((k,v)->System.out.println(k+"----"+v));
			  
			  Map<Integer,List<Person>>map2= getMapList(l2);
					  map2.forEach((k,v)->System.out.println(k+"----"+v));
					  
					  map1.entrySet().stream().forEach(
							  entry->{
								  System.out.println("eeeeee "+entry);
								  map2.merge(entry.getKey(), entry.getValue(), (l4,l5)->{
									  System.out.println(entry.getKey()+"-kv-"+ entry.getValue());
									 System.out.println("l4 "+l4 +"--");
									 System.out.println("l4 "+l5);
									l5.addAll(l4);
									 return l4;
								  });
							  }
							  );
					  
					  map1.forEach((k,v)->System.out.println(k+"--ttt--"+v));

	}


}
	


	
	private  static Map<Integer,List<Person>> getMapList(List<Person> list) {
		
		return list.stream().collect(Collectors.groupingBy(Person::getAge));
		
	}
}
