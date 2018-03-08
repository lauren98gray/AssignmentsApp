

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

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
        System.out.println("\nI was born on a " + birthday);

        // Output the number of days you've been alive
        long numDaysAlive = ChronoUnit.DAYS.between(birthdate, current);
        System.out.println("I have been alive for " + numDaysAlive + " days.");

        // Output the number of days between two dates
        long numDaysBetween = ChronoUnit.DAYS.between(current, fiveWeeksAdded);
        System.out.println("\nThere are " + numDaysBetween + " days between " + current + " and " + fiveWeeksAdded + ".");

        // Given two dates, output the earlier
        LocalDateTime earlierDate = findEarlierDate(current, fiveWeeksAdded);
        System.out.println("\nEarlier date: " + earlierDate);

        // Create a file with 100 random "month/day/year hour:minutes" on each line
        ArrayList<String> listOfDates = generateRandomDates(100);
        createFile("randomDates.txt", listOfDates);

        // Store data from the file into an ArrayList of LocalDateTime objects
        ArrayList<LocalDateTime> dataInFile = createArrayListFromFileDate("randomDates.txt");
        System.out.println("\nArrayList of LocalDateTime objects in file: " + dataInFile);

        //Output the number of stored dates in the year [Y]
        int year = 2092;
        int numDatesinYear = countNumDatesInYear(dataInFile, year);
        System.out.println("\nThere are " + numDatesinYear + " stored dates in the year " + year + ".");

        //Count the number of stored dates in the current year
        int numDatesCurrentYear = countNumDatesInYear(dataInFile, current.getYear());
        System.out.println("There are " + numDatesCurrentYear + " stored dates in the current year.");

        //Count the number of duplicates
        int numDuplicates = countNumDuplicates(dataInFile);
        System.out.println("\nThere are " + numDuplicates + " duplicates in this file.");

        //Sort the dates in chronological order
        Collections.sort(dataInFile);
        System.out.println("\nDates in chronological order: " + dataInFile);

        //TODO Count the number of duplicates in a sorted list without using a Java Set

        //TODO Count the number of evening (after 6pm) dates

        //TODO Count the number of dates in each individual 12 months without using a Java Map

        //TODO Determine the index of the latest LocalDateTime

        //TODO Determine the indexes of the elements that have the earliest starting time, regardless of date

        //TODO Output a date in the format "January 1st, 2018"


    }

    private static int countNumDuplicates(ArrayList<LocalDateTime> dataInFile) {
        Set<LocalDateTime> distinctDates = new HashSet<>();
        for (LocalDateTime date : dataInFile){
            distinctDates.add(date);
        }
        int count = dataInFile.size() - distinctDates.size();
        return count;
    }

    private static int countNumDatesInYear(ArrayList<LocalDateTime> dataInFile, int yearFormatyyyy) {
        int count = 0;
        for (LocalDateTime date : dataInFile){
            if (date.getYear() == yearFormatyyyy){
                count++;
            }
        }
        return count;
    }

    private static ArrayList<LocalDateTime> createArrayListFromFileDate(String fileName) throws FileNotFoundException {
        File infile = new File(fileName);
        ArrayList<LocalDateTime> data = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm");
        if (!infile.exists()){
            System.out.println("Oh no, you can't read from a file that doesn't exist!");
        } else {
            try(Scanner sc = new Scanner(infile)) {
                while (sc.hasNext()){
                    LocalDateTime name = LocalDateTime.parse(sc.nextLine(), formatter);
                    data.add(name);
                }
            }
        }
        return data;
    }

    private static void createFile(String fileName, ArrayList<String> dataToAddToFile) throws FileNotFoundException {
        File newFile = new File(fileName);
        if (newFile.exists()) {
            System.out.println("\nYou have already created a file named " + fileName);
        } else {
            try (PrintWriter pw = new PrintWriter(fileName)) {
                System.out.println("\nYou have created a file named " + fileName);
                for (String data : dataToAddToFile) {
                    pw.println(data);
                }
            }
        }
    }

    private static ArrayList<String> generateRandomDates(int numOfDates) {
        Random rand = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm");
        ArrayList<LocalDateTime> dates = new ArrayList<>();
        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < numOfDates; i++) {
            dates.add(LocalDateTime.now().minusMinutes(rand.nextInt()));
        }
        for (LocalDateTime date : dates){
            String formatDateTime = date.format(formatter);
            answer.add(formatDateTime);
        }
        return answer;
    }

    private static LocalDateTime findEarlierDate(LocalDateTime date1, LocalDateTime date2) {
        if (date1.isBefore(date2)) {
            return date1;
        } else {
            return date2;
        }
    }

    private static String formatTomorrow(LocalDateTime today) {
        LocalDateTime tomorrow = today.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        String formatDateTime = tomorrow.format(formatter);
        return formatDateTime;
    }
}
