package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection button = $$("[data-test-id=action-deposit]");
    private SelenideElement currentCard = $("[data-test-id=amount] input");
    private SelenideElement orderNumberCard = $("[data-test-id=from] input");
    private SelenideElement currentButton = $("[data-test-id=action-transfer]");
    private ElementsCollection cards = $$(By.className("list__item"));
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public void transferMoney(DataHelper.NumberCardOne card, String value) {
        button.first().click();
        currentCard.setValue(value);
        orderNumberCard.setValue(card.getNumber());
        currentButton.click();
    }

    public void returnMoney(DataHelper.NumberCardTwo card, String value) {
        button.last().click();
        currentCard.doubleClick().sendKeys(Keys.BACK_SPACE);
        currentCard.setValue(value);
        orderNumberCard.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        orderNumberCard.setValue(card.getNumber());
        currentButton.click();

    }

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}