package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.DriverFactory;
import praktikum.pages.*;
import praktikum.Users.*;
import static praktikum.EnvConfig.URL_USER;

@RunWith(Parameterized.class)
public class AccountPageTest {
    private AccountPage accountPage;
    private WebDriver driver;
    private MainPage mainPage;
    private Users user;
    private LoginPage loginPage;
    UserClient userApiClient;
    String browser;

    @Parameterized.Parameters(name="Browser {0}")
    public static Object[][] initParams() {
        return new Object[][] {
                {"chrome"},
                {"yandex"}
        };
    }
    public AccountPageTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver(browser);
        driver.get(URL_USER);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        userApiClient = new UserClient();
        user = Users.random();
        userApiClient.register(user);
        mainPage.waitPageLoad();
        mainPage.openMainPage();
        loginPage.waitForm();
        loginPage.login(user);
        mainPage.waitPageLoad();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Клик на личный кабинет")
    public void checkAccountPage() {
        Allure.parameter("Проверка в ", browser);
        accountPage = new AccountPage(driver);
        mainPage.clickAuthButton();
        accountPage.waitForm();
        Assert.assertTrue("Не произошел переход в Личный Кабинет",driver.getCurrentUrl().contains("/account/profile"));
    }

    @Test
    @DisplayName("Клик в Конструктор через кнопку Конструктор")
    public void choiceOpenConstructor() {
        Allure.parameter("Проверка в ", browser);
        mainPage.clickPersonalAccount();
        accountPage = new AccountPage(driver);
        accountPage.waitForm();
        accountPage.clickConstructor();
        mainPage.waitPageLoad();
        Assert.assertTrue("Конструктор не загружен", mainPage.isConstructorVisible());
    }

    @Test
    @DisplayName("Выход из профиля")
    public void checkLogout() {
        Allure.parameter("Проверка в ", browser);
        mainPage.clickAuthButton();
        accountPage = new AccountPage(driver);
        accountPage.waitForm();
        accountPage.clickExit();
        loginPage.waitForm();
        Assert.assertTrue("Не произошел редирект на форму авторизации",driver.getCurrentUrl().contains("/login"));
    }
}