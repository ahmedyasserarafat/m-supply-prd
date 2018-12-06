package thirdTut.java8Features;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class splitIterator {

	public static void main(String[] args) {
		
		Path path=Paths.get("/home/ahmed/Lunaworkspace/java8/src/persons2.txt");
		try(Stream<String> sr=Files.lines(path)){
			
			Spliterator<String> lineSplitter=sr.spliterator();
			Spliterator<Person> people=new PersonSplitIterator<Person>(lineSplitter);
			
			Stream<Person> per=StreamSupport.stream(people, false);
			per.forEach(System.out::println);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
