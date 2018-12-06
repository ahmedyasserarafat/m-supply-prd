package com.plural.java8;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;

public class immutable {

	public static void main(String[] args) {
		imm mi=new imm("test");
		System.out.println(mi.getFf());
		mi.getFf().setMonth(4);
		System.out.println(mi.getFf());
		System.out.println(mi.Galaxy());
		
		String str1="Hi";
		String str2="bye";
		str1=str1.concat(str2);
		//System.out.println(str1);
		String str3=str1;
		//System.out.println(str3);
		HashMap<String,String> hh=new HashMap<String,String>();
		
		
		str1="hi";
		hh.put(str1, "1");hh.put(str3, "2");
		
		str1="tt";
		hh.entrySet().stream().forEach(d->System.out.println(d.getKey() +"--"+d.getValue()));
		System.out.println("----");
		//hh.entrySet().stream().forEach(d->System.out.println(d.getKey() +"--"+d.getValue() +"--"+ZoneId.systemDefault()));
		System.out.println(ZoneId.systemDefault());
		System.out.println(LocalDateTime.now(ZoneId.systemDefault())+" -- "+LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
		
		System.out.println(LocalDateTime.now(ZoneId.of("Europe/London"))+" -- "+ZonedDateTime.now(Clock.systemUTC()));
		System.out.println(ZonedDateTime.now() +"--"+ZonedDateTime.now(ZoneId.of("Europe/London")));
		System.out.println(Instant.now());
		//System.out.println(str1 +"-----"+str3);
		
		//System.out.println(LocalDate.parse("2018-06-12").with(TemporalAdjusters.lastDayOfYear()));
		
		
		
		Clock wholeMinuteClock = Clock.tickMinutes(ZoneId.of("Europe/London"));
		Clock wholeSecondClock = Clock.tickSeconds(ZoneId.of("Asia/Kolkata"));
	
		System.out.println(wholeMinuteClock +"--"+wholeSecondClock);
	}
}


 final class imm {
	 
	 private final String name;
	 private final Date ff;
	 
	 
	 
	public imm(String name) {
		
		this.name = name;
		this.ff=new Date();
	}
	
	 
	private imm(String name, Date ff) {
		this.name = name;
		this.ff = new Date(ff.getTime());
	}

	public String getName() {
		return name;
	}
	public Date getFf() {
		return new Date(this.ff.getTime());
	}
	
	public imm Galaxy() {
		
		return new imm(this.name,getFf());
	}


	@Override
	public String toString() {
		return "imm [name=" + name + ", ff=" + ff + "]";
	}
	
	
	 
	 
	
}
