package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage  extends BasePage {

    //Локатор кнопки "Войти в аккаунт"
    private By loginButtonLocator = By.xpath("//button[text()='Войти в аккаунт']");
    //Локатор кнопки "Личный кабинет"
    private By personalButtonLocator = By.linkText("Личный Кабинет");
    //Локатор кнопки раздела "Булки" - активная
    private By activeBunButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    //Локатор кнопки раздела "Булки" - неактивная
    private By inactiveBunButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");
    //Локатор кнопки раздела "Соусы" - активная
    private By activeSauceButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    //Локатор кнопки раздела "Соусы" - неактивная
    private By inactiveSauceButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");
    //Локатор кнопки раздела "Начинки" - активная
    private By activeFillingButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG " +
            "tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");
    //Локатор кнопки раздела "Начинки" - неактивная
    private By inactiveFillingButtonLocator = By.xpath("//div[@class='tab_tab__1SPyG  " +
            "pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");
    //Локатор надписи "Соберите бургер"
    private By makeBurgerLabelLocator = By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10']");
    //Локатор кнопки "Оформить заказ"
    private By orderButtonLocator = By.xpath("//button[text()='Оформить заказ']");
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной странице")
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForLoad() {
        waitForVisibility(makeBurgerLabelLocator);
    }

    @Step("Проверка видимости кнопки 'Оформить заказ'")
    public boolean isOrderButtonVisible() {
        return driver.findElement(orderButtonLocator).isDisplayed();
    }

    @Step("Клик на кнопку 'Личный кабинет'")
    public void clickPersonalButton() {
        driver.findElement(personalButtonLocator).click();
    }

    @Step("Клик на кнопку 'Соусы'")
    public void clickSauceButton() {
        driver.findElement(inactiveSauceButtonLocator).click();
    }

    @Step("Проверка активности кнопки 'Соусы'")
    public boolean isSauceButtonActive() {
        return driver.findElement(activeSauceButtonLocator).isDisplayed();
    }

    @Step("Клик на кнопку 'Начинки'")
    public void clickFillingButton() {
        driver.findElement(inactiveFillingButtonLocator).click();
    }

    @Step("Проверка активности кнопки 'Начинки'")
    public boolean isFillingButtonActive() {
        return driver.findElement(activeFillingButtonLocator).isDisplayed();
    }

    @Step("Клик на кнопку 'Булки'")
    public void clickBunButton() {
        driver.findElement(inactiveBunButtonLocator).click();
    }

    @Step("Проверка активности кнопки 'Булки'")
    public boolean isBunButtonActive() {
        return driver.findElement(activeBunButtonLocator).isDisplayed();
    }
}


