package ru.netology.data;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @NotNull
    public static String encryptedCardNumber(int item) {
        String[] cards = {" ", "**** **** **** 0001", "**** **** **** 0002"};
        return cards[item];
    }

    @NotNull
    public static String cardNumber(int item) {
        String[] cards = {" ", "5559 0000 0000 0001", "5559 0000 0000 0002"};
        return cards[item];
    }

    public static int randomSum(int currentBalance) {
        int minSum = 1;
        return minSum + (int) (Math.random() * ((currentBalance - minSum) + 1));
    }
}
