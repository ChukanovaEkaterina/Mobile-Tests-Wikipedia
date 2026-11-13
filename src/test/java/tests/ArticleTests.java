package tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Story("Сохранение статей")
public class ArticleTests extends TestBase {

    @Test
    @DisplayName("Сохранённая статья появляется в созданном списке чтения")
    void saveArticleTest() {
        String title = "Sberbank";
        String listName = "Mobile tests";
        welcomeScreen.passWelcomeScreens();
        mainScreen.clickSearchTab();
        searchScreen.closeWidget()
                .clickOnSearchField()
                .setSearchValue(title)
                .openArticle(title);
        articleScreen.checkRightArticleOpen(title)
                .saveArticle(listName)
                .backToMainScreen();
        mainScreen.clickSavedTab();
        savedArticlesScreen.openList(listName).checkArticleSaved(title);
    }
}
