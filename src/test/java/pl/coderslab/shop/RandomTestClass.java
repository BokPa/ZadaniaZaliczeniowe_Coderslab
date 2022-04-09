package pl.coderslab.shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.SocketException;
import java.time.Duration;

public class RandomTestClass {
    public static void main(String[] args)  {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
       // driver.get("https://hotel-testlab.coderslab.pl/");
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ExternalSites externalSites = new ExternalSites(driver);
        System.out.println(externalSites.getGeneratedEmail());
    }
}
