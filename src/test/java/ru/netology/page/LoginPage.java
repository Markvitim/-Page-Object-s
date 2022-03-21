package ru.netology.page;

import ru.netology.data.DataInfo;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public VerificationPage validAuthInfo(DataInfo.AuthInfo authInfo){
        $("[data-test-id=\"login\"] input").setValue(authInfo.getLogin());
        $("[name=\"password\"].input__control").setValue((authInfo.getPassword()));
        $(".button__text").click();
        return new VerificationPage();
    }
}
