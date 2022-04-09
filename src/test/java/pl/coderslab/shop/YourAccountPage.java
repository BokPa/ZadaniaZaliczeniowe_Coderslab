package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class YourAccountPage {
    private WebDriver driver;

    public YourAccountPage(WebDriver driver) {

        this.driver = driver;
    }

    public void goToAddressesPage() {

        try {
            driver.findElement(By.id("address-link")).click();
            NewAddressCreatorPage newAddressCreatorPage = new NewAddressCreatorPage(driver);
            newAddressCreatorPage.goToAddressesPage();
        } catch (NoSuchElementException e) {
            driver.findElement(By.id("addresses-link")).click();
        }
    }
    public void goToMainPage(){
        driver.findElement(By.className("logo")).click();
    }
}
