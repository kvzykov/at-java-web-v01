package demo.part04;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class IFramesTestMy {
    @Test
    void test01IFrame() {
        //Configuration.pageLoadTimeout = 120_000;
        Configuration.browser="firefox";
        Configuration.pageLoadStrategy = "eager";

        open("https://demoqa.com/frames");
        getWebDriver().manage().window().maximize();

//        $x("//*[@class='wp-block-spacer'][2]").scrollTo(); //для демонстрации
//        sleep(5_000);
        switchTo().frame($x("//*[@id=\"frame1\"]"));
//        $x("//a[contains(.,'About')]").click();
//        sleep(5_000);
        $x("//h1[.='This is a sample page']");
        sleep(5_000);

        /*switchTo().defaultContent();
        $x("//body").scrollTo(); //для демонстрации
        sleep(5_000);
        $x("//a[text()='Home']").click();
        sleep(10_000);*/
    }
}