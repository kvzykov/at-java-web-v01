package work.part07.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class SearchFlights {
    SelenideElement
            departureCity = $x("//*[@id=\"avia_form_origin-input\"]"),
            arrivalCity = $x("//*[@id=\"avia_form_destination-input\"]"),
            departureDateField = $x("//button[contains(@data-test-id,'start-date-field')]"),
            returnDateField = $x("//button[contains(@data-test-id,'end-date-field')]"),
            findButton = $x("//button[contains(@data-test-id,'form-submit')]");

    // Формирование даты для поиска в календаре
    public String makeDateButton (String textDate) {
        // Формат передаваемой даты "yyyy-MM-dd"
        LocalDate flightDate = LocalDate.parse(textDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy 'г.'");
        return flightDate.format(formatter);
    }
    // Формирование кнопки даты отправления и даты назначения
    public SelenideElement formDate (String date) {
        SelenideElement departureDateButton = $x(String.format("//button[@aria-label='%s']", makeDateButton(date)));
        return departureDateButton;
    }
    // Проверка сообщений о незаполненных необходимых полях
    @Step("Поиск рейсов не заданы город прибытия и/или дата отправления)")
    public void search() {
        this.findButton.click();
        sleep(3_000);
        //Поиск сообщения о незаданной дате
        $x("//div[contains(text(),'Укажите город прибытия')]").shouldBe(visible);
        $x("//div[contains(text(),'Укажите дату')]").shouldBe(visible);
    }


    @Step("Поиск рейсов заданы город отправления и город назначения)")
    public void search(String departureCity, String arrivalCity) {
        this.departureCity.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        this.departureCity.shouldNotBe(Condition.readonly, Duration.ofSeconds(30));

        this.departureCity.setValue("");
        this.departureCity.type(departureCity).sendKeys(Keys.DOWN);
        sleep(2_000);
        this.arrivalCity.click();
        this.arrivalCity.type(arrivalCity).sendKeys(Keys.DOWN);
        sleep(2_000);
        this.departureDateField.click();
        sleep(2_000);
        this.findButton.click();;
        $x("//div[contains(text(),'Укажите дату')]").shouldBe(visible);
    }

    @Step("Поиск рейсов заданы город отправления, город назначения, дата отправления)")
    public void search(String departureCity, String arrivalCity, String departureDate) {
        this.departureCity.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        this.departureCity.shouldNotBe(Condition.readonly, Duration.ofSeconds(30));

        this.departureCity.setValue("");
        this.departureCity.type(departureCity).sendKeys(Keys.DOWN);
        sleep(2_000);
        this.arrivalCity.click();
        this.arrivalCity.type(arrivalCity).sendKeys(Keys.DOWN);
        sleep(2_000);

        this.departureDateField.click();
        sleep(2000);
        this.formDate(departureDate).click();
        System.out.println(title());
        String winTitle = title();
        this.findButton.click();

        $x("//a[contains(@data-testid,'header-logo-link')]").shouldBe(visible, Duration.ofSeconds(10));
        System.out.println(title());
//        closeWindow();
        switchTo().window(1);
        System.out.println(title());
        $x("//button[contains(.,'Показать ещё билеты')]").shouldBe(visible, Duration.ofSeconds(20));
        sleep(10_000);
    }

    @Step("Поиск рейсов заданы город отправления, город назначения, дата отправления, дата возвращения, багаж, без пересадок)")
    public void search(String departureCity, String arrivalCity, String departureDate, String returnDate, Integer baggageWeight, String transferStatus) {
        this.departureCity.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        this.departureCity.shouldNotBe(Condition.readonly, Duration.ofSeconds(30));

        // Выбор городов отправления и назначения
        this.departureCity.setValue("");
        this.departureCity.type(departureCity).sendKeys(Keys.DOWN);
        sleep(2_000);
        this.arrivalCity.click();
        this.arrivalCity.type(arrivalCity).sendKeys(Keys.DOWN);
        sleep(2_000);

        // Выбор даты отправления
        // sleep нужен для имитации интерактивности мясного покупателя билетов, к сожалению с циклом работает нестабильно
        this.departureDateField.click();
        sleep(2000);
        this.formDate(departureDate).click();
        // Вывод названия окна для понимания, на каком окне сейчас стоим
        // В процессе всплывает рекламный сайт "островок"
        System.out.println(title());
        this.returnDateField.click();
        sleep(2000);
        this.formDate(returnDate).click();
        // Запуск поиска
        this.findButton.click();

        // Ожидаем загрузки сайта "островок", чтобы переключиться на основное окно
        $x("//a[contains(@data-testid,'header-logo-link')]").shouldBe(visible, Duration.ofSeconds(10));
        System.out.println(title());
//        closeWindow();
        // Возврат на основное рабочее окно
        switchTo().window(1);
        System.out.println(title());
        // Ожидание загрузки всех доступных рейсов
        // Триггером выбрана кнопка "Показать ещё билеты". Кнопка появляется внизу страницы после полной загрузки
        $x("//button[contains(.,'Показать ещё билеты')]").shouldBe(visible, Duration.ofSeconds(20));
        switch (baggageWeight){
            case 10:
                $x("//div[@data-test-id='filter-group-transfers_side_group']/div[2]/div[1]/div[1]//div[1]/div[1]/div[1]").click();
                break;
            case 20:
                System.out.println(text("Действие не задано, условие 20"));
                break;
            default:
                System.out.println(text("Действие не задано, условие default"));
                break;
        }

        switch (transferStatus){
            case "Прямые рейсы":
                $x("//div[@data-test-id='filter-group-only_baggage_side_group']/div[2]/div[1]/div[1]/div[1]/div[1]").click();
                break;
            default:
                System.out.println(text("Действие не задано, условие default"));
                break;
        }


        // Ожидание для просмотра содержимого страницы
        sleep(10_000);
    }
}