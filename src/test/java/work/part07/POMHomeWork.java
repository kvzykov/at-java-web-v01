package work.part07;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import work.part07.pages.SearchFlights;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMHomeWork {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {
        open("https://www.aviasales.ru");
        getWebDriver().manage().window().maximize();
        sleep(2_000);
    }
    // 1. Поиск рейсов Красноярск - Сочи
    @Test
    void test01FlightsFound() {
        SearchFlights searchFlights = new SearchFlights();
        searchFlights.search("красноярск","сочи");
    }
    // 2. Проверка поиска без ввода города назначения и даты отправления
    @Test
    void test02FlightsNoDate() {
        SearchFlights searchFlights = new SearchFlights();
        searchFlights.search();
    }
    // 3. Ищем вылеты с указанием даты отправления, городов прибытия и отправления.
    @Test
    void test03FlightSearchWithDepartureDate() {
        SearchFlights searchFlights = new SearchFlights();
        searchFlights.search("красноярск", "сочи", "2025-12-27");

    }
    // 4. Поиск рейса с указанием дополнительных параметров:
    // Багаж: 10,20,30
    // Пересадки
    @Test
    void test04FlightSearchWithAdditionalParameters() {
        SearchFlights searchFlights = new SearchFlights();
        searchFlights.search("санкт-петербург", "владивосток", "2025-12-27", "2026-01-13", 10, "Прямые рейсы");

    }
}