import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.print.DocFlavor.STRING;

import sun.java2d.x11.X11SurfaceDataProxy.Opaque;

public class fourthLamdaExpression {

	public static void main(String[] args) {
		
	
		ArrayList yy=new ArrayList();
		
		Consumer<Integer> d1= o-> {yy.add(o);System.out.println(yy);};
		

		Consumer<Integer> d= System.out::println;
		
			List<Integer> dd= Arrays.asList(1,2,3);
		
		Stream<Integer> ss=dd.stream();
		
		BinaryOperator<Integer> bf=(i1,i2)->i1>i2?i1:i2;;
	
		Integer res1=ss.reduce(0, bf);
		System.out.println(res1);
		System.out.println("---- "+Arrays.asList(-1,-2,-3).stream().reduce(0, bf));
		
		
		
		//Optional
		List<Integer> dd1= Arrays.asList(1,8,4,4);
		
		Stream<Integer> ss1=dd1.stream();
		Optional<Integer> dde=dd1.stream().max(Comparator.naturalOrder());
		System.out.println("--jjjj-"+dde);
		BinaryOperator<Integer> bf1=(i1,i2)->Math.min(i1, i2);
		BinaryOperator<Integer> bf2=Integer::max;
		
		Optional<Integer> res2=ss1.reduce(bf1);
		
		System.out.println(res2);
		
		Predicate<Integer> pf=Predicate.isEqual(4);
		Supplier<Integer> sp=()->9;
	System.out.println("oooo "+res2.filter(pf).orElseGet(sp));

	System.out.println("oooo1 "+dd1.stream().filter(pf.or(Predicate.isEqual(4))).findAny().orElseGet(sp));
		
	//System.out.println("oooo3 "+dd1.stream().filter(pf));
	dd1.stream().filter(pf).forEach(d);

		
	/*List<Person> list= Arrays.asList(
				new Person("DA",56),
				new Person("BB",57),
				new Person("BB",54)
				);
		
		Stream<String> hh=list.stream().filter(p1->p1.getAge()>54).map(p->p.getName());
		
		hh.forEach(s->System.out.println(s));*/
		
		//Stream<String> gg=list.stream().map(p->p.getName()).filter(p1->p1.length()>1);
		//gg.forEach(System.out::println);
		
		
		List<Person> list= Arrays.asList(
				new Person("DA",56),
				new Person("BB",57),
				new Person("BB",54)
				);
		
		Set<String> hh=list.stream().filter(p1->p1.getAge()>54).map(Person::getName).collect(Collectors.toSet());
		System.out.println(hh);
		//hh.forEach(System.out::println);
		
		
	
	}
	
	

}
