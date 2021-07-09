package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Checks {
    private SelenideElement success = $(".notification_status_ok");
    private SelenideElement denied = $(".notification_status_error");
    private SelenideElement cardFieldNote = $(byXpath("//span[text()='Номер карты']/following-sibling::span[text()='Неверный формат']"));
    private SelenideElement monthFieldNote = $(byXpath("//span[text()='Месяц']/following-sibling::span[text()='Неверный формат']"));
    private SelenideElement yearFieldNote = $(byXpath("//span[text()='Год']/following-sibling::span[text()='Неверный формат']"));
    private SelenideElement holderFieldNote = $(byXpath("//span[text()='Владелец']/following-sibling::span[text()='Поле обязательно для заполнения']"));
    private SelenideElement cvcFieldNote = $(byXpath("//span[text()='CVC/CVV']/following-sibling::span[text()='Неверный формат']"));

    private SelenideElement card = $(byXpath("//span[text()='Номер карты']/following-sibling::span/input"));
    private SelenideElement month = $(byXpath("//span[text()='Месяц']/following-sibling::span/input"));
    private SelenideElement year = $(byXpath("//span[text()='Год']/following-sibling::span/input"));
    private SelenideElement cvc = $(byXpath("//span[text()='CVC/CVV']/following-sibling::span/input"));

    public void success() {
        success.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void denied() {
        denied.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void checkCardFieldNote() {
        cardFieldNote.shouldBe(Condition.visible);
    }

    public void checkMonthFieldNote() {
        monthFieldNote.shouldBe(Condition.visible);
    }

    public void checkYearFieldNote() {
        yearFieldNote.shouldBe(Condition.visible);
    }

    public void checkHolderFieldNote() {
        holderFieldNote.shouldBe(Condition.visible);
    }

    public void checkCvcFieldNote() {
        cvcFieldNote.shouldBe(Condition.visible);
    }

    public void checkCardFieldValue(String value){
        card.shouldHave(Condition.value(value));
    }

    public void checkMonthFieldValue(String value){
        month.shouldHave(Condition.value(value));
    }

    public void checkYearFieldValue(String value){
        year.shouldHave(Condition.value(value));
    }

    public void checkCvcFieldValue(String value){
        cvc.shouldHave(Condition.value(value));
    }
}
