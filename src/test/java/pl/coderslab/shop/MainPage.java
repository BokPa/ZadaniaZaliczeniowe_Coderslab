package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterAuthenticationScreen() {
        driver.findElement(By.xpath("//*[text()='Sign in']")).click();
    }

    public void enterYourAccountPage() {
        driver.findElement(By.className("account")).click();
    }

    public void chooseCategory(String category) {
        if (category.matches("Clothes|clothes|CLOTHES")) {
            driver.findElement(By.id("category-3")).click();
        } else if (category.matches("Accessories|accessories|ACCESSORIES")) {
            driver.findElement(By.id("category-6")).click();
        } else
            driver.findElement(By.id("category-9")).click();
    }
}
