package generics;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;

public class personSaver {
	
	private RandomAccessFile file;

	public personSaver(RandomAccessFile file) throws FileNotFoundException {
		this.file = file;
	}
	
	public void save(Person per) {
		
		System.out.println(per);
		
	}
	
	
	public void saveAll(Person[] per) {
		
		for(Person pr:per) {
			save(pr);
		}
		
	}
	public void saveAllUpperBound(List<? extends Person> pers) {

		for(Person pr:pers) {
			save(pr);
		}
		
	}
	
	public <T extends Person> void saveAllUpperBound1(List<T> pers) {

		for(Person pr:pers) {
			save(pr);
		}
		
	}
	public void saveAllList(List<Person> pers) {

		for(Person pr:pers) {
			save(pr);
		}
		
	}
	
	
	public void loadllUpperBound(List<? super Person> pers) {
		Partner one=new Partner("DA",56);
		Partner two=new Partner("BB",57);
		pers.add(one);pers.add(two);
		for(Object pr:pers) {
			System.out.println(pers);
		}
		
	}

}
