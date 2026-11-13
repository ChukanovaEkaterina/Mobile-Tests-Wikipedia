package screens;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.id;
import static com.codeborne.selenide.Selenide.*;

public class ArticleScreen {

    @Step("Проверяем что статья содержит текст {title}")
    public ArticleScreen checkRightArticleOpen(String title) {
        $(className("android.webkit.WebView")).shouldBe(visible);
        $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldNotBe(visible);
        $$(className("android.widget.TextView"))
                .findBy(exactText(title)).shouldBe(visible);
        return this;
    }

}
