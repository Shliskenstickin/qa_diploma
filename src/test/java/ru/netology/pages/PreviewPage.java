package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PreviewPage {
    //    private SelenideElement buy = $$("button").find(exactText("Купить"));
    private SelenideElement buy = $(byText("Купить"));
    private SelenideElement credit = $(byText("Купить в кредит"));

    public BuyPage buy() {
        buy.click();
        return new BuyPage();
    }

    public CreditPage credit() {
        credit.click();
        return new CreditPage();
    }
}
