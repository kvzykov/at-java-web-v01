package work.part04;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.awt.*;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class homeWork3 {
    SelenideElement btnCalc = $(By.cssSelector("input[type='submit']"));
    SelenideElement resultText = $x("/html/body/div");

    @BeforeEach
    void browserConf (){
        Configuration.browser="firefox";
        Configuration.pageLoadStrategy = "eager";
    }
    @Test
    public void at1() {
        Selenide.open("https://www.specialist.ru/");
        $x("//*[@id=\"cookieConsent__ok\"]").click();
        $x("//*[@id=\"js-mobile-menu\"]/section[3]/div/div/nav/ul/li[2]/a").click();
        $x("/html/body/main/section[1]/div[2]/div/article[3]/div[2]/h3/a").click();
        $x("/html/body/div[5]/section[1]/div[2]/div/a").click();
        $x("//*[@id=\"Filter_CategoriesDirectionFilter\"]").selectOptionByValue("ПРГ");
        $x("//*[@id=\"sendBtn\"]").click();
        $x("/html/body").shouldHave(text("Тестирование ПО"));
    }
    @Test
    public void at2Test01PositiveStandard(){
        Selenide.open("http://92.51.36.108:7777/sl.qa/cinema/index.php");
        $x("/html/body/form/input[1]").sendKeys("30");
        LocalDate movieDay = LocalDate.parse("2025-12-20");
        $x("/html/body/form/input[2]").setValue(SetValueOptions.withDate(movieDay));
        $x("/html/body/form/input[6]").click();
        $x("/html/body/form/input[14]").click();
        btnCalc.click();
        resultText.shouldBe(visible).shouldHave(text("Стоимость билета: 500 рублей."));
    }
    @Test
    public void at2Test02NegativeAge(){
        Selenide.open("http://92.51.36.108:7777/sl.qa/cinema/index.php");
        $x("/html/body/form/input[1]").sendKeys("10");
        LocalDate movieDay = LocalDate.parse("2025-12-24");
        $x("/html/body/form/input[2]").setValue(SetValueOptions.withDate(movieDay));
        $x("/html/body/form/input[3]").click();
        $x("/html/body/form/input[14]").click();
        btnCalc.click();
        resultText.shouldBe(visible).shouldHave(text("*Этот фильм можно смотреть только с 18 лет*"));
    }
    @Test
    public void at2Test03PositiveMorningDiscount(){
        Selenide.open("http://92.51.36.108:7777/sl.qa/cinema/index.php");
        $x("/html/body/form/input[1]").sendKeys("30");
        LocalDate movieDay = LocalDate.parse("2025-12-20");
        $x("/html/body/form/input[2]").setValue(SetValueOptions.withDate(movieDay));
        /* Вопрос: Есть ли возможность поиска по тексту для radio button?*/
        $(By.name("session")).selectRadio("1");
        $(By.name("film")).selectRadio("back");
        btnCalc.click();
        resultText.shouldBe(visible).shouldHave(text("Стоимость билета: 450 рублей. "));
    }

    @Test
    public void at2Test04PositiveWeekdayDiscountAge(){
        Selenide.open("http://92.51.36.108:7777/sl.qa/cinema/index.php");
        $x("/html/body/form/input[1]").sendKeys("7");
        LocalDate movieDay = LocalDate.parse("2025-12-22");
        $x("/html/body/form/input[2]").setValue(SetValueOptions.withDate(movieDay));
        /* Вопрос: Есть ли возможность поиска по тексту для radio button?*/
        $(By.name("session")).selectRadio("1");
        $(By.name("film")).selectRadio("king");
        btnCalc.click();
        resultText.shouldBe(visible).shouldHave(text("Стоимость билета: 350 рублей."));
    }
    @Test
    public void at2Test05AgeOutOfRange(){
        Selenide.open("http://92.51.36.108:7777/sl.qa/cinema/index.php");
        $x("/html/body/form/input[1]").sendKeys("120");
        LocalDate movieDay = LocalDate.parse("2025-12-22");
        $x("/html/body/form/input[2]").setValue(SetValueOptions.withDate(movieDay));
        /* Вопрос: Есть ли возможность поиска по тексту для radio button?*/
        $(By.name("session")).selectRadio("1");
        $(By.name("film")).selectRadio("king");
        btnCalc.click();
        $(By.cssSelector("input[type=number]:nth-child(1)")).getAttribute("value").isEmpty();
    }
}
