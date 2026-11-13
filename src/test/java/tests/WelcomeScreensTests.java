package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Story("Запуск приложения")
public class WelcomeScreensTests extends TestBase {

    @Test
    @DisplayName("При запуске Wikipedia открывается главный экран")
    void mainScreenIsOpenOnLaunchTest() {
        welcomeScreen.passWelcomeScreens();
        mainScreen.checkMainScreenOpen();
    }
}

