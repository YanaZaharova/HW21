package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import com.google.common.base.CharMatcher;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.data.DataHelper.cardNumber;

public class DashboardPage {

    private static SelenideElement heading = $(".heading_size_xl");
    private static SelenideElement firstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button__text");
    private static SelenideElement secondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button__text");

    public DashboardPage() {
        heading.shouldBe(visible);
    }


    public static SelenideElement getHeading() {
        return heading;
    }

    public static SelenideElement getFirstCardButton() {
        return firstCardButton;
    }

    public static SelenideElement getSecondCardButton() {
        return secondCardButton;
    }

    public MoneyTransfer moneyTransfer(String number) {
        $$(".list__item").find(text(number.substring(15, 19))).$("button").click();
        return new MoneyTransfer();
    }

    public static int[] cardsBalance() {
        heading.shouldBe(visible);
        int[] result = new int[3];
        String tmp = $$(".list__item").find(text(cardNumber(1).substring(15, 19))).$("div").getOwnText().substring(20);
        result[1] = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(tmp));
        String tmp2 = $$(".list__item").find(text(cardNumber(2).substring(15, 19))).$("div").getOwnText().substring(20);
        result[2] = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(tmp2));
        return result;
    }
}
