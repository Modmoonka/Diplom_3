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
import praktikum.Users.*;
import static org.junit.Assert.assertEquals;
import static praktikum.EnvConfig.REGISTER_URL;
import static praktikum.Users.RandomUsers.*;
import static praktikum.pages.RegistrationPage.EXPECTED_ERROR_PASSWORD;

/**
 Регистрация
 Проверь:
 Успешную регистрацию.
 Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
 */

@RunWith(Parameterized.class)
public class RegistrationTest {
    private WebDriver webDriver;
    private String browser;
    private RegistrationPage registerPage;
    private Users user;

    public RegistrationTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Object[] browsers() {
        return new Object[]{"chrome", "yandex"};
    }

    @Before
    public void setUp() {
        webDriver = DriverFactory.createDriver(browser);
        registerPage = new RegistrationPage(webDriver);
        user = new Users(USER_NAME, USER_PASSWORD, USER_EMAIL);
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void checkUserRegistration() {
        Allure.parameter("Проверка в ", browser);
        registerPage.open();
        registerPage.clickUserName();
        registerPage.setName(USER_NAME);
        registerPage.clickEmailField();
        registerPage.setEmail(USER_EMAIL);
        registerPage.clickPassword();
        registerPage.setPassword(USER_PASSWORD);
        registerPage.clickRegistration();
        assertEquals("Должна быть страница входа", REGISTER_URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Попытка регистрации с коротким паролем, менее 6 символов")
    public void checkShortPasswordRegistration() {
        Allure.parameter("Проверка в ", browser);
        registerPage.open();
        registerPage.clickUserName();
        registerPage.setName(USER_NAME);
        registerPage.clickEmailField();
        registerPage.setEmail(USER_EMAIL);
        registerPage.clickPassword();
        registerPage.setPassword("Qwe");
        registerPage.clickRegistration();
        assertEquals("Некорректный текст",EXPECTED_ERROR_PASSWORD,registerPage.getErrorPassword());
    }
}