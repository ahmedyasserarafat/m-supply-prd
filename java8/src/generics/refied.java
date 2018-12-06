package generics;

import java.util.AbstractList;
import java.util.List;

public class refied {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	/*	String[] vv=new String[3];
		Object[] objects=vv;
		objects[0]=1;
		System.out.println(objects);*/
	
		List<Integer> dd=new customArrayList<>();
		dd.add(1);dd.add(2);dd.add(4);dd.add(5);
		System.out.println(dd);

}
	
}

