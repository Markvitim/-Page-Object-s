package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataInfo;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    void shouldOpen() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferFromFirstToSecondCard() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var dashboardPageYourCards = verificationPage.validVerify(verificationCode);
        var dashboardTopUp = dashboardPageYourCards.shouldTopUpCard(1);
        dashboardTopUp.transferMoneyToSecondCard(500);

    }

    @Test
    void shouldTransferFromSecondToFirstCard() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var dashboardPageYourCards = verificationPage.validVerify(verificationCode);
        var dashboardTopUp = dashboardPageYourCards.shouldTopUpCard(0);
        dashboardTopUp.transferMoneyToFirstCard(180);

    }
    @Test
    void shouldCancelTransfer() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var dashboardPageYourCards = verificationPage.validVerify(verificationCode);
        var dashboardTopUp = dashboardPageYourCards.shouldTopUpCard(1);
        dashboardTopUp.transferCancel(100);
    }
    @Test
    void shouldGetBalance() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var dashboardPageYourCards = verificationPage.validVerify(verificationCode);
        var dashboardTopUp = dashboardPageYourCards.shouldTopUpCard(1);
        dashboardTopUp.transferMoneyToFirstCard(100);
       dashboardPageYourCards.getCardBalance(0);

    }

}
