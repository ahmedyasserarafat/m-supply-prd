package thirdTut.java8Features;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class optinaltest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Double> result=new ArrayList<>();
		
		ThreadLocalRandom.current().doubles(10_000).boxed()/*.parallel()*/
		.forEach(
				d->NewMath.inv(d)
					.ifPresent(
						inv1-> {
							System.out.println(inv1);
							Optional.of(inv1).ifPresent(inv12->result.add(Math.sqrt(inv12)));
							}
						   )
						);
		
		//System.out.println(result);
		//System.out.println(result.size());
		
		
		
		Function<Double,Stream<Double>> flatMapper=d->NewMath.inv(d).flatMap(sqr->NewMath.sqrt(sqr)).
				map(ss->Stream.of(ss)).orElseGet(Stream::empty);
				
		List<Double> result1=ThreadLocalRandom.current().doubles(10_000).parallel().boxed().flatMap(flatMapper).collect(Collectors.toList());
		
		System.out.println(result1);
		System.out.println(result1.size());
	}

}
