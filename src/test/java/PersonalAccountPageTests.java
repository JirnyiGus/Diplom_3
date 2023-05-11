import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PersonalAccountPage;
import pageobject.RegistrationPage;
import pens.UserAPI;
import serialization.User;

public class PersonalAccountPageTests extends Driver {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    User user;
    PersonalAccountPage personalAccountPage;
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
        personalAccountPage = new PersonalAccountPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
        //создаем нового пользователя
        name = "Gleb";
        email = "gkaverin98@mail.ru";
        password = "123456";
        user = new User(email, password, name);
        userAPI.createUser(user);

        //кликаем "Войти в аккаунт"
        mainPage.clickLoginButton();
        //ждем загрузки страницы логина
        loginPage.waitForLoad();
        //заполняем форму логина
        loginPage.fillLoginForm(email, password);
        loginPage.clickLoginButton();
        mainPage.waitForLoad();
    }
    @Test
    @DisplayName("Переход по клику на 'Личный кабинет'")
    public void clickToGoToMyAccount() {
        //клик на личный кабинет
        mainPage.clickPersonalButton();
        //проверяем видимость кнопки профиль
        personalAccountPage.waitForLoadMainPage();
        Assert.assertTrue("Вход в личный кабинет не выполнен", personalAccountPage.isProfileButtonVisible());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на 'Конструктор'")
    public void checkEnterConstructorByConstructorButton() {
        //клик на личный кабинет
        mainPage.clickPersonalButton();
        personalAccountPage.waitForLoadMainPage();
        //кликаем на Конструктор
        personalAccountPage.clickConstructorButton();
        //Проверяем видимость кнопки оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Переход в конструктор не произошел", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип 'Stellar Burgers'")
    public void checkEnterConstructorByLogo() {
        //клик на личный кабинет
        mainPage.clickPersonalButton();
        personalAccountPage.waitForLoadMainPage();
        //кликаем на лого
        personalAccountPage.clickLogo();
        //Проверяем видимость кнопки оформить заказ
        mainPage.waitForLoad();
        Assert.assertTrue("Переход в конструктор не произошел", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Проверка выхода по кнопке 'Выйти'")
    public void checkingTheExitOnTheExitButton() {
        //клик на личный кабинет
        mainPage.clickPersonalButton();
        personalAccountPage.waitForLoadMainPage();
        //клик на Выйти
        personalAccountPage.clickLogoutButton();
        //проверяем что перешли на страницу Логина
        loginPage.waitForLoad();
        Assert.assertTrue("Выход не произошел", loginPage.isEnterLabelVisible());
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
