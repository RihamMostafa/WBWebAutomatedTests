package org.example.pageobjects;

import lombok.Getter;
import lombok.Setter;
import org.example.utils.PropertiesFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Getter @Setter
public class LoginAndRegistrationPage extends BasePage {

    PropertiesFileReader propertiesReader = new PropertiesFileReader("pagesConfig\\LoginAndRegistrationConfigs.properties");

    private final String URL=propertiesReader.getProperty("URL");
    private final By usernameField= By.name("email");
    private final By passwordField= By.id("password");
    private final By loginBtn= By.id("email-login-button");
    private final By createAccountBtn= By.xpath("//*[@id=\"email-login\"]/div[5]/button");
    private final By firstNameField= By.id("first_name");
    private final By lastNameField= By.id("last_name");
    private final By emailField= By.id("email");
    private final By emailHint= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[2]/div[1]/p");
    private final By confirmEmailField= By.id("confirm_email");
    private final By passwordRegistrationField= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[3]/div[1]/div/input");
    private final By passwordRegistrationHint= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[3]/div[2]/ul/li/span");
    private final By countryCode= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[4]/div/div[1]/div/div/div/div/input");
    private final By mobileField= By.name("mobile");
    private final By termsAndConditionsCheckBox= By.xpath("//*[@id=\"signup-wrapper\"]/div[3]/div[1]/label/input");
    private final By termsAndConditionsDescription= By.xpath("//*[@id=\"signup-wrapper\"]/div[3]/div[1]/label/span");
    private final By termsAndConditionsHyperlink= By.xpath("//*[@id=\"signup-wrapper\"]/div[3]/div[1]/label/span/a");
    private final By newsletterCheckBox= By.xpath("//*[@id=\"signup-wrapper\"]/div[3]/div[2]/label/input");
    private final By newsletterDescription= By.xpath("//*[@id=\"signup-wrapper\"]/div[3]/div[2]/label/span");
    private final By newsletterDescriptionExtension = By.xpath("//*[@id=\"signup-wrapper\"]/div[3]/div[2]/label/span/strong");
    private final By createAccountRegistrationBtn= By.xpath("//*[@id=\"signup-wrapper\"]/div[3]/div[3]/button");

    private final By firstNameError= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[1]/div[1]/div/p");
    private final By lastNameError= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[1]/div[2]/div/p");
    private final By emailError= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[2]/div[1]/div/p");
    private final By emailConfirmationError= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[2]/div[2]/div/p");
    private final By passwordError= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[3]/div[1]/p");
    private final By mobileError= By.xpath("//*[@id=\"signup-form-info\"]/fieldset[4]/p");
    private final By termsAndConditionsError= By.xpath("//*[@id=\"signup-wrapper\"]/div[3]/div[1]/p");

    private final String expectedEmailHint= propertiesReader.getProperty("expectedEmailHint");
    private final String expectedPasswordHint= propertiesReader.getProperty("expectedPasswordHint");
    private final String expectedDefaultCountryCodeValue= propertiesReader.getProperty("expectedDefaultCountryCodeValue");
    private final String expectedTermsAndConditionsText= propertiesReader.getProperty("expectedTermsAndConditionsText");
    private final String expectedNewsLetterText= propertiesReader.getProperty("expectedNewsLetterText");


    public LoginAndRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        typeText(usernameField, username);
        typeText(passwordField, password);
        clickElement(loginBtn);
    }

    public void register(String firstName,
                         String lastName,
                         String email,
                         String emailConfirmation,
                         String password,
                         String countryCode,
                         String mobile,
                         Boolean termsAndConditions,
                         Boolean newsLetter)
    {
        typeText(firstNameField, firstName);
        typeText(lastNameField, lastName);
        typeText(emailField, email);
        typeText(confirmEmailField, emailConfirmation);
        typeText(passwordRegistrationField,password);
        typeText(this.countryCode,countryCode);
        clickElement(mobileField);
        typeText(mobileField,mobile);
        if (termsAndConditions && !isChecked(termsAndConditionsCheckBox)) clickElement(termsAndConditionsCheckBox);
        if (newsLetter && !isChecked(newsletterCheckBox)) clickElement(newsletterCheckBox);

        clickElement(createAccountRegistrationBtn);
    }

    public void registerWithNoData()
    {
        clickElement(createAccountRegistrationBtn);
    }

}
