package com.plural.java8;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStreamReader;
import java.util.Date;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.text.DateFormatter;

import com.sun.javafx.geom.transform.GeneralTransform3D;

public class javaDateTimeApi1 {

	public static void main(String[] args) {
		
		LocalDateTime bb=LocalDateTime.of(2016, 02, 03, 04, 51,25);
		
		String hhhh=bb.minusDays(1).truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.MEDIUM));
		
		//LocalDate---------
		System.out.println(hhhh);
		
		
		
		javaDateTimeApi1.test(0);
		System.out.println(ZoneId.getAvailableZoneIds());
	/*	LocalDateTime ld=LocalDateTime.now(ZoneId.of("Singapore"));
		System.out.println(ld +"----"+LocalDateTime.now(Clock.systemUTC()));
		System.out.println(ld.toLocalDate().minusYears(1).isBefore(LocalDate.now()));
		System.out.println(LocalDate.now().atStartOfDay() +"---"+LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));*/
		
		//LocalTime -----------------  
		LocalTime lt=LocalTime.now();
		
		//LocalTime lt1=LocalTime.parse("06:31",DateTimeFormatter.ofPattern("HH:mm a"));
		
		String lt2=LocalTime.of(06,45).plus(1, ChronoUnit.HOURS).format(DateTimeFormatter.ofPattern("hh:mm a"));
		//System.out.println(lt2);
				
		
		ZonedDateTime zd= ZonedDateTime.of(2018,02,27,1,30,0,0, ZoneId.of("Europe/London"));
		System.out.println(zd +"--"+LocalDateTime.now(ZoneId.of("Europe/London")));
		
		System.out.println(ZonedDateTime.now(ZoneId.of("Europe/London"))+"--"+LocalDateTime.now(ZoneId.of("Europe/London"))+"-----"+OffsetDateTime.now());
		
		LocalDateTime ldt=LocalDateTime.of(2015, Month.FEBRUARY, 20,06,30);
		
		OffsetDateTime od=OffsetDateTime.of(ldt, ZoneOffset.of("+02:00"));
		System.out.println("od "+od);
		
		 LocalDate from=LocalDate.now();
		 LocalDate to=LocalDate.now().plus(Period.ofDays(5));
		 Period pd=Period.between(from, to);
		System.out.println(pd.getDays());
		
		
		LocalTime lt4=lt.plus(Duration.ofMinutes(02));
		
		
		System.out.println(Duration.between(lt, lt4).getSeconds());
		System.out.println(ChronoUnit.SECONDS.between(lt, lt4));
		
		LocalDateTime plt=LocalDateTime.of(LocalDate.now(),LocalTime.now().plusSeconds(120));
		System.out.println("p-lt "+plt);
		
		Date nn=new Date();
		LocalDateTime ldg=LocalDateTime.ofInstant(nn.toInstant(), ZoneId.systemDefault());
		//System.out.println(LocalDate.now().withDayOfMonth(12));
		System.out.println(ldg.plusYears(3));
		LocalDateTime jki=ldg.plusYears(3);
		//jki=jki.withYear(2020);
		
		jki=jki.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		System.out.println(jki);

	}
		// TODO Auto-generated method stub

	private static void test(int year) {
		AtomicInteger atomicInteger = new AtomicInteger(year);
		List<DayOfWeek> ggr=Stream.of(Month.values()).map(month -> {
			System.out.println("mmmmm "+month);
			int dfe=LocalDate.now().plusYears(atomicInteger.getAndIncrement()).getYear();
			System.out.println("year  "+dfe);
			LocalDate llk=LocalDate.now().with(month).withYear(dfe);
			System.out.println("llk "+llk +"--"+llk.getDayOfWeek());
			return llk.getDayOfWeek();
		}).collect(Collectors.toList());
		
     System.out.println(ggr);
	}


}
