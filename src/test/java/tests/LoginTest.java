package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.DriverFactory;
import praktikum.Users.UserClient;
import praktikum.Users.Users;
import praktikum.pages.*;
import static praktikum.EnvConfig.*;

/**
 * Регистрация
 * Проверь:
 * Успешную регистрацию.
 * Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
 */

@RunWith(Parameterized.class)
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private RegistrationPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private Users user;
    private UserClient userClient;
    String browser;

    @Parameterized.Parameters(name="Browser {0}")
    public static Object[][] initParams() {
        return new Object[][] {
                {"chrome"},
                {"yandex"}
        };
    }
    public LoginTest(String browser) {
        this.browser = browser;
    }

    @Before
    @Step("Запуск браузера, подготовка данных для теста")
    public void setUp() {
        driver = DriverFactory.createDriver(browser);
        driver.get(URL_USER);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        userClient = new UserClient();
        user = Users.random();
        userClient.register(user);

    }

    @Step("Закрытие браузера, удаление данных для теста")
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Авторизация по кнопке «Войти в аккаунт»")
    public void checkLoginMainPage() {
        Allure.parameter("Проверка в ", browser);
        mainPage.waitPageLoad();
        mainPage.clickAuthButton();
        loginPage.waitForm();
        loginPage.login(user);
        mainPage.waitPageLoad();
        Assert.assertTrue("Authorization failed", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Авторизация по кнопке «Личный кабинет» на главной")
    @Description("Тест проверяет что пользователь может аворизоваться через личный кабинет")
    public void checkLoginAccount() {
        Allure.parameter("Проверка в ", browser);
        mainPage.waitPageLoad();
        mainPage.clickPersonalAccount();
        loginPage.waitForm();
        loginPage.login(user);
        mainPage.waitPageLoad();
        Assert.assertTrue("Authorization failed", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Авторизация по кнопке «Войти» на форме регистрации")
    @Description("Тест проверяет что пользователь может аворизоваться при переходе из формы решистрации")
    public void checkLoginRegistration() {
        Allure.parameter("Проверка в ", browser);
        registerPage = new RegistrationPage(driver);
        driver.get(REGISTER_URL);
        registerPage.waitlRegistrationPage();
        registerPage.clickRegistration();
        loginPage.waitForm();
        loginPage.login(user);
        mainPage.waitPageLoad();
        Assert.assertTrue("Authorization failed", mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Авторизация по кнопке «Войти» при восстановлении пароля")
    public void checkLoginForgotPassword() {
        Allure.parameter("Проверка в ", browser);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        driver.get(FORGOT_PASSWORD_URL);
        forgotPasswordPage.waitFormIsLoad();
        forgotPasswordPage.clickAuthLink();
        loginPage.waitForm();
        loginPage.login(user);
        mainPage.waitPageLoad();
        Assert.assertTrue("Authorization failed", mainPage.isOrderButtonVisible());
    }
}