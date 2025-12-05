package work.part01;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleWikiTest {
    @Test
    void test01() {
        Configuration.browser="firefox";
        Configuration.webdriverLogsEnabled=true;
        Configuration.browserBinary="/usr/bin/google-chrome";
        //System.setProperty("webdriver.firefox.logfile", "/tmp/firefox.log");
        //System.setProperty("webdriver.chrome.verboseLogging", "true");
        //System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        open("https://ru.wikipedia.org/wiki/Selenium");
        $("body").shouldHave(text("WebDriver"));
    }
    @Test
    void test02() {
        Configuration.browser="firefox";
        open("https://ru.wikipedia.org/wiki/Selenium");
        $("body").shouldHave(text("Selenium"));
    }
}