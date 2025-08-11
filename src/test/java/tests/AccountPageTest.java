package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
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
 Переход в личный кабинет
 Проверь переход по клику на «Личный кабинет».
 Переход из личного кабинета в конструктор
 Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers.
 Выход из аккаунта
 Проверь выход по кнопке «Выйти» в личном кабинете.
 */

@RunWith(Parameterized.class)
public class AccountPageTest {
    private WebDriver webDriver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountPage accountPage;

    String browser;
    String email = "email123456@mail.com";
    String password = "qwerty1";

    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Object[] browsers() {
        return new Object[]{"chrome", "yandex"};
    }


    public AccountPageTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void setUp() {
        webDriver = DriverFactory.createDriver(browser);
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        accountPage = new AccountPage(webDriver);

    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    @DisplayName("Переход в личнвй кабинетс главной страницы после в хода в аккаунт")
    public void checkTransferToAccountPage() {
        Allure.parameter("Проверка в ", browser);
        mainPage.openMainPage();
        mainPage.clickAuthButton();
        loginPage.waitForm();
        loginPage.loginPersonalAccount(email, password);
        mainPage.waitUntilMainPAgeUrlIsVisible();
        mainPage.clickPersonalAccount();
        accountPage.waitForm();
        assertEquals("Должна быть страница личного кабинета", PROFILE_PAGE_URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в в конструктор главной страницы из личного кабинета")
    public void checkTransferToConstuctor() {
        Allure.parameter("Проверка в ", browser);
        mainPage.openMainPage();
        mainPage.clickAuthButton();
        loginPage.waitForm();
        loginPage.loginPersonalAccount(email, password);
        mainPage.waitUntilMainPAgeUrlIsVisible();
        mainPage.clickPersonalAccount();
        accountPage.waitForm();
        accountPage.clickConstructor();
        mainPage.isConstructorVisible();
        assertEquals("Должна быть страница личного кабинета", BASE_URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход из аккаунта из личного кабинета")
    public void checkExitFromAccount() {
        Allure.parameter("Проверка в ", browser);
        mainPage.openMainPage();
        mainPage.clickAuthButton();
        loginPage.waitForm();
        loginPage.loginPersonalAccount(email, password);
        mainPage.waitUntilMainPAgeUrlIsVisible();
        mainPage.clickPersonalAccount();
        accountPage.waitForm();
        accountPage.clickExit();
        loginPage.waitForm();
        assertEquals("Должна быть страница входа в аккаунт", LOGIN_PAGE_URL, webDriver.getCurrentUrl());
    }
}