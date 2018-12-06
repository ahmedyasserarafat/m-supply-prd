
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeExample {
   public static void main(String[] args) {

      LocalDateTime localDateTime = LocalDateTime.now();

      OffsetDateTime offsetDateTime1 = OffsetDateTime.of(localDateTime,
            ZoneOffset.ofHoursMinutes(5, 30));

      OffsetDateTime offsetDateTime2 = OffsetDateTime.of(localDateTime,
            ZoneOffset.ofHoursMinutes(5, 30));

      OffsetDateTime offsetDateTime3 = OffsetDateTime.of(localDateTime,
            ZoneOffset.ofHoursMinutes(6, 30));
      
      System.out.println(offsetDateTime1 +"--"+offsetDateTime2+"--"+offsetDateTime3);

      // Using isEqual()
      if (offsetDateTime1.isEqual(offsetDateTime2)) {
         System.out.println("offsetDateTime1 and offsetDateTime2 are equal.");
      } else {
         System.out.println("offsetDateTime1 and offsetDateTime2 are not equal.");
      }

      // Using compareTo()
      if (offsetDateTime1.compareTo(offsetDateTime2) == 0) {
         System.out.println("offsetDateTime1 and offsetDateTime2 are equal.");
      } else {
         System.out.println("offsetDateTime1 and offsetDateTime2 are not equal.");
      }

      // Using isAfter()
      if (offsetDateTime1.isAfter(offsetDateTime3)) {
         System.out.println("offsetDateTime1 is after offsetDateTime3");
      }

      // Using isBefore()
      if (offsetDateTime3.isBefore(offsetDateTime2)) {
         System.out.println("offsetDateTime3 is before offsetDateTime2");
      }
   }
}
