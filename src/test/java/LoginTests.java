import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PasswordRecoveryPage;
import pageobject.RegistrationPage;
import pens.UserAPI;
import serialization.User;

public class LoginTests extends Driver {

     MainPage mainPage;
     LoginPage loginPage;
     RegistrationPage registrationPage;
     User user;
     PasswordRecoveryPage passwordRecoveryPage;
     UserAPI userAPI;
    String name;
    String email;
    String password;

    @Before
    public void setUp() {
        webDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        //создаем нового пользователя
        name = "Gleb";
        email = "gkaverin98@mail.ru";
        password = "123456";
        user = new User(email, password, name);
        userAPI.createUser(user);
    }
    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void checkLoginOnMainPage() {
        //клик на кнопку войти
        mainPage.clickLoginButton();
        //заполняем форму логина
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //Проверяем что появилась кнопка оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void checkLoginByPersonalAccountButton() {
        //клик на кнопку личный кабинет
        mainPage.clickPersonalButton();
        //заполняем форму логина
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //Проверяем что появилась кнопка оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void checkLoginOnRegistrationPage() {
        mainPage.clickLoginButton();
        //ждем загрузки страницы логина
        loginPage.waitForLoad();
        //кликаем на Зарегистрироваться
        loginPage.clickRegisterButton();
        //ждем загрузки страницы регистрации
        registrationPage.waitForLoadRegisterPage();
        //клик на кнопку Войти
        registrationPage.clickLoginButton();
        //заполняем форму логина
        loginPage.waitForLoad();
        loginPage. fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //Проверяем что появилась кнопка оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void checkLoginOnPasswordRecoveryPage() {
        mainPage.clickLoginButton();
        loginPage.waitForLoad();
        loginPage.clickResetPasswordButton();
        //Кликаем на Войти
        passwordRecoveryPage.waitForLoadPage();
        passwordRecoveryPage.clickLoginButton();
        //ждем загрузки страницы регистрации
        loginPage.waitForLoad();
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        //Проверяем что появилась кнопка оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Кнопка оформить заказ не появилась", mainPage.isOrderButtonVisible());
    }
    @After
    public void tearDown() {
        String accessToken = UserAPI.loginUser(new User(email,password)).then().extract().path("accessToken");
        if (accessToken != null) {
            UserAPI.deleteUser(accessToken);
        }
        driver.quit();
    }
}
