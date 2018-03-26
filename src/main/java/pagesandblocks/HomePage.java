package pagesandblocks;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage {

    private static final SelenideElement LOGO = $(byXpath("//div[@class='home__logo']")); // логотип на домашней странице

    // Открывает домашнюю страницу в браузере
    public void openHomePage() {
        open("https://desearch.com/");
        waitUntilLogoIsVisible();
    }

    // Ждёт загрузки логотипа на домашней странице
    private void waitUntilLogoIsVisible(){
        LOGO.waitUntil(visible,10000);
    }

}
