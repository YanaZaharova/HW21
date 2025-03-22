package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.cardNumber;

public class MoneyTransferTest {

    @BeforeEach
    public void openAndLogin() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards1() {
        var dashboard = new DashboardPage();
        var currentBalanceFirstCard = dashboard.getCardBalance("**** **** **** 0002");
        var currentBalanceSecondCard = dashboard.getCardBalance("**** **** **** 0001");
        int minSum = 1;
        int randomSum = minSum + (int) (Math.random() * ((currentBalanceFirstCard - minSum) + 1));
        dashboard.moneyTransfer(cardNumber(1)).transaction(Integer.toString(randomSum), cardNumber(2));
        assertEquals(currentBalanceSecondCard - randomSum, dashboard.getCardBalance("**** **** **** 0001"));
        assertEquals(currentBalanceFirstCard + randomSum, dashboard.getCardBalance("**** **** **** 0002"));
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards2() {
        var dashboard = new DashboardPage();
        var currentBalanceFirstCard = dashboard.getCardBalance("**** **** **** 0002");
        var currentBalanceSecondCard = dashboard.getCardBalance("**** **** **** 0001");
        int minSum = 1;
        int randomSum = minSum + (int) (Math.random() * ((currentBalanceFirstCard - minSum) + 1));
        dashboard.moneyTransfer(cardNumber(2)).transaction(Integer.toString(randomSum), cardNumber(1));
        assertEquals(currentBalanceSecondCard + randomSum, dashboard.getCardBalance("**** **** **** 0001"));
        assertEquals(currentBalanceFirstCard - randomSum, dashboard.getCardBalance("**** **** **** 0002"));
    }
}

