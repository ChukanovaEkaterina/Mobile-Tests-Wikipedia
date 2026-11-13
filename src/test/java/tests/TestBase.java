package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import drivers.BrowserstackDriver;
import screens.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class TestBase {

    WelcomeScreen welcomeScreen = new WelcomeScreen();
    MainScreen mainScreen = new MainScreen();
    SearchScreen searchScreen = new SearchScreen();
    ArticleScreen articleScreen = new ArticleScreen();
    SavedArticlesScreen savedArticlesScreen = new SavedArticlesScreen();

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        Configuration.browser = BrowserstackDriver.class.getName();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        if (!hasWebDriverStarted()) {
            return;
        }
        String sessionId = Selenide.sessionId().toString();
        try {
            Attach.screenshot();
            Attach.pageSource();
        } finally {
            closeWebDriver();
        }
        Attach.addVideo(sessionId);
    }

}


