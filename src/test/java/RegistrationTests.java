import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import pens.UserAPI;
import serialization.User;


public class RegistrationTests extends Driver {


    String name;
    String email;
    String password;

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @Before
    public void setUp() {
        webDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        //кликаем "Войти в аккаунт"
        mainPage.clickLoginButton();
        //ждем загрузки страницы логина
        loginPage.waitForLoad();
        //кликаем на Зарегистрироваться
        loginPage.clickRegisterButton();
        //ждем загрузки страницы регистрации
        registrationPage.waitForLoadRegisterPage();
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkingSuccessfulRegistration() {
        //заполняем форму регистрации
        name = "Gleb";
        email = "gkaverin98@mail.ru";
        password = "123456";
        registrationPage.fillRegistrationForm(name, email, password);
        //клик на кнопку Зарегистрироваться
        registrationPage.clickRegisterButton();
        //ждем загрузки страницы логина
        loginPage.waitForLoad();
        //заполняем форму логина
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //ждем загрузки главной страницы
        mainPage.waitForLoad();
        //проверяем что появилась кнопка заказа
        Assert.assertTrue("Регистрация не произошла", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка входа с некорректным паролем")
    public void checkingForBadPasswordLogin() {
        //заполняем форму регистрации
        name = "Gleb";
        email = "gkaverin98@mail.ru";
        password = "12345";
        registrationPage.fillRegistrationForm(name, email, password);
        //клик на кнопку Зарегистрироваться
        registrationPage.clickRegisterButton();
        //проверяем что появилась надпись о некорректном пароле
        Assert.assertTrue("Ошибка о некорректном пароле не появилась",
                registrationPage.isIncorrectPasswordLabelVisible());
    }

    @After
    public void tearDown() {
        String accessToken = UserAPI.loginUser(new User(email, password)).then().extract().path("accessToken");
        if (accessToken != null) {
            UserAPI.deleteUser(accessToken);
        }
        driver.quit();
    }


}
