import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sun.text.SupplementaryCharacterData;

public class streamIterateAndOptional {
	
	public static void main(String[] args) {
		
		Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 2);
		 
		// when
		List<Integer> collect = infiniteStream
		  .limit(10)
		  .collect(Collectors.toList());
		System.out.println(collect);
		
		Stream.iterate(0, i -> i + 1).limit(10).forEach(System.out::println);
	
		Stream.generate(UUID::randomUUID).skip(10).limit(10).forEach(System.out::println);
		
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
		 
		
		
		
		
		Function<List<String>, Integer> dd=s->{
			System.out.println(s);
			return s.size();
			};
		Supplier<Stream<List<String>>> namesOriginalList =()-> Stream.of(
				Arrays.asList("Pankaj","Hi","pp"), 
				Arrays.asList("David", "Lisa","op"),
				Arrays.asList("David1", "Lisa1","op1"),
				Arrays.asList("Amit"));
			//flat the stream from List<String> to String stream
				
				//.flatMap(strList -> strList.stream()).collect(Collectors.toList());
		
	Map<Integer, List<String>> hh=namesOriginalList.get().map(gr->gr).collect(Collectors.toMap(List::size,e->e,(list1, list2)->
	{
		
	
	
	
	//s2.add("Test");
		//s1.stream().forEach(p->s2.add(p));
		return new ArrayList<String>() {{
			addAll(list1);
			addAll(list2);
		}};
	
	}));
		
		
		Stream<String> jj=namesOriginalList.get().flatMap(h->h.stream());
		List<String> kk=jj.map(j->j).collect(Collectors.toList());
		System.out.println(kk);
		
				
				System.out.println(hh);

			//flatStream.forEach(System.out::println);
	}
}
