

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
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

        //Count the number of duplicates in a sorted list without using a Java Set
        int numDuplicatesWithoutSet = countNumDuplicatesWithoutSet(dataInFile);
        System.out.println("\nThere are " + numDuplicatesWithoutSet + " duplicates in this file (without using a Set).");

        //Count the number of evening (after 6pm) dates
        int numEveningDates = countEveningDates(dataInFile, 6);
        System.out.println("\nThere are " + numEveningDates + " evening dates in this file.");

        //Count the number of dates in each individual 12 months without using a Java Map
        ArrayList<Integer> numDatesInMonth = countNumDatesInMonth(dataInFile);
        System.out.println("\nArrayList of number of dates in each month: " + numDatesInMonth);

        //Count the number of dates in each individual 12 months using a Java Map
        Map<Integer, Integer> numDatesInMonthMap = countDatesInMonthMap(dataInFile);
        System.out.println("Map of number of dates in each month: " + numDatesInMonthMap);

        //Determine the index of the latest LocalDateTime
        int indexMostRecent = findMostRecentDate(dataInFile);
        System.out.println("\nThe most recent date in the list is at this index: " + indexMostRecent);

        //Determine the indexes of the elements that have the earliest starting time, regardless of date
        ArrayList<Integer> indexEarlyStartTime = findEarliestTime(dataInFile);
        System.out.println("The indexes of the elements that have the earliest starting time, regardless of date are: " + indexEarlyStartTime);

        //TODO Output a date in the format "January 1st, 2018"


    }

    private static ArrayList<Integer> findEarliestTime(ArrayList<LocalDateTime> dataInFile) {
        int indexEarliestTime = 0;
        ArrayList<Integer> indexesEarliestTime = new ArrayList<>();
        for (int i = 1; i < dataInFile.size(); i++) {
            if (dataInFile.get(indexEarliestTime).getHour() > dataInFile.get(i).getHour()){
                indexEarliestTime = i;
            }else if (dataInFile.get(indexEarliestTime).getHour() == dataInFile.get(i).getHour()){
                if (dataInFile.get(indexEarliestTime).getMinute() > dataInFile.get(i).getMinute()){
                    indexEarliestTime = i;
                }else if (dataInFile.get(indexEarliestTime).getMinute() == dataInFile.get(i).getMinute()){
                    indexesEarliestTime.add(i);
                }
            }
        }
        indexesEarliestTime.add(indexEarliestTime);
        return indexesEarliestTime;
    }

    private static int findMostRecentDate(ArrayList<LocalDateTime> dataInFile) {
        int index = 0;
        LocalDateTime current = LocalDateTime.now();
        for (int i = 0; i < dataInFile.size(); i++) {
            if (dataInFile.get(i).isBefore(current)){
                index = i;
            }
        }
        return index;
    }

    private static Map<Integer, Integer> countDatesInMonthMap(ArrayList<LocalDateTime> dataInFile) {
        Map<Integer, Integer> answer = new HashMap<>();
        ArrayList<Integer> counts = countNumDatesInMonth(dataInFile);
        for (int i = 0; i < counts.size(); i++) {
            answer.put(i+1, counts.get(i));
        }
        return answer;
    }

    private static ArrayList<Integer> countNumDatesInMonth(ArrayList<LocalDateTime> dataInFile) {
        ArrayList<Integer> counts = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            counts.add(0);
        }
        for (LocalDateTime date : dataInFile){
            int currentValue = counts.get(date.getMonthValue()-1);
            currentValue++;
            counts.set((date.getMonthValue())-1, currentValue);
        }
        return counts;
    }

    private static int countEveningDates(ArrayList<LocalDateTime> dataInFile, int hour) {
        int count = 0;
        for (LocalDateTime date : dataInFile) {
            if (date.getHour() >= hour){
                count++;
            }
        }
        return count;
    }

    private static int countNumDuplicatesWithoutSet(ArrayList<LocalDateTime> dataInFile) {
        // this code works for any list
        /*int count = 0;
        ArrayList<LocalDateTime> datesCopy = new ArrayList<>();
        for (LocalDateTime date : dataInFile){
            if (datesCopy.contains(date)){
                count++;
            }
            datesCopy.add(date);
        }
        return count;*/

        //this code works specifically for a sorted list
        int count = 0;
        LocalDateTime dateBefore = dataInFile.get(0);
        for (int i = 1; i < dataInFile.size(); i++) {
            if (dataInFile.get(i).compareTo(dateBefore) == 0){
                count++;
            }
            dateBefore = dataInFile.get(i);
        }
        return count;
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
