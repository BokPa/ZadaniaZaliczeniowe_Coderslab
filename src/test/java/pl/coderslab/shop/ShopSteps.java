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
        driver.findElement(By.className("logout")).click();
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
        // w tym miejscu mo??na by doda?? zabezpieczenie na nieprawid??owy format danych, ??e zast??puje je prawid??owymi. Problem w tym,
        // ??e nie za bardzo potrafi?? odr????ni?? czy alert by dotyczy?? telefonu czy kodu pocztowego, wi??c trzebaby podmieni?? oba.
        // Zak??adam jednak,??e tester wprowadza prawid??owe dane do testowania. No chyba, ??e chce testowa?? odrzucanie nieprawi??owych, ale
        // to zupe??nie inaczej podchodzi??bym do kodu.
    }

    @Then("^user sees success message with text (.*)$")
    public void userSeesSuccessMessageWithText(String message) {
        assertEquals(message, driver.findElement(By.xpath("//*[text()='Address successfully added!']")).getText());

    }

    @And("^on the first place of address list is visible alias (.*)$")
    public void onTheFirstPlaceOfAddressListIsVisibleAlias(String message) {
        assertEquals(message, driver.findElement(By.xpath("//h4")).getText());
        //mo??na te?? wej???? w update i sprawdzi?? wszystkie pokolei dane, czy si?? prawid??owo zapisa??y, ale to wystarczy powtarzaa?? wykonany juz kod.
    }

    @And("close shop page")
    public void closeShopPage() {
        driver.close();

    }


}
