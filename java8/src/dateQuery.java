import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.util.Date;


public class dateQuery {
	public static void main(String[] args) {
		LocalDate ld=LocalDate.now();
		//ld.atStartOfDay().atZone(ZoneId.systemDefault());
		//long dd=ChronoUnit.DAYS.between(LocalDate.of(2017, Month.DECEMBER, 10), ld);
		//System.out.println(dd);
		String hh=LocalDate.now().minus(Period.ofMonths(2)).with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		System.out.println(hh);
		Date gg=Date.from(Instant.now(Clock.system(ZoneId.of("Asia/Kolkata"))));
		LocalDateTime jj=gg.toInstant().atZone(ZoneId.of("Asia/Kolkata")).toLocalDateTime();
		System.out.println(jj);
		//TemporalQuery<Quater> qr=new TQuery();
		//Quater a=ld.query(qr);
		TemporalQuery<Chronology> qr=TemporalQueries.chronology();
		Chronology a=ld.query(qr);
		System.out.println("----a "+a.getCalendarType());
		
	    TemporalQuery<ZoneId> query = TemporalQueries.zone();

	    LocalDateTime date = LocalDateTime.of(2014, Month.DECEMBER, 02, 0, 0);
	    ZonedDateTime zonedDate = ZonedDateTime.of(date,
	            ZoneId.of("Pacific/Chatham"));

	    ZoneId zoneId = zonedDate.query(query);

	    //System.out.println(zoneId); // "Pacific/Chatham"
	}

}


class TQuery implements TemporalQuery<Quater>{

	@Override
	public Quater queryFrom(TemporalAccessor temporal) {
		// TODO Auto-generated method stub
		LocalDate date = LocalDate.from(temporal);
		if((date.isBefore(LocalDate.now().withMonth(4).withDayOfMonth(1)) || date.isEqual(LocalDate.now().withMonth(4).withDayOfMonth(1)))){
			return Quater.FIRST;
		}
		return null;
		
		//date.with
	}
	
}


enum Quater {
	
	FIRST,
	
	SECOND,
	
	THIRD,
	
	FOURTH
}
