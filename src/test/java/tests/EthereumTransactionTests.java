package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.assertionMode;

@Listeners(SoftAsserts.class)

public class EthereumTransactionTests extends BaseTest {

    // Проверка отображаемых данных в результатах поиска подтверждённой транзакции Ethereum
    @Test
    public void checkDataOfConfirmedTransaction(){
        assertionMode = Configuration.AssertionMode.SOFT;
        String jsonString = api.getSearch("0x37261284f818e90516737e7fcb74980b926e11f6c51cf59c845cda3af80e0179");
        homePage.openHomePage();
        searchFormBlock.makeRequest("0x37261284f818e90516737e7fcb74980b926e11f6c51cf59c845cda3af80e0179");
        searchPage.checkTransactionTitleCorrect("Ethereum");
        searchPage.checkTxnTabHeaderIsVisible();
        searchPage.checkTxnHashValIsCorrect(jsonString);
        searchPage.checkTxnStatusValIsCorrect(jsonString);
        searchPage.checkTxnIncludeInBlocksValIsCorrect(jsonString);
        searchPage.checkTxnTimestampValIsCorrect(jsonString);
        searchPage.checkTxnFromValIsCorrect(jsonString);
        searchPage.checkTxnToValIsCorrect(jsonString);
        searchPage.checkTxnValueValIsCorrect(jsonString);
        searchPage.checkTxnTransferTokensValIsCorrect(jsonString);
        searchPage.checkTxnGasLimitValIsCorrect(jsonString);
        searchPage.checkTxnGasUsedByTxnValIsCorrect(jsonString);
    }

    // Проверка отображаемых данных в результатах поиска неподтверждённой транзакции Ethereum
    /*
    *  Заметил, что в результате поиска неподтверждённой транзакции не отображаются некоторые данные, хотя АПИ их возвращает.
    *  Но у меня возникла проблема с получением актуального хеша неподтверждённой транзакции.
    * */
    @Test
    public void checkDataOfPendingTransaction(){
        assertionMode = Configuration.AssertionMode.SOFT;
        String pendingTrn = "0xcfb263872bb16779ce5f0f79698826d23b422cfada01192f370a7d96ac1f96e5";
        String jsonString = api.getSearch(pendingTrn);
        homePage.openHomePage();
        searchFormBlock.makeRequest(pendingTrn);
        searchPage.checkTransactionTitleCorrect("Ethereum");
        searchPage.checkTxnTabHeaderIsVisible();
        searchPage.checkTxnHashValIsCorrect(jsonString);
        searchPage.checkTxnStatusValIsCorrect(jsonString);
        searchPage.checkTxnIncludeInBlocksValIsCorrect(jsonString);
        searchPage.checkTxnTimestampValIsCorrect(jsonString);
        searchPage.checkTxnFromValIsCorrect(jsonString);
        searchPage.checkTxnToValIsCorrect(jsonString);
        searchPage.checkTxnValueValIsCorrect(jsonString);
        searchPage.checkTxnTransferTokensValIsCorrect(jsonString);
        searchPage.checkTxnGasLimitValIsCorrect(jsonString);
        searchPage.checkTxnGasUsedByTxnValIsCorrect(jsonString);
    }

    // Проверка отображаемых данных в результатах поиска конфликтующей транзакции Ethereum
    @Test
    public void checkDataOfConflictingTransaction(){
        assertionMode = Configuration.AssertionMode.SOFT;
        String pendingTrn = "0x2da54ac1ba7c8770bf31add4d12b88970fe1de4992a6c40bd60a7707ccf5555a";
        String jsonString = api.getSearch(pendingTrn);
        /*
        *  Здесь скорее всего должны быть проверки данных, но метод ничего не возвращает
        * */
    }

}
