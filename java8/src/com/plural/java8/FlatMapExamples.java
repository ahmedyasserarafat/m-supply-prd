package com.plural.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FlatMapExamples {
	
	public static void main(String[] args) {
		
		Consumer<String> d= System.out::println;
		try(Stream<String> s1=Files.lines(Paths.get("/Users/administrator/eclipse-workspace/Sample/src/thirdTut/java8Features/TomSayer1.txt"));
				Stream<String> s2=Files.lines(Paths.get("/Users/administrator/eclipse-workspace/Sample/src/thirdTut/java8Features/TomSayer2.txt"));	
				){
			Stream<Stream<String>> streamOfStreams=Stream.of(s1,s2);
			Function<Stream<String>,Stream<String>> flatMapper=(stream)->(stream);
			Function<String,Stream<String>> lineMapper=(line)->{
				return Pattern.compile(" ").splitAsStream(line);
				};
			streamOfStreams.flatMap(flatMapper).flatMap(lineMapper).forEach(d);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
