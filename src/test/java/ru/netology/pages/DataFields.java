package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class DataFields {

    private SelenideElement card = $(byXpath("//span[text()='Номер карты']/following-sibling::span/input"));
    private SelenideElement month = $(byXpath("//span[text()='Месяц']/following-sibling::span/input"));
    private SelenideElement year = $(byXpath("//span[text()='Год']/following-sibling::span/input"));
    private SelenideElement holder = $(byXpath("//span[text()='Владелец']/following-sibling::span/input"));
    private SelenideElement cvc = $(byXpath("//span[text()='CVC/CVV']/following-sibling::span/input"));
    private SelenideElement nextButton = $(byText("Продолжить"));

    public Checks buyTicket(String card, String month, String year, String holder, String cvc) {
        this.card.setValue(card);
        this.month.setValue(month);
        this.year.setValue(year);
        this.holder.setValue(holder);
        this.cvc.setValue(cvc);
        nextButton.click();
        return new Checks();
    }
}
