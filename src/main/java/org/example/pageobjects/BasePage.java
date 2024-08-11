package org.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Default timeout of 10 seconds
        PageFactory.initElements(driver, this); // Initialize PageFactory elements
    }

    public void open(String url) {
        driver.get(url);
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickElement(By locator) {
        WebElement element = findElement(locator);
        element.click();
    }

    public void typeText(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        WebElement element = findElement(locator);
        return element.getText();
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void selectFromDropdown(By locator, String optionText) {
        WebElement dropdown = findElement(locator);
        dropdown.click();
        WebElement option = dropdown.findElement(By.xpath("//option[text()='" + optionText + "']"));
        option.click();
    }

    public void waitForPageToLoad() {
        // Example: Check if an element that indicates the page is loaded is present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pageLoadedIndicator")));
    }

    public boolean isChecked(By locator) {
        WebElement element = findElement(locator);
        return element.isSelected();
    }

    public String getValue(By locator) {
        WebElement element = driver.findElement(locator);
        String tagName = element.getTagName();

        if ("input".equalsIgnoreCase(tagName) || "textarea".equalsIgnoreCase(tagName)) {
            return element.getAttribute("value");  // Returns the value attribute for input fields
        } else {
            return element.getText();  // Returns the visible text for other elements
        }
    }

    public WebElement getParent(By locator) {
        WebElement element = driver.findElement(locator);
        return element.findElement(By.xpath(".."));
    }
}
