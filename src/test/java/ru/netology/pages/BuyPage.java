package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class BuyPage {
    private SelenideElement heading = $(byText("Оплата по карте"));
    public BuyPage() {
        heading.shouldBe(Condition.visible);
    }
}
