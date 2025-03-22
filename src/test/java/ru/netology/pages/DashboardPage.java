package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private final SelenideElement heading = $(".heading_size_xl");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public MoneyTransfer moneyTransfer(String number) {
        $$(".list__item").find(text(number.substring(15, 19))).$("button").click();
        return new MoneyTransfer();
    }

    public int getCardBalance(String number) {
        val text = cards.findBy(text(number)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}

