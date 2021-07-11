package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.pages.PreviewPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.ConstString.*;

public class UiYearFieldTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyEmptyYearField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getYear(), EMPTY, user.getCardHolder(), user.getCvc())
                .checkYearFieldWrongFormat();
    }

    @Test
    void shouldBuyInvalidYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        DataGenerator.UserDto user2 = new DataGenerator().getInvalidData();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user2.getYear(), user.getCardHolder(), user.getCvc())
                .checkYearFieldWrongDate();
    }

    @Test
    void shouldBuyLessThenNeedYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), ONE, user.getCardHolder(), user.getCvc())
                .checkYearFieldWrongFormat();
    }

    @Test
    void shouldBuyMoreThenNeedYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), THREE, user.getCardHolder(), user.getCvc())
                .checkYearFieldValue(TWO);
    }

    @Test
    void shouldBuyCharInYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), VALUE, user.getCardHolder(), user.getCvc())
                .checkYearFieldValueExist();
    }
}
