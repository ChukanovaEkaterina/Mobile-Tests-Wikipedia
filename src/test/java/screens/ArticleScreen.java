package screens;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.id;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static com.codeborne.selenide.Selenide.*;

public class ArticleScreen {

    @Step("Сохраняем статью в новый список {listName}")
    public ArticleScreen saveArticle(String listName) {
        $(id("org.wikipedia.alpha:id/page_save")).click();
        $x("//*[@text='Create a new reading list']").click();
        $(id("org.wikipedia.alpha:id/text_input")).sendKeys(listName);
        $(id("android:id/button1")).click();
        return this;
    }

    @Step("Возвращаемся из статьи к основному меню")
    public ArticleScreen backToMainScreen() {
        $(accessibilityId("Navigate up")).click();
        if ($(id("org.wikipedia.alpha:id/search_src_text")).isDisplayed()) {
            $(accessibilityId("Navigate up")).click();
        }
        $(id("org.wikipedia.alpha:id/nav_tab_home")).shouldBe(visible);
        return this;
    }

    @Step("Проверяем что статья содержит текст {title}")
    public ArticleScreen checkRightArticleOpen(String title) {
        $(className("android.webkit.WebView")).shouldBe(visible);
        $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldNotBe(visible);
        $$(className("android.widget.TextView"))
                .findBy(exactText(title)).shouldBe(visible);
        return this;
    }

}
