package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PreviewPage {
    //    private SelenideElement buy = $$("button").find(exactText("Купить"));
    private SelenideElement buy = $(byText("Купить"));
    private SelenideElement credit = $(byText("Купить в кредит"));
    private SelenideElement buyHeading = $(byText("Оплата по карте"));
    private SelenideElement creditHeading = $(byText("Кредит по данным карты"));

    public DataFields buy() {
        buy.click();
        buyHeading.shouldBe(Condition.visible);
        return new DataFields();
    }

    public DataFields credit() {
        credit.click();
        creditHeading.shouldBe(Condition.visible);
        return new DataFields();
    }
}
