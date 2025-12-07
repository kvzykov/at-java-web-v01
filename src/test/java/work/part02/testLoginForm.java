package work.part02;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class testLoginForm {
    @Test
    public void testElementSearchMethods() {
        Configuration.browser="firefox";
        open("https://slqamsk.github.io/cases/slflights/v01/");
        // Негативный сценарий
//        SelenideElement myElement = $("input[id=username]");
        $("input[id=username]").sendKeys("testuser");
        $("input[id=password]").sendKeys("testpass");

//        myElement.sendKeys("testuser");
        $(By.id("loginButton")).click();
        $(By.className("message")).shouldHave(text("Неверное имя пользователя или пароль."));
        sleep(5000);
    }
}