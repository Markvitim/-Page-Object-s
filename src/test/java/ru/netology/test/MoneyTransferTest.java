package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataInfo;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class MoneyTransferTest {

    @BeforeEach
    void shouldOpen() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldTopUpFirstCard() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var topUp = validVerify.shouldTopUpCard(0);
        var transfer = topUp.transferMoneyToFirstCard(100);
        var actual1 = transfer.getCardFirstBalance();
        var actual2 = transfer.getCardSecondBalance();
        var actual3 = actual1 + actual2;
        Assertions.assertEquals(10100, actual1);
        Assertions.assertEquals(9900, actual2);
        Assertions.assertEquals(20000, actual3);
    }

    @Test
    void shouldTopUpSecondCard() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var topUp = validVerify.shouldTopUpCard(1);
        var transfer = topUp.transferMoneyToSecondCard(300);
        var actual1 = transfer.getCardFirstBalance();
        var actual2 = transfer.getCardSecondBalance();
        var actual3 = actual1 + actual2;
        Assertions.assertEquals(10100, actual1);
        Assertions.assertEquals(9900, actual2);
        Assertions.assertEquals(20000, actual3);
    }

    @Test
    void shouldCancelTransfer() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var topUp = validVerify.shouldTopUpCard(1);
        var cancel = topUp.transferCancel(100);
        var actual1 = cancel.getCardFirstBalance();
        var actual2 = cancel.getCardSecondBalance();
        var actual3 = actual1 + actual2;
        Assertions.assertEquals(10000, actual1);
        Assertions.assertEquals(10000, actual2);
        Assertions.assertEquals(20000, actual3);
    }

    @Test
    void shouldGetBalanceCardFirst() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var topUpClick = validVerify.shouldTopUpCard(0);
        var inputAmount = topUpClick.transferMoneyToFirstCard(300);
        int actual = inputAmount.getCardFirstBalance();
        Assertions.assertEquals(10400, actual);
    }

    @Test
    void shouldGetBalanceCardSecond() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var topUpClick = validVerify.shouldTopUpCard(1);
        var inputAmount = topUpClick.transferMoneyToSecondCard(50);
        var actual = inputAmount.getCardSecondBalance();
        Assertions.assertEquals(9950, actual);
    }

    @Test
    void shouldCheckRefuse() {
        var loginPage = new LoginPage();
        var authInfo = DataInfo.getAuthInfo();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verificationCode = DataInfo.getCodeFor(authInfo);
        var validVerify = verificationPage.validVerify(verificationCode);
        var topUpClick = validVerify.shouldTopUpCard(1);
        topUpClick.transferRefuse(50);
        $(".notification__title").shouldHave(Condition.text("Ошибка"));
    }
}
