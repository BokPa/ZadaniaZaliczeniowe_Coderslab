package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthenticationPage {
    private WebDriver driver;

    public AuthenticationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void putAllNeededCredentials(String email) {

        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&create_account=1");
        driver.findElement(By.className("radio-inline")).click();
        driver.findElement(By.name("firstname")).sendKeys("name");
        driver.findElement(By.name("lastname")).sendKeys("surname");
        driver.findElement(By.name("password")).sendKeys("Pass123");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.cssSelector("button.btn.btn-primary.form-control-submit.float-xs-right")).click();
        boolean alertExists;
        for (; ; ) {
            try {
                driver.findElement(By.className("alert"));
                alertExists = true;
            } catch (NoSuchElementException e) {
                alertExists = false;
            }
            if (alertExists) {
                driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&create_account=1");
                driver.findElement(By.className("radio-inline")).click();
                driver.findElement(By.name("firstname")).sendKeys("name");
                driver.findElement(By.name("lastname")).sendKeys("surname");
                driver.findElement(By.name("password")).sendKeys("Pass123");
                email = email + "17";
                driver.findElement(By.name("email")).sendKeys(email);
                driver.findElement(By.cssSelector("button.btn.btn-primary.form-control-submit.float-xs-right")).click();
            } else
                break;
        }

    }


    public void loginAs(String email) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Pass123");
        driver.findElement(By.id("submit-login")).click();
    }
}
