package org.example.driver;

import org.example.utils.PropertiesFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    static PropertiesFileReader propertiesReader = new PropertiesFileReader("runConfig.properties");

    public static WebDriver setUpDriver() {
        String browser = propertiesReader.getProperty("Browser");

        switch (browser.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();

            case "firefox":
                return new FirefoxDriver();

            case "edge":
                return new EdgeDriver();

            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" not supported.");
        }
    }
}
