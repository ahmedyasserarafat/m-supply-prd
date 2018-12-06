package generics;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.OBJ_ADAPTER;

public class personGetterClassUpperBoundedWildCards {

	public static void main(String[] args) throws FileNotFoundException {
	
		
		Partner one=new Partner("DA",56);
		Partner two=new Partner("BB",57);
		Employee three=new Employee("CC1",53);
		
		personSaver ps=new personSaver(new RandomAccessFile("persons.txt", "rw"));
		
		//ps.save(one);
		
		List<Partner> pers=new ArrayList<>();
		pers.add(one);pers.add(two);
		//pers.add(three);
		
		ps.saveAllUpperBound(pers);
		
		System.out.println("--------");
		
		List<Object> perss=new ArrayList<>();
		ps.loadllUpperBound(perss);
		
		
	System.out.println("--------");
		
		List<? super Object> pers1=new ArrayList<>();
		pers1.add(new Object());
		pers1.add(one);
		
		
	}
	
	
  
}

class  personholder<T extends Person>{
	
	T get(Object val) {
		return (T)val;
		
	}

}