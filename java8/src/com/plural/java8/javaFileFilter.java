package com.plural.java8;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class javaFileFilter implements FilenameFilter {

	@Override
	public boolean accept(File pathname,String ext) {
		// TODO Auto-generated method stub
		return ext.endsWith(".java");
	}

}
