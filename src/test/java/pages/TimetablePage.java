package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.CityComponent;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TimetablePage extends BasePage {

    CityComponent cityComponent = new CityComponent();
    CalendarComponent calendarComponent = new CalendarComponent();

    private final SelenideElement
            cityFrom = $("[data-test='timetable_from-suggest'] input"),
            cityTo = $("[data-test='timetable_to-suggest'] input"),
            dateFrom = $("input[name='start date']"),
            submitButton = $(".timetable__submit-btn"),
            suggestedList = $(".suggest__item"),
            languageButton = $(".DS__LanguageSelect__button");
    private final ElementsCollection
            flights = $$(".timetable-flight__flight"),
            languagesList = $$(".DS__MenuLinks__item"),
            headersList = $$("[data-test*='page-header']");

    public TimetablePage openPage() {
        open("/ru/timetable");
        $("h1").shouldHave(text("Расписание полетов S7 Airlines"));
        this.cancelSlide();
        return this;
    }

    public TimetablePage setCityFrom(String city) {
        cityComponent.setCity(cityFrom, city);
        return this;
    }

    public TimetablePage setCityTo(String city) {
        cityComponent.setCity(cityTo, city);
        return this;
    }

    public TimetablePage setDateFrom() {
        dateFrom.click();
        calendarComponent.setCurrentDate();
        return this;
    }

    public TimetablePage submit() {
        submitButton.click();
        return this;
    }

    public void checkResultFlightsExistence() {
        flights.shouldHave(sizeGreaterThan(0));
    }

    public void checkCityFrom(String city) {
        suggestedList.shouldNotBe(visible);
    }

    public TimetablePage selectLanguage(String language) {
        languageButton.click();
        languagesList.find(text(language)).click();
        return this;
    }

    public void checkPageHeaders(List<String> headers) {
        headersList.shouldHave(texts(headers));
    }
}
