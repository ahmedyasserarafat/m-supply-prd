package com.plural.java8;
import java.io.File;
import java.io.FileFilter;

public class firstLamdaExpression {

	public static void main(String[] args) {
		File[] dd=null;
		
		FileFilter filter = new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.getName().endsWith(".java");
			}
		};
		
		File f=new File("/Users/administrator/eclipse-workspace/Sample/src/");
		 dd=f.listFiles(filter);
		for(File d:dd) {
			System.out.println(d.getName());
		}
		
		
		javaFileFilter filter1=new javaFileFilter();
		File f1=new File("/Users/administrator/eclipse-workspace/Sample/src/");
		dd=f1.listFiles(filter1);
		for(File d:dd) {
			System.out.println(d.getName());
		}
		
		System.out.println("--------");
		FileFilter ffFilter=(File pathname) -> pathname.getName().endsWith(".java");
		File f2=new File("/Users/administrator/eclipse-workspace/Sample/src/");
		dd=f1.listFiles(ffFilter);
		for(File d:dd) {
			System.out.println(d.getName());
		}
		
		
		
	}
	
	

}
