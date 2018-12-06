package generics;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class personLoader {
	
	private RandomAccessFile file;

	public personLoader(RandomAccessFile file) throws FileNotFoundException {
		this.file = new RandomAccessFile("persons.txt", "rw");
	}
	
	public void load(Person person) {
		
	
		
	}
	
	
	

}
