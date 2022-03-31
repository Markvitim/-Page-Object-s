package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPageTopUp {
    private SelenideElement sumInputField = $("[data-test-id=\"amount\"] input");
    private SelenideElement fromField = $("[data-test-id=\"from\"] input");
    private SelenideElement button = $(".button__text");
    private SelenideElement buttonCancel = $("[data-test-id=\"action-cancel\"].button");
    private SelenideElement notification = $("[data-test-id=\"error-notification\"].notification");


    public DashboardPageYourCards transferCancel(int amount, String cardNumber) {
        sumInputField.shouldBe(Condition.visible).setValue(String.valueOf(amount));
        fromField.shouldBe(Condition.visible).setValue(cardNumber);
        buttonCancel.click();
        return new DashboardPageYourCards();
    }

    public DashboardPageYourCards transferRefuse(int amount, String cardNumber) {
        sumInputField.shouldBe(Condition.visible).setValue(String.valueOf(amount));
        fromField.shouldBe(Condition.visible).setValue(cardNumber);
        button.shouldHave(Condition.text("Пополнить")).click();
        notification.shouldBe(Condition.appear);
        return new DashboardPageYourCards();
    }

    public DashboardPageYourCards transferMoney(int amount, String cardNumber) {
        sumInputField.shouldBe(Condition.visible).setValue(String.valueOf(amount));
        fromField.shouldBe(Condition.visible).setValue(cardNumber);
        button.shouldHave(Condition.text("Пополнить")).click();
        return new DashboardPageYourCards();
    }
}
