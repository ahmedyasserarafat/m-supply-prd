package thirdTut.java8Features;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PersonSplitIterator<T> implements Spliterator<T> {
	
	
		private Spliterator<String> lineSpliterator=null;
		private String name;
		private int age;
		private String city;;
	
	public PersonSplitIterator(Spliterator<String> lineSpliterator) {
		this.lineSpliterator = lineSpliterator;
		}

	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		if(this.lineSpliterator.tryAdvance(line->this.name=line) && 
		this.lineSpliterator.tryAdvance(line->this.age=Integer.parseInt(line)) &&
		this.lineSpliterator.tryAdvance(line->this.city=line)) {
		Person p=new Person(name, age, city);
		action.accept((T) p);
		return true;
		}else{
		return false;
		}
	}

	@Override
	public Spliterator<T> trySplit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long estimateSize() {
		// TODO Auto-generated method stub
		return (lineSpliterator.estimateSize())/3;
	}

	@Override
	public int characteristics() {
		// TODO Auto-generated method stub
		return lineSpliterator.characteristics();
	}

	

}
