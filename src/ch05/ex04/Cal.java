package ch05.ex04;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cal {

    public static void main(String[] args) {
        CalArgs calArgs = new CalArgs();
        try {
            calArgs.parse(args);
        } catch (IllegalArgumentException e) {
            usage();
            System.exit(1);
        }
        Month month = calArgs.month;
        Year year = calArgs.year;

        DayOfWeek first = calcDayOfWeekAt1st(year, month);
        int[][] result = calcEachWeekOfMonth(first, month.length(year.isLeap()));
        print(result).forEach(System.out::println);
    }

    private static void usage() {
        System.out.println("usage: java " + Cal.class.getSimpleName() + " [[month] year]");
        System.exit(1);
    }

    private static DayOfWeek calcDayOfWeekAt1st(Year year, Month month) {
        return LocalDate.of(year.getValue(), month, 1).getDayOfWeek();
    }

    private static int[][] calcEachWeekOfMonth(DayOfWeek dayOfWeekAt1st, int lastDayOfMonth) {
        int day = 1;

        int[] week1 = new int[7];
        for (int i = dayOfWeekAt1st.getValue() - 1; i < week1.length; i++) {
            week1[i] = day++;
        }

        int numOfRestDays = lastDayOfMonth - day + 1;
        int numOfRestWeeks = (numOfRestDays / 7) + ((numOfRestDays / 7) % 7 > 0 ? 1 : 0);
        int[][] restWeeks = new int[numOfRestWeeks][7];

        for (int[] week : restWeeks) {
            for (int i = 0; i < week.length; i++) {
                if (day <= lastDayOfMonth) {
                    week[i] = day++;
                } else {
                    week[i] = 0;
                }
            }
        }

        int[][] result = new int[numOfRestWeeks + 1][7];
        result[0] = week1;
        System.arraycopy(restWeeks, 0, result, 1, numOfRestWeeks);

        return result;
    }

    private static List<String> print(int[][] ordarOfDays) {
        return Arrays.stream(ordarOfDays).map(Cal::printWeek).collect(Collectors.toList());
    }

    private static String printWeek(int[] week) {
        return Arrays.stream(week).boxed().map(day -> {
            if (day == 0) {
                return "  ";
            }
            if (day < 10) {
                return " " + day;
            }
            return String.valueOf(day);
        }).collect(Collectors.joining(" "));
    }
}

class CalArgs {
    Month month;
    Year year;

    void parse(String[] args) throws IllegalArgumentException {
        LocalDate now = LocalDate.now();

        switch (args.length) {
        case 0:
            month = now.getMonth();
            year = Year.of(now.getYear());
            break;
        case 1:
            try {
                month = Month.of(Integer.parseInt(args[0]));
            } catch (DateTimeException | NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
            year = Year.of(now.getYear());
            break;
        case 2:
            try {
                month = Month.of(Integer.parseInt(args[0]));
                year = Year.of(Integer.parseInt(args[1]));
            } catch (DateTimeException | NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
            break;
        default:
            throw new IllegalArgumentException();
        }
    }
}
