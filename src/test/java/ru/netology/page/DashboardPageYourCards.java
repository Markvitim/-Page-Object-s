package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPageYourCards {

    private final ElementsCollection button = $$("[data-test-id=\"action-deposit\"].button");
    private SelenideElement cardFirst = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
    private SelenideElement cardSecond = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");


    public DashboardPageTopUp shouldTopUpCard(int index) {
        button.get(index).click();
        return new DashboardPageTopUp();
    }

    public int getCardFirstBalance() {
        var text = cardFirst.getText();
        return extractBalance(text);
    }

    public int getCardSecondBalance() {
        var text = cardSecond.getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        String balanceStart = "баланс: ";
        var start = text.indexOf(balanceStart);
        String balanceFinish = " р.";
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}
