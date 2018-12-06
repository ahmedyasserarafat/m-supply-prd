package com.polaris.servlets;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
public class HttpsUrlConnection {
   public static void main(String[] args) {
     // String sURL = args[0];
      try {
         URL oURL = new URL("http://localhost:9080/store/set_status");
         URLConnection con = oURL.openConnection();

// Testing connection type
         if (con instanceof URLConnection) 
            System.out.println("This is a URLConnection.");
         if (con instanceof HttpURLConnection)
            System.out.println("This is a HttpURLConnection.");
         if (con instanceof HttpsURLConnection)
            System.out.println("This is a HttpsURLConnection.");
         System.out.println("Connection object: "+con.toString());

// Reading data
         BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        /* String line;
         while ((line = in.readLine()) != null) 
            System.out.println(line);
         in.close();*/

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
