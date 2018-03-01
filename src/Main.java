import java.time.LocalDateTime;
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
    }

    private static String formatTomorrow(LocalDateTime today) {
        LocalDateTime tomorrow = today.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        String formatDateTime = tomorrow.format(formatter);
        return formatDateTime;
    }
}
