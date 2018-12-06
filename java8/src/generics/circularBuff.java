package generics;
import java.util.Arrays;

import generics.CircularBuffer;

public class circularBuff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Person one=new Person("DA",56);
		Person two=new Person("BB",57);
		Person three=new Person("CC",54);
		
		
		Person[] p= {one,two,three};
		//
		Person [] p1=Arrays.copyOf(p, p.length+1);
		System.out.println(Arrays.toString(p1));
		p1[p.length]=	new Person("DD",54);
		System.out.println(Arrays.toString(p1));
		
		
	CircularBufferTypeGeneric<String> cb=new CircularBufferTypeGeneric<>(10);
	
	cb.offer("one");cb.offer("two");cb.offer("three");
	Concatenate(cb);

	}
	
	static void Concatenate(CircularBufferTypeGeneric<String> cb) {
		
		StringBuffer sb=new StringBuffer();
		sb.append(cb.poll());sb.append(cb.poll());sb.append(cb.poll());
		
		System.out.println(sb.toString());
	}
	
	

}
