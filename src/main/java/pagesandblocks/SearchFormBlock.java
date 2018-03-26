package pagesandblocks;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

public class SearchFormBlock {

    private static final SelenideElement SEARCH_FORM_INPUT = $(byXpath("//app-search-form//input")); // поле ввода для поиска
    private static final SelenideElement SEARCH_FORM_BUTTON = $(byXpath("//app-search-form//button")); // кнопка поиска

    // Делает запрос поиска
    public void makeRequest(String request){
        searchFormInputSendKeys(request);
        clickSearchBtn();
        SearchPage.waitUntilResultListIsVisible();
    }

    // пишет текст в поле ввода
    private void searchFormInputSendKeys(String request){
        SEARCH_FORM_INPUT.sendKeys(request);
    }
    // кликает по кнопке поиска
    private void clickSearchBtn(){
        SEARCH_FORM_BUTTON.click();
    }
}
