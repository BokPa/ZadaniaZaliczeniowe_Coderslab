package pl.coderslab.shop;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ItemDetailsPage {
    private WebDriver driver;

    public ItemDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDetailsAndAddToCart(String size, int amount) {
        driver.findElement(By.cssSelector("select[data-product-attribute='1']")).click();
        driver.findElement(By.name("qty")).clear();
        driver.findElement(By.name("qty")).sendKeys(String.valueOf(amount));
        if (size.matches("S|s|short|smart|Smart|SMART|SHORT|slim|SLIM|Slim"))
            driver.findElement(By.cssSelector("option[title='S']")).click();
        else if (size.matches("M|m|medium|Medium|MEDIUM"))
            driver.findElement(By.cssSelector("option[title='M']")).click();
        else if (size.matches("L|l|Large|LARGE|large"))
            driver.findElement(By.cssSelector("option[title='L']")).click();
        else
            driver.findElement(By.cssSelector("option[title='XL']")).click();


        WebElement add = driver.findElement(By.className("add-to-cart"));
        if (add.isEnabled()) {
            add.click();
        } else {
            Assert.fail();
            System.out.println("There is currently only" + driver.findElement(By.className("data-stock")).getAttribute("data-stock") + "items in stock.");
        }
    }

    public void proceedToCheckoutPage() {
        driver.findElement(By.xpath("//*[text()='Proceed to checkout']")).click();
    }

}
