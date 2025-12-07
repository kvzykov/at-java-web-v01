package work.part02;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
public class Search {
    @Test
    public void testElementSearchMethods() {
        Configuration.browser="firefox";
        open("https://slqamsk.github.io/demo/search-demo/");

 /*       By myLocator = By.id("submit-button");
        SelenideElement myElement = Selenide.element(myLocator);
        myElement.shouldBe(Condition.visible);
*/
//        element(By.id("submit-button")).shouldBe(visible);
        $(By.id("user-password")).shouldBe(visible);
        $(By.name("user_email")).shouldBe(visible);
        $(By.className("btn")).shouldBe(visible);
        $(By.tagName("h3")).shouldBe(visible);
        $(By.linkText("Регистрация нового пользователя в системе")).shouldBe(visible);
        $(By.partialLinkText("Контакты")).shouldBe(visible);
    }
}
