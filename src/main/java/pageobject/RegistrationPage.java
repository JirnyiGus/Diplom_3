package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage  extends BasePage {

    //Локатор надписи "Регистрация"
    private By registerLabelLocator = By.xpath("//h2[text()='Регистрация']");
    //Локатор поля "Имя"
    private By nameFieldLocator = By.xpath("//label[text()='Имя']/../input");
    //Локатор поля "Email"
    private By emailFieldLocator = By.xpath("//label[text()='Email']/../input");
    //Локатор поля "Пароль"
    private By passwordFieldLocator = By.xpath("//label[text()='Пароль']/../input");
    //Локатор кнопки "Зарегистрироваться"
    private By registerButtonLocator = By.xpath("//button[@class='button_button__33qZ0 " +
            "button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    //Локатор кнопки "Войти"
    private By loginButtonLocator = By.xpath("//a[@href='/login']");
    //Локатор надписи "Некорректный пароль"
    private By incorrectPasswordLabelLocator = By.xpath("//p[@class='input__error text_type_main-default']");
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ожидание загрузки страницы Регистрации")
    public void waitForLoadRegisterPage() {
        waitForVisibility(registerLabelLocator);
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrationForm(String name, String email, String password) {
        driver.findElement(nameFieldLocator).sendKeys(name);
        driver.findElement(emailFieldLocator).sendKeys(email);
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButtonLocator).click();
    }

    @Step("Проверка видимости надписи 'Неправильный пароль'")
    public boolean isIncorrectPasswordLabelVisible() {
        return driver.findElement(incorrectPasswordLabelLocator).isDisplayed();
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }
}


