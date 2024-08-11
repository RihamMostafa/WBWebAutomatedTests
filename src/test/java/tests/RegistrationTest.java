package tests;

import org.example.pageobjects.LoginAndRegistrationPage;
import org.example.utils.PropertiesFileReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class RegistrationTest extends BaseTest{

    private LoginAndRegistrationPage registrationPage;
    private final PropertiesFileReader propertyReader = new PropertiesFileReader("pagesConfig\\LoginAndRegistrationConfigs.properties");

    @BeforeMethod
    public void setUpLoginAndRegistrationPage() {

        registrationPage = new LoginAndRegistrationPage(driver);
        registrationPage.open(registrationPage.getURL());
        registrationPage.clickElement(registrationPage.getCreateAccountBtn());
    }

    @Test
    public void testRegistration_GeneralUiValidations() {
        SoftAssert a = new SoftAssert();
        a.assertEquals(registrationPage.getText(registrationPage.getEmailHint()),
                registrationPage.getExpectedEmailHint());
        a.assertEquals(registrationPage.getText(registrationPage.getPasswordRegistrationHint()),
                registrationPage.getExpectedPasswordHint());
        a.assertEquals(registrationPage.getValue(registrationPage.getCountryCode()),
                registrationPage.getExpectedDefaultCountryCodeValue());
        a.assertEquals(registrationPage.getText(registrationPage.getTermsAndConditionsDescription()),
                registrationPage.getExpectedTermsAndConditionsText());
        a.assertEquals(registrationPage.getText(registrationPage.getNewsletterDescription()),
                registrationPage.getExpectedNewsLetterText());

        a.assertFalse(registrationPage.isChecked(registrationPage.getTermsAndConditionsCheckBox()));
        a.assertTrue(registrationPage.isChecked(registrationPage.getNewsletterCheckBox()));
        a.assertAll();
    }

    @Test
    public void testRegistration_MissingMandatoryFields()
    {
        registrationPage.registerWithNoData();
        String ExpectedError= "Required";

        SoftAssert a = new SoftAssert();
        a.assertEquals(registrationPage.getText(registrationPage.getFirstNameError()),
                ExpectedError);
        a.assertEquals(registrationPage.getText(registrationPage.getLastNameError()),
                ExpectedError);
        a.assertEquals(registrationPage.getValue(registrationPage.getEmailError()),
                ExpectedError);
        a.assertEquals(registrationPage.getText(registrationPage.getEmailConfirmationError()),
                ExpectedError);
        a.assertEquals(registrationPage.getText(registrationPage.getPasswordError()),
                ExpectedError);
        a.assertEquals(registrationPage.getText(registrationPage.getMobileError()),
                ExpectedError);
        a.assertEquals(registrationPage.getText(registrationPage.getTermsAndConditionsError()),
                ExpectedError);

        a.assertAll();
    }

    @Test
    public void testRegistration_InvalidEmail()
    {
        registrationPage.register(propertyReader.getProperty("validFirstName"),
                propertyReader.getProperty("validLastName")
                ,"ri"
                ,propertyReader.getProperty("validEmail")
                ,propertyReader.getProperty("validPassword")
                ,propertyReader.getProperty("validCountryCode"),
                propertyReader.getProperty("validMobile")
                ,true,false);

        assertEquals(registrationPage.getValue(registrationPage.getEmailError()),
                "Invalid Email");

    }

    @Test
    public void testRegistration_EmailMissMatch()
    {
        registrationPage.register(propertyReader.getProperty("validFirstName"),
                propertyReader.getProperty("validLastName")
                ,propertyReader.getProperty("validEmail")
                ,"ri_x@gmail.com"
                ,propertyReader.getProperty("validPassword")
                ,propertyReader.getProperty("validCountryCode"),
                propertyReader.getProperty("validMobile")
                ,true,false);

        assertEquals(registrationPage.getText(registrationPage.getEmailConfirmationError()),
                "Emails do not match");
    }

    @Test
    public void testRegistration_InvalidMobileNumber()
    {
        registrationPage.register(propertyReader.getProperty("validFirstName"),
                propertyReader.getProperty("validLastName")
                ,propertyReader.getProperty("validEmail")
                ,propertyReader.getProperty("validEmail")
                ,propertyReader.getProperty("validPassword")
                ,propertyReader.getProperty("validCountryCode")
                ,"234"
                ,true,false);

        assertEquals(registrationPage.getText(registrationPage.getMobileError()),
                "Invalid mobile");
    }

    @Test
    public void testRegistration_InvalidPassword()
    {
        registrationPage.register(propertyReader.getProperty("validFirstName"),
                propertyReader.getProperty("validLastName")
                ,propertyReader.getProperty("validEmail")
                ,propertyReader.getProperty("validEmail")
                ,"1234593839"
                ,propertyReader.getProperty("validCountryCode"),
                propertyReader.getProperty("validMobile")
                ,true,false);

        assertEquals(registrationPage.getText(registrationPage.getPasswordError()),
                "Invalid password");
    }


    @Test(enabled = false)
    public void testRegistrationValidScenario() {
        /*
        disabled due to test data limitations
        1. we can assert here the mobile number validation
        2. screen redirection.
        3. after completing the registration same user is able to log in successfully
        */
    }

}
