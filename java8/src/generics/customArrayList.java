package generics;

import java.util.AbstractList;

public class customArrayList<E> extends AbstractList<E>{
	 
	E values[];
	
	public customArrayList() {
		
		this.values = (E[]) new Object[0];
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return values[index];
	}
	
	public boolean add(E o) {
		// TODO Auto-generated method stub
		E[] newValue=(E[]) new Object[size()+1];
		
		for(int i=0;i<size();i++) {
			newValue[i]=values[i];
		}
		newValue[size()]=o;
		values=newValue;
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return values.length;
	}
	
}