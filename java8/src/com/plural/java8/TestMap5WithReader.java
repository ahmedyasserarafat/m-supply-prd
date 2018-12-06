package com.plural.java8;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;



public class TestMap5WithReader {
	public static void main(String[] args) throws IOException {
		
		List<Person> persons=new ArrayList<Person>();
		
		Path p1=Paths.get("persons.txt");

		try(Stream<String> st=Files.lines(p1)){
			st.filter(line->line.contains("86")).findFirst().ifPresent(s->System.out.println(s));
		}
		
		/*try(Stream<Path> st=Files.list(Paths.get("/Users"))){
			st.filter(pt->{
				//System.out.println(pt.toFile().isDirectory());
				return pt.toFile().isDirectory();
			}
					).forEach(s->System.out.println(s));
			}*/
		
		try(Stream<Path> st=Files.walk(Paths.get("/Users"),2)){
			st.filter(pt->{
				System.out.println("pppp  t"+pt.toFile().isDirectory());
				return pt.toFile().isDirectory();
			}
					).forEach(s->System.out.println(s));
			}
		
		
		try(
				
				BufferedReader reader=new BufferedReader(new InputStreamReader(TestMap5WithReader.class.getResourceAsStream("persons.txt")));
				
				){
			Stream<String> st=reader.lines();
			
			Optional<String> opt=reader.lines().filter(line->line.contains("Y&*")).findFirst();
			System.out.println("Ooooooo "+opt.isPresent());
			
			  reader.lines().filter(line->line.contains("6")).findFirst().ifPresent(s->System.out.println(s));
			  
			  st.close();
			  st=reader.lines();
			  st.map(line->{
				   String h[]=line.split(" ");
					Person p=new Person(h[0], Integer.parseInt(h[1]));
					persons.add(p);
					return p;
			   }).forEach(s->System.out.println(s));
			  
			
		}
		
	}

}
