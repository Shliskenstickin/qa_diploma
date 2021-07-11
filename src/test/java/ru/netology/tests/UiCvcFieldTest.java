package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.pages.PreviewPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.ConstString.*;

public class UiCvcFieldTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyEmptyCvcField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(),user.getYear(), user.getYear(), user.getCardHolder(), EMPTY)
                .checkCvcFieldWrongFormat();
    }

    @Test
    void shouldBuyLessThenNeedCvc() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), ONE)
                .checkCvcFieldWrongFormat();
    }

    @Test
    void shouldBuyMoreThenNeedCvc() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), FOUR)
                .checkCvcFieldValue(THREE);
    }

    @Test
    void shouldBuyCharInCvc() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), VALUE)
                .checkCvcFieldValueExist();
    }
}
