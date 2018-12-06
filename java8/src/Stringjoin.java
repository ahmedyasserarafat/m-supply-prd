import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stringjoin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s="Hello World";
		IntStream g=s.codePoints();
		Stream<Character> ff=g.mapToObj(letter-> (char)letter);
		Stream<Character> chr=ff.map(ch->Character.toUpperCase(ch));
		//chr.forEach(System.out::println);
		//System.out.println(g);
		
		StringJoiner s1=new StringJoiner(",","{","}");
		s1.add("one");s1.add("two");s1.add("three");
		System.out.println(s1.toString());
		
		String s2=String.join(",","one","two","three");
		System.out.println(s2.toString());
		Set<String> gyu=new HashSet<String>();gyu.add("one");gyu.add("two");gyu.add("three");
		s2=String.join(",",gyu);
		System.out.println(s2.toString());
		
		IntStream.range(0, 10).forEach(
				nbr -> System.out.println(nbr)
			);

	}

}
