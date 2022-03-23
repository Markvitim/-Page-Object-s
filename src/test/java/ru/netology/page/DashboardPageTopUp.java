package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataInfo;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPageTopUp {
    private SelenideElement sumInputField = $("[data-test-id=\"amount\"] input");
    private SelenideElement fromField = $("[data-test-id=\"from\"] input");
    private SelenideElement button = $(".button__text");
    private SelenideElement buttonCancel = $("[data-test-id=\"action-cancel\"].button");


    public DashboardPageYourCards transferMoneyToFirstCard(int amount) {
        sumInputField.shouldBe(Condition.visible).setValue(String.valueOf(amount));
        fromField.shouldBe(Condition.visible).setValue(DataInfo.getCardSecond().getNumber());
        button.shouldHave(Condition.text("Пополнить")).click();
        return new DashboardPageYourCards();
    }

    public DashboardPageYourCards transferMoneyToSecondCard(int amount) {
        sumInputField.shouldBe(Condition.visible).setValue(String.valueOf(amount));
        fromField.shouldBe(Condition.visible).setValue(DataInfo.getCardFirst().getNumber());
        button.shouldHave(Condition.text("Пополнить")).click();
        return new DashboardPageYourCards();
    }

    public DashboardPageYourCards transferCancel(int amount) {
        sumInputField.shouldBe(Condition.visible).setValue(String.valueOf(amount));
        fromField.shouldBe(Condition.visible).setValue(String.valueOf(DataInfo.getCardFirst()));
        buttonCancel.click();
        return new DashboardPageYourCards();
    }

    public DashboardPageYourCards transferRefuse(int amount) {
        sumInputField.shouldBe(Condition.visible).setValue(String.valueOf(amount));
        fromField.shouldBe(Condition.visible).setValue("23255522233");
        button.shouldHave(Condition.text("Пополнить")).click();
        return new DashboardPageYourCards();
    }
}
