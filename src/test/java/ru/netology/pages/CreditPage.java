package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class CreditPage {
    private SelenideElement heading = $(byText("Кредит по данным карты"));
    public CreditPage() {
        heading.shouldBe(Condition.visible);
    }
}
