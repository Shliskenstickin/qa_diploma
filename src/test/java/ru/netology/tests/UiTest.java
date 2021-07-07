package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.PreviewPage;

import static com.codeborne.selenide.Selenide.open;

public class UiTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldBuy(){
        new PreviewPage().buy();
    }

    @Test
    void shouldCredit(){
        new PreviewPage().credit();
    }
}
