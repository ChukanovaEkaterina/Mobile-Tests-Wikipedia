package screens;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.id;

public class SavedArticlesScreen {

    @Step("Открываем список чтения {name}")
    public SavedArticlesScreen openList(String name) {
        $$(className("android.widget.TextView")).findBy(exactText(name)).click();
        $(id("org.wikipedia.alpha:id/buttonView"))
                .shouldHave(exactText("Got it")).click();
        return this;
    }

    @Step("Проверяем наличие статьи {title} в сохранённом списке")
    public SavedArticlesScreen checkArticleSaved(String title) {
        $$(className("android.widget.TextView"))
                .findBy(exactText(title)).shouldBe(visible);
        return this;
    }
}
