package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.data.DataGenerator;
import ru.netology.pages.PreviewPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.ConstString.*;

public class UiCardFieldTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyEmptyCardField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(EMPTY, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldWrongFormat();
    }

    @Test
    void shouldBuyInvalidCard() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(SIXTEEN, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .denied();
    }

    @Test
    void shouldBuyLessThenNeedCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(ONE, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldWrongFormat();
    }

    @Test
    void shouldBuyMoreThenNeedCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(SEVENTEEN, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldValue(CARD_NUMBER);
    }

    @Test
    void shouldBuyCharInCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(VALUE, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldValueExist();
    }
}
