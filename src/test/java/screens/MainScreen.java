package screens;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MainScreen {

    private static final SelenideElement mainButton = $(io.appium.java_client.AppiumBy.id("org.wikipedia.alpha:id/nav_tab_home"));
    private static final SelenideElement searchTab = $(io.appium.java_client.AppiumBy.id("org.wikipedia.alpha:id/nav_tab_search"));

    @Step("Нажимаем на вкладку \"Поиск\"")
    public MainScreen clickSearchTab() {
        searchTab.click();
        return this;
    }

    @Step("Проверям, что открыт главный экран")
    public MainScreen checkMainScreenOpen() {
        mainButton.shouldBe(Condition.visible);
        return this;
    }


}
