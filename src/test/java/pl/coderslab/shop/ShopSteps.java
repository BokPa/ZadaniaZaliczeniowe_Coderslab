package pl.coderslab.shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ShopSteps {
    private WebDriver driver;
    String email;

    @Given("user has created account")
    public void createAnAccount() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //get generated email address
        ExternalSites externalSites = new ExternalSites(driver);
        email = externalSites.getGeneratedEmail();
        //register and logout
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.putAllNeededCredentials(email);
    }

    @And("shops authentication page is displayed")
    public void shopsAuthenticationPageIsDisplayed() {
        MainPage mainPage = new MainPage(driver);
        mainPage.enterAuthenticationScreen();
    }

    @When("user logs in")
    public void userLogsIn() {
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.loginAs(email);
    }

    @And("user enters his account page")
    public void userEntersHisAccountPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.enterYourAccountPage();
    }

    @And("user goes to Your addresses page")
    public void userGoesToAddressesPage() {
        YourAccountPage yourAccountPage = new YourAccountPage(driver);
        yourAccountPage.goToAddressesPage();
    }

    @And("user clicks Create new address button")
    public void userClicksCreateNewAddressButton() {
        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);
        yourAddressesPage.createNewAddress();

    }

    @And("^user enters Alias (.*), Address (.*), City (.*), Postal code (.*), Country (.*), Phone (.*)$")
    public void userEntersAliasAddressCityPostalCodeCountryPhone(String alias, String address, String city, String zip, String country, String phone) {
        NewAddressCreatorPage newAddressCreatorPage = new NewAddressCreatorPage(driver);
        newAddressCreatorPage.enterAlias(alias);
        newAddressCreatorPage.enterPhoneNumber(phone);
        newAddressCreatorPage.enterNeededCredentials(address, city, zip, country);

    }

    @And("user clicks Save")
    public void userClicksSave() {
        NewAddressCreatorPage newAddressCreatorPage = new NewAddressCreatorPage(driver);
        newAddressCreatorPage.clickSaveButton();
        // w tym miejscu można by dodać zabezpieczenie na nieprawidłowy format danych, że zastępuje je prawidłowymi. Problem w tym,
        // że nie za bardzo potrafię odróżnić czy alert by dotyczył telefonu czy kodu pocztowego, więc trzebaby podmienić oba.
        // Zakładam jednak,że tester wprowadza prawidłowe dane do testowania. No chyba, że chce testować odrzucanie nieprawiłowych, ale
        // to zupełnie inaczej podchodziłbym do kodu.
    }

    @Then("^user sees success message with text (.*)$")
    public void userSeesSuccessMessageWithText(String message) {
        assertEquals(message, driver.findElement(By.xpath("//*[text()='Address successfully added!']")).getText());

    }

    @And("^on the first place of address list is visible alias (.*)$")
    public void onTheFirstPlaceOfAddressListIsVisibleAlias(String message) {
        assertEquals(message, driver.findElement(By.xpath("//h4")).getText());
        //można też wejść w update i sprawdzić wszystkie pokolei dane, czy się prawidłowo zapisały, ale to wystarczy powtarzaać wykonany juz kod.
    }

    @And("close shop page")
    public void closeShopPage() {
        driver.close();

    }


}
