package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.RandomData;
import ru.netology.pages.PreviewPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.ConstString.*;

public class UiCreditTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldCreditValidData() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .success();
    }

    @Test
    void shouldCreditBlockCard() {
        DataGenerator.UserDto user = new DataGenerator().getInvalidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .denied();
    }

    //Тесты поля Карта раздел покупки-----------------------------------------------------------------------------------
    @Test
    void shouldCreditEmptyCardField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(EMPTY, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldWrongFormat();
    }

    @Test
    void shouldCreditInvalidCard() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(SIXTEEN, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .denied();
    }

    @Test
    void shouldCreditLessThenNeedCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(ONE, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldWrongFormat();
    }

    @Test
    void shouldCreditMoreThenNeedCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(SEVENTEEN, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldValue(CARD_NUMBER);
    }

    @Test
    void shouldCreditCharInCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(VALUE, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldValueExist();
    }

    //Тесты поля Месяц раздел покупки-----------------------------------------------------------------------------------
    @Test
    void shouldCreditEmptyMonthField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), EMPTY, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldWrongFormat();
    }

    @Test
    void shouldCreditInvalidMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getYear(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldWrongDate();
    }

    @Test
    void shouldCreditLessThenNeedMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), ONE, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldWrongFormat();
    }

    @Test
    void shouldCreditMoreThenNeedMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), THREE, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldValue(TWO);
    }

    @Test
    void shouldCreditCharInMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), VALUE, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldValueExist();
    }

    //Тесты поля Год раздел покупки-------------------------------------------------------------------------------------
    @Test
    void shouldCreditEmptyYearField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getYear(), EMPTY, user.getCardHolder(), user.getCvc())
                .checkYearFieldWrongFormat();
    }

    @Test
    void shouldCreditInvalidYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        DataGenerator.UserDto user2 = new DataGenerator().getInvalidData();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user2.getYear(), user.getCardHolder(), user.getCvc())
                .checkYearFieldWrongDate();
    }

    @Test
    void shouldCreditLessThenNeedYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), ONE, user.getCardHolder(), user.getCvc())
                .checkYearFieldWrongFormat();
    }

    @Test
    void shouldCreditMoreThenNeedYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), THREE, user.getCardHolder(), user.getCvc())
                .checkYearFieldValue(TWO);
    }

    @Test
    void shouldCreditCharInYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), VALUE, user.getCardHolder(), user.getCvc())
                .checkYearFieldValueExist();
    }

    //Тесты поля Держатель раздел покупки-------------------------------------------------------------------------------
    @Test
    void shouldCreditEmptyHolderField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getYear(), user.getYear(), EMPTY, user.getCvc())
                .checkHolderFieldEmpty();
    }

    @Test
    void shouldCreditInvalidHolder() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        DataGenerator.UserDto user2 = new DataGenerator().getInvalidData();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user2.getCardHolder(), user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldCreditHolderFieldLessThenNeed() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), A, user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldCreditHolderFieldMoreThenNeed() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();

        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), RandomData.getString(256), user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldCreditHolderFieldNumbers() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();

        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), SIXTEEN, user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldCreditHolderFieldWhitespace() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();

        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), SPACE, user.getCvc())
                .checkHolderFieldEmpty();
    }

    //Тесты поля Держатель раздел покупки-------------------------------------------------------------------------------
    @Test
    void shouldCreditEmptyCvcField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(),user.getYear(), user.getYear(), user.getCardHolder(), EMPTY)
                .checkCvcFieldWrongFormat();
    }

    @Test
    void shouldCreditLessThenNeedCvc() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), ONE)
                .checkCvcFieldWrongFormat();
    }

    @Test
    void shouldCreditMoreThenNeedCvc() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), FOUR)
                .checkCvcFieldValue(THREE);
    }

    @Test
    void shouldCreditCharInCvc() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .creditTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), VALUE)
                .checkCvcFieldValueExist();
    }
}
