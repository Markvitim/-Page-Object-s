package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataInfo;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[name=\"code\"].input__control");
    private SelenideElement verifyButton = $(".button__text");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashboardPageYourCards validVerify(DataInfo.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPageYourCards();
    }
}

