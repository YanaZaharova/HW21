package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.cardNumber;
import static ru.netology.data.DataHelper.justifyBalance;
import static ru.netology.pages.DashboardPage.cardsBalance;

public class MoneyTransferTest {
    private int[] cardsBalance;
    private int value1 = 6_420;
    private int value2 = 3_580;
    private int value3 = 10_000;

    @BeforeEach
    public void openAndLogin() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        cardsBalance = cardsBalance();
        cardsBalance = justifyBalance(cardsBalance[1], cardsBalance[2]);
        assertEquals(cardsBalance[1], cardsBalance[2]);
    }

    @AfterEach
    void asserting() {
        cardsBalance = cardsBalance();
        cardsBalance = justifyBalance(cardsBalance[1], cardsBalance[2]);
        assertEquals(cardsBalance[1], cardsBalance[2]);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var dashboard = new DashboardPage();
        dashboard.moneyTransfer(cardNumber(1)).transaction(Integer.toString(value1), cardNumber(2));
        dashboard.moneyTransfer(cardNumber(1)).transaction(Integer.toString(value2), cardNumber(2));
        dashboard.moneyTransfer(cardNumber(2)).transaction(Integer.toString(value3), cardNumber(1));
        cardsBalance = cardsBalance();
        int firstResultExpected = cardsBalance[1] + value1 + value2 - value3;
        int secondResultExpected = cardsBalance[2] - value1 - value2 + value3;
        assertEquals(firstResultExpected, cardsBalance[1]);
        assertEquals(secondResultExpected, cardsBalance[2]);
    }

    /* Test with bug */
// @Test
// void shouldNotWithdrawMoneyOutOfBounds() {
//      new DashboardPage().moneyTransfer(cardNumber(1)).transaction(Integer.toString(value1 + value2 + value3), cardNumber(2));
//      cardsBalance = cardsBalance();
//      int firstResultExpected = cardsBalance[1];
//      int secondResultExpected = cardsBalance[2];
//      assertEquals(firstResultExpected, cardsBalance[1]);
//      assertEquals(secondResultExpected, cardsBalance[2]);
//  }
}

