package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.ConstString;
import ru.netology.data.DataGenerator;
import ru.netology.pages.PreviewPage;

import static com.codeborne.selenide.Selenide.open;

public class UiBuyFieldsTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyValidData() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .success();
    }

    @Test
    void shouldBuyBlockCard() {
        DataGenerator.UserDto user = new DataGenerator().getInvalidCard();
        new PreviewPage()
                .buy()
                .buyTicket(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .denied();
    }

    @Test
    void shouldBuyEmptyCardField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(ConstString.EMPTY, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldNote();
    }

    @Test
    void shouldBuyEmptyMonthField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(user.getCardNumber(),ConstString.EMPTY, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldNote();
    }

    @Test
    void shouldBuyEmptyYearField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(user.getCardNumber(),user.getYear(), ConstString.EMPTY, user.getCardHolder(), user.getCvc())
                .checkYearFieldNote();
    }

    @Test
    void shouldBuyEmptyHolderField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(user.getCardNumber(),user.getYear(), user.getYear(), ConstString.EMPTY, user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldBuyEmptyCvcField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(user.getCardNumber(),user.getYear(), user.getYear(), user.getCardHolder(),ConstString.EMPTY)
                .checkCvcFieldNote();
    }

    @Test
    void shouldBuyInvalidCard() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(ConstString.invalidCard, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .denied();
    }

    @Test
    void shouldBuyLessThenNeedCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(ConstString.ONE, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldNote();
    }

    @Test
    void shouldBuyMoreThenNeedCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(ConstString.SEVENTEEN, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldValue(ConstString.expectedSEVENTEEN);
    }

    @Test
    void shouldBuyCharInCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buy()
                .buyTicket(ConstString.VALUE, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldValue(ConstString.EMPTY);
    }
}
