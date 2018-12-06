package generics;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class personGetterClassArray {

	public static void main(String[] args) throws FileNotFoundException {
	
		
		Partner one=new Partner("DA",56);
		Partner two=new Partner("BB",57);
		Employee three=new Employee("CC1",53);
		
		personSaver ps=new personSaver(new RandomAccessFile("persons.txt", "rw"));
		
		
	/*	Person per=new Person("Per",55);
		ps.save(three);*/
		
		/*Partner par[]=new Partner[2];
		Person per[]=par;
		par[0]=one;
		par[1]=two;
		
		ps.saveAll(par);*/
		
	/*	Partner par[]=new Partner[2];
		Person per[]=par;
		per[0]=one;
		per[1]=two;
		
		ps.saveAll(par);*/
		
		/*Employee par[]=new Employee[2];
		Person per[]=par;
		par[0]=one;
		par[1]=two;
		
		ps.saveAll(par);*/
		
		
		List<Person> pers=new ArrayList<>();
		pers.add(one);pers.add(two);
		
		ps.saveAllList(pers);
	}

}
