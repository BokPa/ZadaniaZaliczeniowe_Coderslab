package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewAddressCreatorPage {
    private WebDriver driver;

    public NewAddressCreatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterNeededCredentials(String address, String city, String zip, String country) {
        driver.findElement(By.name("address1")).sendKeys(address);
        driver.findElement(By.name("postcode")).sendKeys(zip);
        driver.findElement(By.name("city")).sendKeys(city);
        //rozwijana lista dla kraju
        driver.findElement(By.name("id_country")).click();

        driver.findElement(By.xpath("//*[.='" + country + "']")).click();
    }

    public void enterAlias(String alias) {
        try {
            driver.findElement(By.name("alias")).sendKeys(alias);
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            driver.findElement(By.name("alias")).sendKeys(alias);
        }
    }

    public void enterPhoneNumber(String phone) {
        try {
            driver.findElement(By.name("phone")).sendKeys(phone);
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            driver.findElement(By.name("phone")).sendKeys(phone);
        }

    }

    public void clickSaveButton() {
        driver.findElement(By.cssSelector("button.btn.btn-primary.float-xs-right")).click();
    }

    public void goToAddressesPage() {
        driver.findElement(By.xpath("//*[text()='Addresses']")).click();
    }
}
