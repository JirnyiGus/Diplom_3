package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class LoginPage extends BasePage{

    //Локатор поля "Вход"
    private By inputFieldLocator = By.xpath("//h2[text()='Вход']");
    //Локатор поля "Email"
    private By emailFieldLocator = By.xpath("//input[@name='name']");
    //Локатор поля Пароль
    private By passwordFieldLocator = By.xpath("//input[@name='Пароль']");
    //Локатор кнопки "Войти"
    private By loginButtonLocator = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx " +
            "button_button_size_medium__3zxIa']");
    //Локатор кнопки "Зарегистрироваться"
    private By registerButtonLocator = By.xpath("//a[@href='/register']");
    //Локатор кнопки "Восстановить пароль"
    private By resetPasswordButtonLocator = By.xpath("//a[@href='/forgot-password']");
    //Локатор поля "Логотип"
    private By logoFieldLocator = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки страницы авторизации")
    public void waitForLoad() {
        waitForVisibility(inputFieldLocator);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButtonLocator).click();
    }

    @Step("Заполнение формы авторизации")
    public void fillLoginForm(String email, String password) {
        driver.findElement(emailFieldLocator).sendKeys(email);
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    @Step("Клик на поле 'Логотип'")
    public void clickLogoButton() {
        driver.findElement(logoFieldLocator).click();
    }

    @Step("Клик на кнопку 'Восстановить пароль'")
    public void clickResetPasswordButton() {
        driver.findElement(resetPasswordButtonLocator).click();
    }

    @Step("Проверка видимости надписи 'Войти'")
    public boolean isEnterLabelVisible() {
        return driver.findElement(inputFieldLocator).isDisplayed();
    }
}
