package tests;

import org.example.pageobjects.WelcomePage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;

public class SearchTest extends BaseTest{
    private WelcomePage searchWelcomePage;
    String URL= "https://webook.com/en";

    @BeforeMethod
    public void setUpLoginAndRegistrationPage() {

        searchWelcomePage = new WelcomePage(driver);
        searchWelcomePage.open(URL);
    }

    @Test
    public void testSearchBar_WelcomeScreen_UIValidations()
    {
        assertTrue(searchWelcomePage.validateSearchPlaceholder());
        searchWelcomePage.clickElement(searchWelcomePage.getSearchField());
        assertEquals(searchWelcomePage.findElements(searchWelcomePage.getTypesList()).size(),
                searchWelcomePage.getExploreTypesList().size());
        assertTrue(searchWelcomePage.validateSearchTypesList());
    }

    @Test
    public void testSearchByText_ValidSearchKeyword()
    {
        searchWelcomePage.clickElement(searchWelcomePage.getSearchField());
        searchWelcomePage.typeText(searchWelcomePage.getSearchField(),"Book"); //any valid value
        assertFalse(searchWelcomePage.findElements(searchWelcomePage.getSearchResultsList()).isEmpty());

        searchWelcomePage.clickElement(searchWelcomePage.getSearchButton());
        assertEquals(searchWelcomePage.getCurrentURL(),URL+"/search?q=Book");
        //we should assert on the search results based on the setup/db and the requirements

    }

    @Test
    public void testSearchByText_NonExistSearchKeyword()
    {
        searchWelcomePage.clickElement(searchWelcomePage.getSearchField());
        searchWelcomePage.typeText(searchWelcomePage.getSearchField(),"XYZ"); //any invalid value
        assertEquals(searchWelcomePage.getText(searchWelcomePage.getSearchNoRecordError()),
                "Sorry, we could not find what you were looking for.");

    }

    @Test
    public void testSearchByCategory()
    {
        searchWelcomePage.clickElement(searchWelcomePage.getSearchField());
        WebElement _1stCategory =searchWelcomePage.findElements(searchWelcomePage.getTypesList()).get(0);
        String categoryName= _1stCategory.getText();
        _1stCategory.click();
        assertEquals(searchWelcomePage.getCurrentURL(),URL+"/explore?type="+categoryName.toLowerCase());
    }

}
