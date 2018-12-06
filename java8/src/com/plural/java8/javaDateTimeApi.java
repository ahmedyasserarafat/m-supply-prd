package com.plural.java8;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class javaDateTimeApi {

	public static void main(String[] args) {

		BufferedReader hh=new BufferedReader(new InputStreamReader(javaDateTimeApi.class.getResourceAsStream("persons.txt")));
		ArrayList<Person> persons=new ArrayList<Person>();
		Stream<String> stream=hh.lines();
		Function<String, Person> map=line->{
			String h[]=line.split(" ");
			int year=Integer.parseInt(h[2]);
			Month month=Month.of(Integer.parseInt(h[3]));
			int day=Integer.parseInt(h[4]);
			Person p=new Person(h[0], Integer.parseInt(h[1]),LocalDate.of(year, month, day));
			/**-------------------------------------------Not Thread Safe---------------------------------**/
			persons.add(p);
			return p;

		}; 


		/**---------------------------------------Thread Safe Synchronized ----------------------------
		hh.lines().map(map).collect(Collectors.toList());**/

		stream.map(map).forEach(System.out::println);

		persons.stream().forEach(person -> {
			LocalDate dob=person.getDob();
			Period gg=Period.between(dob, LocalDate.now());
			System.out.println(gg.getYears() +"-/-"+gg.getMonths()+"--"+gg.getDays());
			System.out.println(ChronoUnit.DAYS.between(dob, dob.plusMonths(1)));
			//System.out.println(dob.until(LocalDate.now(),ChronoUnit.MONTHS));
			//Instant dd= Instant.MAX;
			//System.out.println(dd);

			LocalDate now=LocalDate.now();

			System.out.println(now.with(TemporalAdjusters.dayOfWeekInMonth(5, DayOfWeek.FRIDAY))); 
			//System.out.println("--- "+now.with(Month.JUNE).withDayOfMonth(10));
			// System.out.println(ZoneId.getAvailableZoneIds());
			System.out.println("FFFFF "+dob.format(DateTimeFormatter.ISO_DATE) +"---"+dob.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

			LocalDate hhf=LocalDate.parse(dob.toString());
			System.out.println(hhf.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

		});
		
		Date gg=Date.from(Instant.now(Clock.system(ZoneId.of("Asia/Dubai"))));
		System.out.println(Instant.now(Clock.system(ZoneId.systemDefault())));
		LocalDateTime jj=gg.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		System.out.println(jj);
		//TimeZone.getTimeZone("UTC")
		LocalDateTime dt=LocalDateTime.now(ZoneId.of("GMT"));
		System.out.println("dt "+dt);
		//Date to=Date.from(jj.toInstant(ZoneOffset.UTC));
		String hhhh=LocalDateTime.now().minusDays(1).truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_DATE_TIME);
		System.out.println("hhhhhhhhhh--"+hhhh);
	}
	// TODO Auto-generated method stub




}
