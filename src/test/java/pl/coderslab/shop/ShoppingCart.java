package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCart {
    private WebDriver driver;

    public ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckoutPage() {
        driver.findElement(By.xpath("//*[text()='Proceed to checkout']")).click();
    }
}
