package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Story("Функционал поиска статей")
public class SearchTests extends TestBase{

    public static String searchedArticleName = "Sberbank";

    @Test
    @DisplayName("Для несуществующего запроса показано сообщение об отсутствии результатов")
    void noSearchResultsTest() {
        welcomeScreen.passWelcomeScreens();
        mainScreen.clickSearchTab();
        searchScreen.closeWidget()
                .clickOnSearchField()
                .setSearchValue("Qzxvnotarealwikipediaarticle987654321")
                .checkNoResults();
    }

    @Test
    @DisplayName("Кнопка очистки удаляет запрос и найденные результаты")
    void clearSearchQueryTest() {
        welcomeScreen.passWelcomeScreens();
        mainScreen.clickSearchTab();
        searchScreen.closeWidget()
                .clickOnSearchField()
                .setSearchValue(searchedArticleName)
                .checkArticlesListIsNotEmpty()
                .clearSearch()
                .checkSearchCleared();
    }

    @Test
    @DisplayName("Успешный поиск статьи")
    void successfulSearchArticleTest() {
        welcomeScreen.passWelcomeScreens();
        step("Открываем \"Поиск\" и вводим запрос", () -> {
            mainScreen.clickSearchTab();
            searchScreen.closeWidget();
            searchScreen.clickOnSearchField();
            searchScreen.setSearchValue(searchedArticleName);
        });
        searchScreen.checkArticlesListIsNotEmpty();
    }

    @Test
    @DisplayName("Открытие найденной статьи и проверка её содержимого")
    void successfulViewArticleTest() {
        welcomeScreen.passWelcomeScreens();
        step("Открываем \"Поиск\" и вводим запрос", () -> {
            mainScreen.clickSearchTab();
            searchScreen.closeWidget();
            searchScreen.clickOnSearchField();
            searchScreen.setSearchValue(searchedArticleName);
        });

        searchScreen.openArticle(searchedArticleName);
        articleScreen.checkRightArticleOpen(searchedArticleName);
    }

}
