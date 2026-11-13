package screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class SearchScreen {

    private static final SelenideElement closeWidgetButton = $(accessibilityId("Close"));
    private static final SelenideElement searchField = $(accessibilityId("Search Wikipedia"));
    private static final SelenideElement searchInput = $(id("org.wikipedia.alpha:id/search_src_text"));
    private static final SelenideElement clearQueryButton = $(id("org.wikipedia.alpha:id/search_close_btn"));
    private static final ElementsCollection articlesList = $(id("org.wikipedia.alpha:id/fragment_search_results"))
            .$$x(".//android.view.View[@clickable='true']/android.widget.TextView[1]");

    @Step("Закрываем всплывающие экраны")
    public SearchScreen closeWidget() {
        try {
            new WebDriverWait(getWebDriver(), Duration.ofSeconds(3))
                    .until(driver -> closeWidgetButton.isDisplayed());
        } catch (TimeoutException e) {
            return this;
        }
        closeWidgetButton.click();
        closeWidgetButton.shouldNotBe(visible);
        return this;
    }

    @Step("Клик по строке запроса")
    public SearchScreen clickOnSearchField() {
        if (!searchInput.isDisplayed()) {
            searchField.click();
        }
        return this;
    }

    @Step("Вводим запрос на поиск статьи и нажимаем Enter")
    public SearchScreen setSearchValue(String value) {
        searchInput.shouldBe(visible).getWrappedElement().clear();
        searchInput.sendKeys(value);
        return this;
    }

    @Step("Открывем статью")
    public SearchScreen openArticle(String value) {
        articlesList.findBy(exactText(value)).shouldBe(visible).click();
        return this;
    }

    @Step("Проверяем что список найденных статей не пустой")
    public SearchScreen checkArticlesListIsNotEmpty() {
        articlesList.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Проверяем сообщение об отсутствии результатов")
    public SearchScreen checkNoResults() {
        $x("//*[@text='No results']").shouldBe(visible);
        articlesList.shouldHave(size(0));
        return this;
    }

    @Step("Очищаем поисковый запрос кнопкой крестика")
    public SearchScreen clearSearch() {
        clearQueryButton.click();
        return this;
    }

    @Step("Проверяем, что запрос и результаты поиска очищены")
    public SearchScreen checkSearchCleared() {
        searchInput.shouldBe(visible).shouldHave(exactText("Search Wikipedia"));
        clearQueryButton.shouldNotBe(visible);
        articlesList.shouldHave(size(0));
        return this;
    }

}
