
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.io.IOUtils;

public class Java8StreamReplaceFile1 {
 public static void main(String[] args) throws IOException  {
	 BufferedReader reader = null;

     try {
         reader = new BufferedReader(new FileReader("/home/ahmed/Lunaworkspace/java8/src/com/plural/java8/ScreenResolution.java"));
         String tmp;
         StringBuffer sb=new StringBuffer();
         sb.append("package com.plural.java8;").append("\r\n");
         while ((tmp = reader.readLine()) != null)
            // list.add(tmp);
        	 sb.append(tmp).append("\r\n");

         
         
     	Files.write(Paths.get("/home/ahmed/Lunaworkspace/java8/src/com/plural/java8/ScreenResolution.java"), 
     			sb.toString().getBytes(), StandardOpenOption.WRITE);
       /*  writer = new BufferedWriter(new FileWriter("src/lt/test.txt"));
         for (int i = 0; i < list.size(); i++)
             writer.write(list.get(i) + "\r\n");*/
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         IOUtils.closeQuietly(reader);
        
     }
}
}
