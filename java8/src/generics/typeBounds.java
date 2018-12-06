package generics;

public class typeBounds {
public static void main(String[] args) {
	
	SortedPair<Integer> ss=new SortedPair(5, 4);
	System.out.println(ss.getFirst() +"--"+ss.getSecond());
}
}




class SortedPair<T extends Comparable<T>>{
	
	private T first;
	private T second;
	
	
	
	public SortedPair(T left, T right) {
		super();
		
		if(left.compareTo(right)<0) {
			this.first = left;
			this.second = right;
		}else {
			this.first = right;
			this.second = left;
		}
	
	}



	public T getFirst() {
		return first;
	}



	public void setFirst(T first) {
		this.first = first;
	}



	public T getSecond() {
		return second;
	}



	public void setSecond(T second) {
		this.second = second;
	}

	
	
	
}

