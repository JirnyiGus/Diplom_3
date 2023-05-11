package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage extends BasePage{

    //Локатор надписи Восстановление пароля
    private By resetPasswordLabelLocator = By.xpath("//h2[text()='Восстановление пароля']");
    //Локатор кнопки Войти
    private By loginButtonLocator = By.xpath("//a[@href='/login']");
    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForLoadPage() {
        waitForVisibility(resetPasswordLabelLocator);
    }
}
