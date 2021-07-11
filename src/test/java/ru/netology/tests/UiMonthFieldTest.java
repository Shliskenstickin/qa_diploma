package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.ConstString;
import ru.netology.data.DataGenerator;
import ru.netology.pages.PreviewPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.ConstString.*;

public class UiMonthFieldTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyEmptyMonthField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), EMPTY, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldWrongFormat();
    }

    @Test
    void shouldBuyInvalidMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getYear(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldWrongDate();
    }

    @Test
    void shouldBuyLessThenNeedMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), ONE, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldWrongFormat();
    }

    @Test
    void shouldBuyMoreThenNeedMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), THREE, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldValue(TWO);
    }

    @Test
    void shouldBuyCharInMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), VALUE, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldValueExist();
    }
}
