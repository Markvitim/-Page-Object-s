package ru.netology.data;

import lombok.Value;

public class DataInfo {
    private DataInfo() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo(){
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode{
        private String code;
    }
    public static VerificationCode getCodeFor(AuthInfo authInfo){
        return new VerificationCode("12345");
    }
    @Value
    public static class DataCard{
        private String number;
        private int balance;
    }
    public static DataCard getCardFirst(){
        return new DataCard("5559 0000 0000 0001", 10000);
    }
    public static DataCard getCardSecond(){
        return new DataCard("5559 0000 0000 0002", 10000);
    }
}
