import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;


public class ConstructorSectionTests extends Driver {

    MainPage mainPage;

    @Before
    public void setUp() {
        webDriver();
        mainPage = new MainPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
    }
    @Test
    @DisplayName("Проверка перехода в раздел 'Соусы'")
    public void checkingTheTransitionToTheSaucesSection(){
        mainPage.clickSauceButton();
        Assert.assertTrue("Соусы не активны", mainPage.isSauceButtonActive());
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Начинки'")
    public void checkingTheTransitionToTheFillingSection(){
        mainPage.clickFillingButton();
        Assert.assertTrue("Начинки не активны", mainPage.isFillingButtonActive());
    }

    @Test
    @DisplayName("Проверка перехода в раздел 'Булки'")
    public void checkingTheTransitionToTheBunSection(){
        mainPage.clickFillingButton();
        mainPage.clickBunButton();
        Assert.assertTrue("Булки не активны", mainPage.isBunButtonActive());
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
