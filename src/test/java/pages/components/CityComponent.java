package pages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

public class CityComponent {

    public void setCity(SelenideElement el, String city) {
        clearInputField(el);
        el.setValue(city).pressEnter();
    }

    private void clearInputField(SelenideElement el) {
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.chord(Keys.DELETE));
    }
}
