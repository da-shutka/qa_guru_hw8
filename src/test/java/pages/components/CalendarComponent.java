package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    SelenideElement currentDate = $(".CalendarMonth[data-visible='true'] .CalendarDay__today");

    public void setCurrentDate() {
        currentDate.click();
    }
}
