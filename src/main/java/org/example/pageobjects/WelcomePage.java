package org.example.pageobjects;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class WelcomePage extends BasePage{

    private final List<String> exploreTypesList=
            new ArrayList<>(Arrays.asList("Events", "Experiences", "Restaurants", "Shows", "Streams", "Conferences"));

    private final By searchField= By.xpath("//input[@type='search']");
    private final By searchButton= By.xpath("//button/img[@alt='Search']");
    private final By typesList= By.xpath("//div/ul[@class='flex flex-wrap gap-2']/li");
    private final By seasonsList= By.xpath("//div/ul[@class='grid grid-cols-3 gap-1 md:grid-cols-4']/li");
    private final By searchResultsList= By.xpath("//div/ul/li[@class='flex items-center gap-2']");
    private final By searchNoRecordError= By.xpath("//div[@class=\"mx-auto max-w-lg space-y-4 p-4 text-center\"]/p");

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public boolean validateSearchTypesList()
    {
        List<WebElement> typesListElements= findElements(typesList);
        for (int i=0; i<typesListElements.size();i++)
        {
            if(!Objects.equals(typesListElements.get(i).getText(), exploreTypesList.get(i))) return false;
        }
        return true;
    }

    public boolean validateSearchPlaceholder()
    {
        return getAttributeValue(searchField,"placeholder").equals("Search events, experiences, restaurants, artists...");
    }
}
