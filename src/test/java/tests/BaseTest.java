package tests;

import api.API;
import org.testng.annotations.BeforeClass;
import pagesandblocks.HomePage;
import pagesandblocks.PageObject;
import pagesandblocks.SearchFormBlock;
import pagesandblocks.SearchPage;

public class BaseTest {

    PageObject pageObject = new PageObject();
    HomePage homePage = new HomePage();
    SearchFormBlock searchFormBlock = new SearchFormBlock();
    SearchPage searchPage = new SearchPage();
    API api = new API();

    @BeforeClass
    public void openHomePage(){
        homePage.openHomePage();
    }

}
