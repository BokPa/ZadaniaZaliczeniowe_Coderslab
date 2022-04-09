package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.SocketException;

public class ExternalSites {
    private WebDriver driver;

    public ExternalSites(WebDriver driver) {

        this.driver = driver;
    }
    public String getGeneratedEmail() {
        driver.get("https://www.fakenamegenerator.com/");
        String email = driver.findElement(By.xpath("//div/h3")).getText();
        email = email.replaceAll(" ","");
        driver.findElement(By.xpath("//a[@title='Fake Mail Generator']")).click();
        email += driver.findElement(By.id("home-email")).getText() + driver.findElement(By.id("domain")).getText();
        driver.close();
        return email;


    }
}
