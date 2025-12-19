package work.part03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class dataFromFile {

    @ParameterizedTest (name = "01. Попытка входа в систему по кнопке Войти под разными логинами, #{index}, username: {0}")
    @CsvFileSource(resources = "com_test.csv", numLinesToSkip=1)
    void test02Positive(String user, String pass, String fio) {
        open("https://slqamsk.github.io/cases/slflights/v01/");
        $x("//*[@id=\"username\"]").setValue(user);
        $x("//*[@id=\"password\"]").setValue(pass);
        $x("//*[@id=\"loginButton\"]").click();
        $x("//*[@id=\"greeting\"]").shouldHave(exactText("Добро пожаловать, "+fio+"!"));
        $x("//*[@id=\"logoutButton\"]").shouldBe(visible).click();

        sleep(1000);
//        $x("//input[@type='submit']").click();
//        $x("//span[@name='com']").shouldHave(text(com));

    }

}
