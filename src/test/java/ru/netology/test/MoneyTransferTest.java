package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelp;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsFromtheSecondTotheFirst() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelp.getAuthInf();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        var dashboardCardPage = verificationPage.validVerify(verificationCode);
        String sumRep = "500";
        var balance1CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard1().getNumber());
        var balance2CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard2().getNumber());
        var dashboardCardReplPage = dashboardCardPage.replenishCard(DataHelp.getCard1().getNumber());
        dashboardCardReplPage.replenishCard(sumRep, DataHelp.getCard2().getNumber());
        var balance1CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard1().getNumber());
        var balance2CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard2().getNumber());
        int sum_Rep = Integer.parseInt(sumRep);
        assertEquals(balance1CardBefoRep + sum_Rep, balance1CardAfteRep);
        assertEquals(balance2CardBefoRep - sum_Rep, balance2CardAfteRep);
    }


   @Test
    void shouldTransferMoneyBetweenOwnCardsFromFirstTotheSecond() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelp.getAuthInf();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        var dashboardCardPage = verificationPage.validVerify(verificationCode);
        String sumRep = "500";
        var balance1CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard1().getNumber());
        var balance2CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard2().getNumber());
        var dashboardCardReplPage = dashboardCardPage.replenishCard(DataHelp.getCard2().getNumber());
        dashboardCardReplPage.replenishCard(sumRep, DataHelp.getCard1().getNumber());
        var balance1CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard1().getNumber());
        var balance2CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard2().getNumber());
        int sum_Rep = Integer.parseInt(sumRep);
        assertEquals(balance1CardBefoRep - sum_Rep, balance1CardAfteRep);
        assertEquals(balance2CardBefoRep + sum_Rep, balance2CardAfteRep);
    }


    @Test
    void shouldDepositAmountExceedsActualAmount() {
        open("http://localhost:9999");
       var loginPage = new LoginPage();
        var authInfo = DataHelp.getAuthInf();
        var verificationPage = loginPage.validLogin(authInfo);
       var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        var dashboardCardPage = verificationPage.validVerify(verificationCode);
       var balance1CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard1().getNumber());
        var balance2CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard2().getNumber());
        int sumRepPlus=balance1CardBefoRep+500;
        var dashboardCardReplPage = dashboardCardPage.replenishCard(DataHelp.getCard2().getNumber());
        dashboardCardReplPage.replenishCard(String.valueOf(sumRepPlus), DataHelp.getCard1().getNumber());
       var balance1CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard1().getNumber());
       var balance2CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard2().getNumber());
       assertEquals(balance1CardBefoRep, balance1CardAfteRep);
       assertEquals(balance2CardBefoRep, balance2CardAfteRep);
    }
}
