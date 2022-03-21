package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPageYourCards {
    private final ElementsCollection card = $$(".list__item");
    private final ElementsCollection button = $$(".button__text");


    public DashboardPageTopUp shouldTopUpCard(int index) {
        button.get(index).click();
        return new DashboardPageTopUp();
    }
    public int getCardBalance(int index) {
        var text = card.get(index).text();
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
