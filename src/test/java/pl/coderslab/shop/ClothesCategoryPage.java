package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClothesCategoryPage {

    private WebDriver driver;

    public ClothesCategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseHummingbirdPrintedSweater(){
        driver.findElement(By.xpath("//*[@data-id-product-attribute='9']")).click();
    }
}
