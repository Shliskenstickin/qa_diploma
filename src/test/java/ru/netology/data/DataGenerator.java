package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataGenerator {
    private Calendar calendar = new GregorianCalendar();

    public UserDto getValidCard() {
        Faker user = new Faker(new Locale("en"));
        String cardNumber = "4444 4444 4444 4441";
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String year = Integer.toString(calendar.get(Calendar.YEAR) + 1);
        String cardHolder = user.name().lastName() + user.name().firstName();
        String Cvc = "123";

        return null;
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        String c = Integer.toString(calendar.get(Calendar.YEAR) + 1);
        System.out.println(c);
    }

    public UserDto getInvalidCard() {
        Faker user = new Faker(new Locale("en"));
        String cardNumber = "4444 4444 4444 4442";
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String year = Integer.toString(calendar.get(Calendar.YEAR) + 1);
        String cardHolder = user.name().lastName() + user.name().firstName();
        String Cvc = "123";

        return null;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class UserDto {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String Cvc;
    }
}
