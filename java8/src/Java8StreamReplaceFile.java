
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

public class Java8StreamReplaceFile {
 public static void main(String[] args)  {
	 String fileName="/home/ahmed/Lunaworkspace/java8/src/com/cts/java8/";
	 Path path=Paths.get(fileName);
	try(  Stream<Path> filePath=Files.list(path);
			 
		
			){
		Stream<Path> kk1=filePath.filter(pathName->pathName.getFileName().toString().endsWith("DisplayFeatures.java"))
				.map(koi->{
					try(Stream<String> ss=Files.lines(koi))
					{
						Optional<String> kk= ss.filter(line->Objects.nonNull(line)).findFirst().map(line->
						("package com.cts.java8\n;").concat(line));
						//filter(line->line.contains(";import"))
						//.map(line->line.replaceAll("import", "\nimport"));
						
						Files.write(koi, kk.get().getBytes(), StandardOpenOption.SPARSE);
						System.out.println(kk.get());
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
