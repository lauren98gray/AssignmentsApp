import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n\nHello, AssignmentsApp!\n");

        // Output current date-time
        LocalDateTime current = LocalDateTime.now();
        System.out.println("The current date-time is " + current);

        // Output tomorrow's date using a formatter
        String formattedTomorrow = formatTomorrow(current);
        System.out.println("Tomorrow's date is " + formattedTomorrow);

        // Add 5 weeks to today's LocalDateTime
        LocalDateTime fiveWeeksAdded = current.plusWeeks(5);
        System.out.println("Today's date plus 5 weeks is " + fiveWeeksAdded);

        // Initialize LocalDateTime object to your birthdate at 12:35PM
        LocalDateTime birthdate = LocalDateTime.of(1998, 12, 3, 12, 35);

        // Output the day of the week that you were born
        DayOfWeek birthday = birthdate.getDayOfWeek();
        System.out.println("I was born on a " + birthday);

        // Output the number of days you've been alive
        long numDaysSince = getNumDaysSince(birthdate, current);
        System.out.println("I have been alive for " + numDaysSince + " days.");
    }

    private static long getNumDaysSince(LocalDateTime earlierDate, LocalDateTime mostRecentDate) {
        LocalDate earlierToLD = earlierDate.toLocalDate();
        LocalDate recentToLD = mostRecentDate.toLocalDate();
        long earlierSinceEpoch = earlierToLD.toEpochDay();
        long recentSinceEpoch = recentToLD.toEpochDay();
        long numDaysSince = recentSinceEpoch - earlierSinceEpoch;
        return numDaysSince;
    }

    private static String formatTomorrow(LocalDateTime today) {
        LocalDateTime tomorrow = today.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        String formatDateTime = tomorrow.format(formatter);
        return formatDateTime;
    }
}
