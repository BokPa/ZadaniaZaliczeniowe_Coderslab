package pl.coderslab.shop;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features/shop-put-new-address.feature",
        plugin = {"pretty", "html:out.html"})

public class Shop_Zad1Test {
}


