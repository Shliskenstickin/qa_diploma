package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.RandomData;
import ru.netology.pages.PreviewPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.ConstString.*;

public class UiBuyTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBuyValidData() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .success();
    }

    @Test
    void shouldBuyBlockCard() {
        DataGenerator.UserDto user = new DataGenerator().getInvalidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .denied();
    }

    //Тесты поля Карта раздел покупки-----------------------------------------------------------------------------------
    @Test
    void shouldBuyEmptyCardField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(EMPTY, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldWrongFormat();
    }

    @Test
    void shouldBuyInvalidCard() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(SIXTEEN, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .denied();
    }

    @Test
    void shouldBuyLessThenNeedCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(ONE, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldWrongFormat();
    }

    @Test
    void shouldBuyMoreThenNeedCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(SEVENTEEN, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldValue(CARD_NUMBER);
    }

    @Test
    void shouldBuyCharInCardNumber() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(VALUE, user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkCardFieldValueExist();
    }

    //Тесты поля Месяц раздел покупки-----------------------------------------------------------------------------------
    @Test
    void shouldBuyEmptyMonthField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), EMPTY, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldWrongFormat();
    }

    @Test
    void shouldBuyInvalidMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getYear(), user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldWrongDate();
    }

    @Test
    void shouldBuyLessThenNeedMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), ONE, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldWrongFormat();
    }

    @Test
    void shouldBuyMoreThenNeedMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), THREE, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldValue(TWO);
    }

    @Test
    void shouldBuyCharInMonth() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), VALUE, user.getYear(), user.getCardHolder(), user.getCvc())
                .checkMonthFieldValueExist();
    }

    //Тесты поля Год раздел покупки-------------------------------------------------------------------------------------
    @Test
    void shouldBuyEmptyYearField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getYear(), EMPTY, user.getCardHolder(), user.getCvc())
                .checkYearFieldWrongFormat();
    }

    @Test
    void shouldBuyInvalidYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        DataGenerator.UserDto user2 = new DataGenerator().getInvalidData();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user2.getYear(), user.getCardHolder(), user.getCvc())
                .checkYearFieldWrongDate();
    }

    @Test
    void shouldBuyLessThenNeedYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), ONE, user.getCardHolder(), user.getCvc())
                .checkYearFieldWrongFormat();
    }

    @Test
    void shouldBuyMoreThenNeedYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), THREE, user.getCardHolder(), user.getCvc())
                .checkYearFieldValue(TWO);
    }

    @Test
    void shouldBuyCharInYear() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), VALUE, user.getCardHolder(), user.getCvc())
                .checkYearFieldValueExist();
    }

    //Тесты поля Держатель раздел покупки-------------------------------------------------------------------------------
    @Test
    void shouldBuyEmptyHolderField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getYear(), user.getYear(), EMPTY, user.getCvc())
                .checkHolderFieldEmpty();
    }

    @Test
    void shouldBuyInvalidHolder() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        DataGenerator.UserDto user2 = new DataGenerator().getInvalidData();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user2.getCardHolder(), user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldBuyHolderFieldLessThenNeed() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), A, user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldBuyHolderFieldMoreThenNeed() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();

        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), RandomData.getString(256), user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldBuyHolderFieldNumbers() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();

        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), SIXTEEN, user.getCvc())
                .checkHolderFieldNote();
    }

    @Test
    void shouldBuyHolderFieldWhitespace() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();

        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), SPACE, user.getCvc())
                .checkHolderFieldEmpty();
    }

    //Тесты поля Держатель раздел покупки-------------------------------------------------------------------------------
    @Test
    void shouldBuyEmptyCvcField() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(),user.getYear(), user.getYear(), user.getCardHolder(), EMPTY)
                .checkCvcFieldWrongFormat();
    }

    @Test
    void shouldBuyLessThenNeedCvc() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), ONE)
                .checkCvcFieldWrongFormat();
    }

    @Test
    void shouldBuyMoreThenNeedCvc() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), FOUR)
                .checkCvcFieldValue(THREE);
    }

    @Test
    void shouldBuyCharInCvc() {
        DataGenerator.UserDto user = new DataGenerator().getValidCard();
        new PreviewPage()
                .buyTicket()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), VALUE)
                .checkCvcFieldValueExist();
    }
}
