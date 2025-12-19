package work.part02;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class pizzaSelect {
    @Test
    public void pizzaFind() {
        Configuration.browser="firefox";
        Selenide.open("https://slqamsk.github.io/tmp/xPath01.html");

        $x("//h3").shouldHave(exactText("Маргарита"));
//        $x("//label[@for='name']").shouldHave(exactText("Ваше имя:"));
//        $x("//p[@class='info-text'][1]").shouldHave(exactText("Это первый информационный текст."));
        
    }
}
