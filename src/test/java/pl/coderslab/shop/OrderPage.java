package pl.coderslab.shop;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class OrderPage {
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void assertAddress(String address, String city, String zip, String country) {
        WebElement element = driver.findElement(By.xpath("//*[text()='" + address + "']"));
        String[] addressDetails = {address, city, zip, country};
        String[] lines = element.getText().split("\\r?\\n");
        for (int i = 1; i < lines.length - 1; i++) {
            assertEquals(addressDetails[i - 1], lines[i]);
        }
    }

    public void acceptAddress() {
        driver.findElement(By.name("confirm-addresses")).click();
    }

    public void choosePrestaShopDeliver() {
        WebElement deliveryOption1 = driver.findElement(By.id("delivery_option_1"));
        if (!deliveryOption1.isSelected()) {
            deliveryOption1.click();
        }

    }

    public void acceptDeliveryOption() {
        driver.findElement(By.name("confirmDeliveryOption")).click();
    }

    public void choosePayByCheckAndContinue() {
        driver.findElement(By.id("payment-option-1")).click();
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary.center-block")).click();
    }


}
