package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
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
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .success();
    }

    @Test
    void shouldBuyBlockCard() {
        DataGenerator.UserDto user = new DataGenerator().getInvalidCard();
        new PreviewPage()
                .buy()
                .enterCardData(user.getCardNumber(), user.getMonth(), user.getYear(), user.getCardHolder(), user.getCvc())
                .denied();
    }
}
