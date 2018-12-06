
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
public class MapExamples {
 
    public static void main(String[] args) {
        Map<String, Integer> prices = new HashMap<>();
        System.out.println("Prices mapo: " + prices);
 
        // Integer::sum(a,b) is perfect two-argument
        // function (BiFunction)
        prices.merge("fruits", 3, (oldVal, newVal) -> {
            System.out.printf("Old val: %d, new val: %d%n",
                    oldVal, newVal);
            return null;
        });

        System.out.println("Prices map1: " + prices);
        prices.merge("fruits", 5, (oldVal, newVal) -> {
            System.out.printf("Old val: %d, new val: %d%n",
                    oldVal, newVal);
            return oldVal+newVal;
        });

        System.out.println("Prices map2: " + prices);
 
        // null removes mapping for the key:
        prices.merge("fruits", 7, (oldVal, newVal) -> {
            System.out.printf("Old val: %d, new val: %d%n",
                    oldVal, newVal);
            return null;
        });
        System.out.println("Prices map3: " + prices);
 
        prices.put("veggies", null);
        System.out.println("Prices map4: " + prices);
        // No need to handle initial null value:
        prices.merge("veggies", 45, ((s,j)->{
        	return null;
        }));
    
        System.out.println("Prices map5: " + prices);
        
        Stream.iterate(1, (Integer n) -> n + 1)
        .peek(n -> System.out.println("number generated: - " + n))
        .filter(n -> (n % 2 == 0))
        .peek(n -> System.out.println("Even number filter passed for - " + n))
        .limit(5)
        .count();
        
        List<Integer> list = Arrays.asList(10,11,12);
        list.stream().map(i->{System.out.println("peek "+i*i);return i*i;}).forEach(System.out::println);
        
        BinaryOperator<Integer> bf1=Integer::sum;
		
		Optional<Integer> res2=list.stream().reduce(bf1);;
		System.out.println("res -----"+res2.get());
		System.out.println(res2.isPresent());
		
		List<String> versions = new ArrayList<>(); 
		versions.add("Lollipop"); 
		versions.add("KitKat"); versions.add("Jelly Bean"); versions.add("Ice Cream Sandwidch"); versions.add("Honeycomb"); versions.add("Gingerbread"); 

		Set<String> jj=versions.stream() .filter(s -> s.length() > 7) 
		.peek(e -> System.out.println("After the first filter: " + e)) 
		.filter(e -> e.startsWith("H")) .peek(e -> System.out.println("After the second filter: " + e)) .collect(Collectors.toSet());

		System.out.println(jj);
		
		
       
    }
}

