package screens;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static com.codeborne.selenide.Selenide.$x;

public class WelcomeScreen {

    @Step("Проходим приветственные экраны и открываем Wikipedia")
    public WelcomeScreen passWelcomeScreens() {
        $(accessibilityId("Forward")).click();
        $(accessibilityId("Forward")).click();
        $(accessibilityId("Forward")).click();
        $x("//*[@text='Skip' or @text='Get started' or @text='Done']").click();
        $(id("org.wikipedia.alpha:id/nav_tab_home")).shouldBe(visible);
        return this;
    }
}
