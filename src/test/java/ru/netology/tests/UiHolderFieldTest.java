package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.pages.PreviewPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.ConstString.*;

public class UiHolderFieldTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyEmptyHolderField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getYear(), user.getYear(), EMPTY, user.getCvc())
                .checkHolderFieldEmpty();
    }

    @Test
    void shouldBuyInvalidHolder() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        DataGenerator.UserDto user2 = new DataGenerator().getInvalidData();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user2.getCardHolder(), user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldBuyHolderFieldLessThenNeed() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), A, user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldBuyHolderFieldMoreThenNeed() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();

        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkHolderFieldNote();
        //TODO написать функцию генерации случайной строки
    }

    @Test
    void shouldBuyHolderFieldNumbers() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();

        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), SIXTEEN, user.getCvc())
                .checkHolderFieldNote();
    }
}
