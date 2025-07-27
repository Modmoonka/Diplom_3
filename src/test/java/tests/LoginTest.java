package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.DriverFactory;
import praktikum.pages.*;
import static org.junit.Assert.assertEquals;
import static praktikum.EnvConfig.*;

/**
 Вход
 Проверь:
 вход по кнопке «Войти в аккаунт» на главной,
 вход через кнопку «Личный кабинет»,
 вход через кнопку в форме регистрации,
 вход через кнопку в форме восстановления пароля.
 */

@RunWith(Parameterized.class)
public class LoginTest {
    private WebDriver webDriver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    String browser;
    String email = "email123456@mail.com";
    String password = "qwerty1";

    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Object[] browsers() {
        return new Object[]{"chrome", "yandex"};
    }

    public LoginTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void setUp() {
        webDriver = DriverFactory.createDriver(browser);
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        registerPage = new RegistrationPage(webDriver);
        forgotPasswordPage = new ForgotPasswordPage(webDriver);
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    @DisplayName("Авторизация по кнопке «Войти в аккаунт»")
    public void checkLoginMainPage() {
        Allure.parameter("Проверка в ", browser);
        mainPage.openMainPage();
        mainPage.clickAuthButton();
        loginPage.waitForm();
        loginPage.loginPersonalAccount(email, password);
        mainPage.waitUntilMainPAgeUrlIsVisible();
        assertEquals("Должна быть страница входа", BASE_URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Авторизация по кнопке «Личный кабинет» на главной")
    public void checkLoginAccount() {
        Allure.parameter("Проверка в ", browser);
        mainPage.openMainPage();
        mainPage.clickPersonalAccount();
        loginPage.waitForm();
        loginPage.loginPersonalAccount(email, password);
        mainPage.waitUntilMainPAgeUrlIsVisible();
        assertEquals("Должна быть страница входа", BASE_URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Авторизация по кнопке «Войти» на форме регистрации")
    public void checkLoginRegistration() {
        Allure.parameter("Проверка в ", browser);
        registerPage.open();
        registerPage.clickLoginLButton();
        loginPage.waitForm();
        loginPage.loginPersonalAccount(email, password);
        mainPage.waitUntilMainPAgeUrlIsVisible();
        assertEquals("Должна быть страница входа", BASE_URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Авторизация по кнопке «Войти» при восстановлении пароля")
    public void checkLoginForgotPassword() {
        Allure.parameter("Проверка в ", browser);
        forgotPasswordPage.openRestorePage();
        forgotPasswordPage.clickAuthLink();
        loginPage.waitForm();
        loginPage.loginPersonalAccount(email, password);
        mainPage.waitUntilMainPAgeUrlIsVisible();
        assertEquals("Должна быть страница входа", BASE_URL, webDriver.getCurrentUrl());
    }
}