
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.plural.java8.Person;

public class Java8StreamReplaceFile2 {
 public static void main(String[] args)  {
	 String fileName="/home/ahmed/Lunaworkspace/java8/src/com/plural/java8/";
	 Path path=Paths.get(fileName);
	try(  Stream<Path> filePath=Files.list(path);
			 
		
			){
		Stream<Path> kk1=filePath.filter(pathName->pathName.getFileName().toString().endsWith(".java"))
				.map(koi->{
					 StringBuffer sb=new StringBuffer();
				         sb.append("package com.plural.java8;").append("\r\n");
					try(Stream<String> st=Files.lines(koi))
					{
						 
						
						  st.map(line->{
							  sb.append(line).append("\r\n");;
								return line;
						   }).forEach(s->System.out.println(s));
						  //System.out.println(sb.toString());
						  Files.write(koi, 
					     			sb.toString().getBytes(), StandardOpenOption.WRITE);
					}
					 catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return koi;
					
					});
		
		kk1.forEach(System.out::println);
		
/*	Optional<String> kk= ss.filter(line->line.contains("import")).findFirst().map(line->
	new StringBuilder().append("package com.cts.java8;\n\n"
			+ "").append(line).append("\n").append("\n")
	.toString());
	
	Files.write(path, kk.get().getBytes(), StandardOpenOption.SPARSE);
	System.out.println(kk.get());*/
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
