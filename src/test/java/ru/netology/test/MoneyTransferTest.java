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
    void shouldTopUpCardFirst() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var amount = 100;
        var expectedBalanceFirstCard = validVerify.getCardFirstBalance() + amount;
        var expectedBalanceSecondCard = validVerify.getCardSecondBalance() - amount;
        var topUpPage = validVerify.shouldTopUpCard(0);
        var transfer = topUpPage.transferMoney(amount, DataInfo.getCardSecond().getNumber());
        var actualBalanceFirstCard = transfer.getCardFirstBalance();
        var actualBalanceSecondCard = transfer.getCardSecondBalance();
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
        Assertions.assertEquals(20000, actualBalanceFirstCard + actualBalanceSecondCard);
    }

    @Test
    void shouldTopUpCardSecond() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var amount = 100;
        var expectedBalanceFirstCard = validVerify.getCardFirstBalance() - amount;
        var expectedBalanceSecondCard = validVerify.getCardSecondBalance() + amount;
        var topUpPage = validVerify.shouldTopUpCard(1);
        var transfer = topUpPage.transferMoney(amount, DataInfo.getCardFirst().getNumber());
        var actualBalanceFirstCard = transfer.getCardFirstBalance();
        var actualBalanceSecondCard = transfer.getCardSecondBalance();
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
        Assertions.assertEquals(20000, actualBalanceFirstCard + actualBalanceSecondCard);
    }

    @Test
    void shouldCancelTransfer() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var amount = 100;
        var expectedBalanceFirstCard = validVerify.getCardFirstBalance();
        var expectedBalanceSecondCard = validVerify.getCardSecondBalance();
        var topUp = validVerify.shouldTopUpCard(1);
        var cancel = topUp.transferCancel(100, DataInfo.getCardFirst().getNumber());
        var actualBalanceFirstCard = cancel.getCardFirstBalance();
        var actualBalanceSecondCard = cancel.getCardSecondBalance();
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
        Assertions.assertEquals(20000, actualBalanceFirstCard + actualBalanceSecondCard);
    }


    @Test
    void shouldCheckRefuse() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var amount = 100;
        var cardNumber = "1000000000000000";
        var topUpClick = validVerify.shouldTopUpCard(1);
        topUpClick.transferRefuse(amount, cardNumber);
    }

}
