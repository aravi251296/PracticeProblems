package Java8;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalDateTimeAPI {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        System.out.println(date+""+time);
    }
}