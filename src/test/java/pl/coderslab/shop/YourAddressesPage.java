package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourAddressesPage {
    private WebDriver driver;

    public YourAddressesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createNewAddress() {
        driver.findElement(By.xpath("//*[text()='Create new address']")).click();
    }

    public void logout() {
        driver.findElement(By.className("logout")).click();
    }
}
