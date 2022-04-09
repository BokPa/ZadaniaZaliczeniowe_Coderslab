package pl.coderslab.shop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class Shop_Zad2Test {


    private static WebDriver driver;

    private String email;
    private String address = "Prozniowa 16";
    private String city = "Krater";
    private String country = "United Kingdom";
    private String zip = "66-666";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        email = generateUniqueEmail();
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        authenticationPage.putAllNeededCredentials(email);
        MainPage mainPage = new MainPage(driver);
        mainPage.enterYourAccountPage();
        YourAccountPage yourAccountPage = new YourAccountPage(driver);
        yourAccountPage.goToAddressesPage();
        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);
        yourAddressesPage.createNewAddress();
        NewAddressCreatorPage newAddressCreatorPage = new NewAddressCreatorPage(driver);
        String alias = "bronek";
        newAddressCreatorPage.enterAlias(alias);
        newAddressCreatorPage.enterPhoneNumber("0000000000");
        newAddressCreatorPage.enterNeededCredentials(address, city, zip, country);
        newAddressCreatorPage.clickSaveButton();
        yourAddressesPage.logout();
    }

    @Test
    public void buyingHummingbirdPrintedSweater() throws IOException {
        MainPage mainPage = new MainPage(driver);
        YourAccountPage yourAccountPage = new YourAccountPage(driver);
        ClothesCategoryPage clothesCategoryPage = new ClothesCategoryPage(driver);
        ItemDetailsPage itemDetailsPage = new ItemDetailsPage(driver);
        mainPage.enterAuthenticationScreen();
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        authenticationPage.loginAs(email);
        yourAccountPage.goToMainPage();
        mainPage.chooseCategory("clothes");
        clothesCategoryPage.chooseHummingbirdPrintedSweater();
        //Sprawdzenia czy się zgadza zniżka
        double price0 = changeStringPriceToDouble(driver.findElement(By.className("regular-price")).getText());
        double priceDiscounted = changeStringPriceToDouble(driver.findElement(By.xpath("//span[@itemprop='price']")).getText());
        assertEquals(0.2 * price0, price0 - priceDiscounted, 0.0);
        //Wybór szczegółów
        itemDetailsPage.setDetailsAndAddToCart("l", 15);
        //Przejdź do checkouta
        itemDetailsPage.proceedToCheckoutPage();
        ShoppingCart shoppingCart = new ShoppingCart(driver);
        shoppingCart.proceedToCheckoutPage();
        //potwierdzenie adresu
        OrderPage orderPage = new OrderPage(driver);
        orderPage.assertAddress(address, city, zip, country);
        //Akceptacja i ciągnięcie dalej
        orderPage.acceptAddress();
        orderPage.choosePrestaShopDeliver();
        orderPage.acceptDeliveryOption();
        orderPage.choosePayByCheckAndContinue();
        //Screenshot
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(".//screenshot/screen" + System.currentTimeMillis() + ".png"));


    }

    @After
    public void tearDown() {
          driver.quit();
    }

    private String generateUniqueEmail() {
        return "unique." + System.currentTimeMillis() + "@gmail.com";
    }

    private double changeStringPriceToDouble(String price) {
        price = price.replace("€", "");
        return Double.parseDouble(price);
    }


}
