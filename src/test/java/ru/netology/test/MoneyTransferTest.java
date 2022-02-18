package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    // сумма перевода
    String cash = "100";
    DashboardPage dashboardPage;
    DataHelper.NumberCardOne cardInfoOne;
    DataHelper.NumberCardTwo cardInfoTwo;


    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
         dashboardPage = verificationPage.validVerify(verifyInfo);
         cardInfoOne = DataHelper.getInnerCardOne(authInfo);
         cardInfoTwo = DataHelper.getInnerCardTwo(authInfo);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {

        dashboardPage.transferMoney(cardInfoOne, cash);
        assertEquals(10100, dashboardPage.getFirstCardBalance());

    }

    @AfterEach
    void cleanUp(){
        dashboardPage.returnMoney(cardInfoTwo,cash);
    }
}