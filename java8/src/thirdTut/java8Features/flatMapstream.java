package thirdTut.java8Features;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

public class flatMapstream {
	
	public static void main(String[] args) throws IOException {
		try(
				BufferedReader br=new BufferedReader(new InputStreamReader(new flatMapstream().getClass().getResourceAsStream("/TM.txt")));
				BufferedReader br1=new BufferedReader(new InputStreamReader(new flatMapstream().getClass().getResourceAsStream("/TM1.txt")));
				){
			
			 Stream<Stream<String>> sr = Stream.of(br.lines(),br1.lines());
			 
			 System.out.println(sr);
			 
			 Function<Stream<String>,Stream<String>> fs1=pk->pk;
			//List<String> list=sr.flatMap(Function.identity()).collect(Collectors.toList());
			//System.out.println(list);
					//.forEach(System.out::println);
			 sr.flatMap(fs1).flatMap(line->
			 {
				 System.out.println(line);
			 return java.util.regex.Pattern.compile(" ").splitAsStream(line);
			 }
					 ).forEach(System.out::println);
			 
			 int scrb[]= {1,3,5,6,7,8,9};
			 Function<String,Integer> fs=word->word.chars().map(letter->{
				 System.out.println((char)letter);
				 System.out.println(scrb[letter-'a']);
				 return scrb[letter-'a'];
						 }).sum();
			 System.out.println(fs.apply("abcd"));
	}

}
}