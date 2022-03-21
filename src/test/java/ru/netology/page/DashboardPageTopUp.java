package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import ru.netology.data.DataInfo;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPageTopUp {
    private ElementsCollection inputField = $$(".input__control");
    private ElementsCollection button = $$(".button__text");

    public void DashboardPageTop() {
    }

    public DashboardPageYourCards transferMoneyToFirstCard(int amount) {
        inputField.get(0).setValue(String.valueOf(amount));
        inputField.get(1).setValue(String.valueOf(DataInfo.getCardSecond()));
        button.first().click();
        return new DashboardPageYourCards();
    }
    public DashboardPageYourCards transferMoneyToSecondCard(int amount) {
        inputField.get(0).setValue(String.valueOf(amount));
        inputField.get(1).setValue(String.valueOf(DataInfo.getCardFirst()));
        button.first().click();
        return new DashboardPageYourCards();
    }
    public DashboardPageYourCards transferCancel(int amount) {
        inputField.get(0).setValue(String.valueOf(amount));
        inputField.get(1).setValue(String.valueOf(DataInfo.getCardFirst()));
        button.last().click();
        return new DashboardPageYourCards();
    }
}
