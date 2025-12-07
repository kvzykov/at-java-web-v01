package work.part02;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FeeCalculationTests {
    @Test
    public void testElementSearchMethods() {
        Configuration.browser="firefox";
        open("https://slqa.ru/cases/fc/v01/");
        SelenideElement myElement = $("input[name=sum]");

        myElement.sendKeys("100");
        $(By.name("submit")).click();

        myElement.sendKeys("2000");
        $(By.name("submit")).click();
    }
}