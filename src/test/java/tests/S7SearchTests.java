package tests;

import data.Language;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.TimetablePage;

import java.util.List;
import java.util.stream.Stream;

@DisplayName("Тесты на проверку страницы расписания полетов S7")
public class S7SearchTests extends TestBase {

    TimetablePage timetablePage = new TimetablePage();

    @Tags({
            @Tag("SMOKE"),
            @Tag("REGRESS"),
            @Tag("UI")
    })
    @ParameterizedTest(name = "Поиск рейсов из города {0} в город {1} на текущю дату должен возвращать карточки")
    @CsvSource(value = {
            "Москва, Санкт-Петербург",
            "Волгоград, Москва"
    })
    public void searchFlightsShouldReturnNotEmptyResult(String cityFrom, String cityTo) {
        timetablePage
                .openPage()
                .setCityFrom(cityFrom)
                .setCityTo(cityTo)
                .setDateFrom()
                .submit()
                .checkResultFlightsExistence();
    }

    @Tag("TEMP")
    @ParameterizedTest(name = "Проверка отсутствия временно закрытого аэропорта {0} в списке Откуда")
    @ValueSource(strings = {
            "Анапа", "Белгород", "Брянск", "Воронеж", "Геленджик",
            "Краснодар", "Курск", "Липецк", "Ростов-на-Дону", "Симферополь"
    })
    public void temporarilyClosedAirportsAreNotInTheFromList(String city) {
        timetablePage
                .openPage()
                .setCityFrom(city)
                .checkCityFrom(city);
    }

    static Stream<Arguments> headersDependOnSelectedLanguage() {
        return Stream.of(
                Arguments.of(
                        Language.RU,
                        List.of("Расписание полетов S7 Airlines", "Список доступных маршрутов")
                ),
                Arguments.of(
                        Language.EN,
                        List.of("S7 Airlines flight schedule", "List of available S7 Airlines routes and flights details")
                ),
                Arguments.of(
                        Language.CN,
                        List.of("飞行时刻表", "S7 Airlines 路线列表及航班详情")
                ),
                Arguments.of(
                        Language.DE,
                        List.of("S7 Airlines Flugplan", "Liste der S7 Airlines Routen zur Verfügung und Details der Flüge")
                ),
                Arguments.of(
                        Language.ES,
                        List.of("Horarios de vuelos S7 Airlines", "Lista de rutas de S7 Airlines y detalles de vuelos")
                ),
                Arguments.of(
                        Language.IT,
                        List.of("Programma di volo S7 Airlines", "Lista dei percorsi S7 Airlines disponibili ei dettagli dei voli")
                )

        );
    }

    @Tag("SMOKE")
    @MethodSource
    @ParameterizedTest(name = "Проверка заголовков в баннере для языка {0}")
    public void headersDependOnSelectedLanguage(Language language, List<String> expectedHeaders) {
        timetablePage
                .openPage()
                .selectLanguage(language.description)
                .checkPageHeaders(expectedHeaders);
    }
}
