package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataGenerator {
    private Calendar calendar = new GregorianCalendar();
    private SimpleDateFormat showMonth = new SimpleDateFormat("MM");
    private SimpleDateFormat showYear = new SimpleDateFormat("yy");

    public UserDto getValidCard() {
        Faker user = new Faker(new Locale("en"));
        String cardNumber = "4444444444444441";
        String month = showMonth.format(calendar.getTime());
        calendar.roll(Calendar.YEAR, 1);
        String year = showYear.format(calendar.getTime());
        String cardHolder = user.name().lastName() + ConstString.SPACE + user.name().firstName();
        String cvc = "123";

        return new UserDto(cardNumber, month, year, cardHolder, cvc);
    }

    public UserDto getInvalidCard() {
        Faker user = new Faker(new Locale("en"));
        String cardNumber = "4444444444444442";
        String month = showMonth.format(calendar.getTime());
        calendar.roll(Calendar.YEAR, 1);
        String year = showYear.format(calendar.getTime());
        String cardHolder = user.name().lastName() + ConstString.SPACE + user.name().firstName();
        String cvc = "123";

        return new UserDto(cardNumber, month, year, cardHolder, cvc);
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
