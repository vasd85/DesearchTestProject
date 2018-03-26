package pagesandblocks;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage extends PageObject {

    private static final SelenideElement SEARCH_RESULT_LIST = $(byXpath("//div[@class='search-result-list__item']")); // Общий элементвсех типов результатов поиска
    private static final String OPERATIONS_TEXT = "operations";

    // Проверяет, что блок с результатами поиска загрузлся
    public static void waitUntilResultListIsVisible(){
        SEARCH_RESULT_LIST.waitUntil(visible,10000);
    }

/*
*  Элементы блока app-search-result-transaction
* */

    private static final SelenideElement TRANSACTION_TITLE = $(byXpath("//h2[@class='search-result-transaction__title']")); // Заголовок страницы результата поиска транзакций
    private static final SelenideElement TXN_TAB_HEADER = $(byXpath("//thead/tr/th[.='Summary']")); // Заголовок таблицы транзакции
    private static final SelenideElement TXN_HASH_VAL = $(byXpath("//td[.='Hash']/../td[2]")); // Значение строки Hash
    private static final SelenideElement TXN_STATUS_VAL = $(byXpath("//td[.='Status']/../td[2]/span")); // Значение строки Status
    private static final SelenideElement TXN_INCLUDED_IN_BLOCKS_VAL = $(byXpath("//td[.='Included In Blocks']/../td[2]")); // Значение строки Included In Blocks
    private static final SelenideElement TXN_TIMESTAMP_VAL = $(byXpath("//td[.='TimeStamp']/../td[2]")); // Значение строки TimeStamp
    private static final SelenideElement TXN_FROM_VAL = $(byXpath("//td[.='From']/../td[2]")); // Значение строки From
    private static final SelenideElement TXN_TO_VAL = $(byXpath("//td[.='To']/../td[2]")); // Значение строки To
    private static final SelenideElement TXN_VALUE_VAL = $(byXpath("//td[.='Value']/../td[2]")); // Значение строки Value
    private static final SelenideElement TXN_TRANSFER_TOKENS_VAL = $(byXpath("//td[.='Transfer tokens']/../td[2]")); // Значение строки Transfer tokens
    private static final SelenideElement TXN_GAS_LIMIT_VAL = $(byXpath("//td[.='Gas Limit']/../td[2]")); // Значение строки Gas Limit
    private static final SelenideElement TXN_GAS_USED_BY_TXN_VAL = $(byXpath("//td[.='Gas Used By Txn']/../td[2]")); // Значение строки Gas Used By Txn


    private void checkElementLink(SelenideElement element, String link, int index){
        element.$x("a["+index+"]").shouldHave(attribute("href","https://desearch.com/search?q=" + link));
    }
    private void checkElementLink(SelenideElement element, String link){
        checkElementLink(element,link,1);
    }
    private void elementShouldHaveExactText(SelenideElement element, String text){
        element.shouldHave(exactText(text));
    }

    // Проверяет текст заголовка и ссылку
    public void checkTransactionTitleCorrect(String asset){
        elementShouldHaveExactText(TRANSACTION_TITLE,asset + " Transaction");
        checkElementLink(TRANSACTION_TITLE,asset);
    }

    // Методы проверяют текст поля и ссылку в тблице результатов поиска транзакции
    public void checkTxnTabHeaderIsVisible(){
        TXN_TAB_HEADER.shouldBe(visible);
    }
    public void checkTxnHashValIsCorrect(String jsonString){
        String hash = getJsonKey2Value(jsonString,"data","hash");
        elementShouldHaveExactText(TXN_HASH_VAL,hash);
        checkElementLink(TXN_HASH_VAL,hash);
    }
    public void checkTxnStatusValIsCorrect(String jsonString){
        String status = getJsonKey2Value(jsonString,"data","status").isEmpty()
                ? "Success": getJsonKey2Value(jsonString,"data","status"); // не уверен, что нет других вариантов для статуса, но пусть пока так...
        elementShouldHaveExactText(TXN_STATUS_VAL,status);
    }
    public void checkTxnIncludeInBlocksValIsCorrect(String jsonString){
        String blockNumber = getJsonKey2Value(jsonString,"data","blockNumber");
        String confirmations = getJsonKey2Value(jsonString,"data","confirmations");
        elementShouldHaveExactText(TXN_INCLUDED_IN_BLOCKS_VAL,blockNumber + " ("+ confirmations +" block confirmations)");
        checkElementLink(TXN_INCLUDED_IN_BLOCKS_VAL,blockNumber);
    }
    public void checkTxnTimestampValIsCorrect(String jsonString){
        String timeStamp = timestampToFormat(Long.valueOf(getJsonKey2Value(jsonString,"data","timestamp")));
        elementShouldHaveExactText(TXN_TIMESTAMP_VAL,timeStamp);
    }
    public void checkTxnFromValIsCorrect(String jsonString){
        String fromAddress = getJsonKey2Value(jsonString,"data","from");
        elementShouldHaveExactText(TXN_FROM_VAL,fromAddress);
        checkElementLink(TXN_FROM_VAL,fromAddress);
    }
    public void checkTxnToValIsCorrect(String jsonString){
        String toAddress = getJsonKey2Value(jsonString,"data","to");
        elementShouldHaveExactText(TXN_TO_VAL,toAddress);
        checkElementLink(TXN_TO_VAL,toAddress);
    }
    public void checkTxnValueValIsCorrect(String jsonString){
        String currency = getJsonKey1Value(jsonString,"currency");
        String value = getJsonKey2Value(jsonString,"data","value");
        elementShouldHaveExactText(TXN_VALUE_VAL,value + " "+ currency.toUpperCase());
    }
    public void checkTxnTransferTokensValIsCorrect(String jsonString){
        if(getJsonKey2Value(jsonString,"data", OPERATIONS_TEXT).isEmpty()){
            TXN_TRANSFER_TOKENS_VAL.shouldNotBe(exist);
        } else {
            String intValue = getJsonKey1ValueFromArrayFromKey1(jsonString,"data", OPERATIONS_TEXT,"intValue");
            String symbol = getJsonKey2ValueFromArrayFromKey1(jsonString,"data", OPERATIONS_TEXT,"tokenInfo","symbol");
            String address = getJsonKey2ValueFromArrayFromKey1(jsonString,"data", OPERATIONS_TEXT,"tokenInfo","address");
            String from = getJsonKey1ValueFromArrayFromKey1(jsonString,"data", OPERATIONS_TEXT,"from");
            String to = getJsonKey1ValueFromArrayFromKey1(jsonString,"data", OPERATIONS_TEXT,"to");
            elementShouldHaveExactText(TXN_TRANSFER_TOKENS_VAL,Double.valueOf(intValue)+" "+ symbol + "\nfrom "+ from + "\nto " + to);
            checkElementLink(TXN_TRANSFER_TOKENS_VAL,address);
            checkElementLink(TXN_TRANSFER_TOKENS_VAL,from,2);
            checkElementLink(TXN_TRANSFER_TOKENS_VAL,to,3);
        }
    }
    public void checkTxnGasLimitValIsCorrect(String jsonString){
        String gasLimit = getJsonKey2Value(jsonString,"data","gasLimit");
        elementShouldHaveExactText(TXN_GAS_LIMIT_VAL,NF.format(Integer.valueOf(gasLimit)));
    }
    public void checkTxnGasUsedByTxnValIsCorrect(String jsonString){
        String gasUsed = getJsonKey2Value(jsonString,"data","gasUsed");
        elementShouldHaveExactText(TXN_GAS_USED_BY_TXN_VAL,NF.format(Integer.valueOf(gasUsed)));
    }

}
