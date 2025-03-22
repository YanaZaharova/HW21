package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransfer {

    private SelenideElement summField = $(".money-input .input__control");
    private SelenideElement cardField = $("[data-test-id='from'] .input__control");
    private SelenideElement replenishmentButton = $("[data-test-id='action-transfer'] .button__text");

    public void transaction(String value, String source) {
        summField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, value.replace(" ", ""));
        cardField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, source.replace(" ", ""));
        replenishmentButton.click();
    }
}
