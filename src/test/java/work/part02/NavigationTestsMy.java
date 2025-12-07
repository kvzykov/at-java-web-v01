package demo.part02;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
public class NavigationTestsMy {
    @Test
    void test_navigation() {
        Configuration.browser="firefox";
        Configuration.webdriverLogsEnabled=true;
        open("https://www.ozon.ru/");
        sleep(3_000);
        open("https://www.gismeteo.ru");
        sleep(3_000);
        open("https://www.selenium.dev");
        sleep(3_000);
        back();
        sleep(3_000);
        back();
        sleep(3_000);
        forward();
        sleep(10_000);
    }
}
