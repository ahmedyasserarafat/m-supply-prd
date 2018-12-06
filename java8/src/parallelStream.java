import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class parallelStream {
public static void main(String[] args) throws InterruptedException {
	/*List<String> list1=Arrays.asList("Ali","Abu Moosa","Zainab","Fathima","Ayesha","Barakah");
	
	List<String> list2=new ArrayList<String>();
	
	list2=list1.stream().map(s->s.toUpperCase()).
	sorted(Comparator.comparingInt((String d)->d.length()).thenComparing(Comparator.naturalOrder()).
	collect(Collectors.toList());
	System.out.println(list2);*/
	//list1.parallelStream().peek(s->interupption(500L)).sorted(Comparator.naturalOrder()).forEachOrdered(System.out::println);
	
/*List<Integer> list1=Collections.synchronizedList(new ArrayList());
	
	List<Integer> list2=new ArrayList<Integer>();
	
	list2=IntStream.range(0, 20).parallel().boxed().peek(list1::add).collect(Collectors.toList());
	
	System.out.println(list1);
	System.out.println(list2);*/
	
	/*long sum[]={0L};
	Long[] ss=new Long[]{0L};
	AtomicLong Al=new AtomicLong(0);
	LongStream.rangeClosed(0, 100_000_000L).parallel().forEach(i->Al.addAndGet(i));
	System.out.println(Al.get());
	
	long sum1=LongStream.rangeClosed(0, 100_000_000L).sum();
	System.out.println(sum1);
	
	long sum1P=LongStream.rangeClosed(0, 100_000_000L).parallel().sum();
	System.out.println("sum1P "+sum1P);
	
	long sum2=LongStream.rangeClosed(0, 100_000_000L).reduce(0, (a, b) -> a+b);
	System.out.println(sum2);
	
	long sum2P=LongStream.rangeClosed(0, 100_000_000L).parallel().reduce(0, (a, b) -> a+b);
	System.out.println("sum2P "+sum2P);*/
	
	
	
	
}

public static void  interupption(long millis) {
	try {
		Thread.sleep(millis);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
