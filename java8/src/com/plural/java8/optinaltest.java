package com.plural.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import thirdTut.java8Features.NewMath;

public class optinaltest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Double> result=new ArrayList<>();
		
		List<String> ffs=new ArrayList<>();
		
		List<String> hh=ffs.stream().filter(s->s.length()>0).collect(Collectors.toList());
		System.out.println(hh.size());
		
		
		ThreadLocalRandom.current().doubles(10_000).boxed()/*.parallel()*/
		.forEach(
				d->NewMath.inv(d)
					.ifPresent(
						inv1-> {
							Optional.of(inv1).ifPresent(inv12->result.add(Math.sqrt(inv12)));
							}
						   )
						);
		
		//System.out.println(result);
		//System.out.println(result.size());
		
		Optional<Double> ff=NewMath.inv(1.23d);
		
		Function<Double,Stream<Double>> flatMapper=d->NewMath.inv(d).flatMap(sqr->{ return NewMath.sqrt(sqr);}).
				map(ss->{ 
					System.out.println(ss);
					return Stream.of(ss);
					}).orElseGet(()->Stream.empty());
				
		List<Double> result1=ThreadLocalRandom.current().doubles(10_000).parallel().map(d->d*20-10).
				boxed().flatMap(flatMapper).collect(Collectors.toList());
		
		System.out.println(result1);
		System.out.println(result1.size());
	}

}
