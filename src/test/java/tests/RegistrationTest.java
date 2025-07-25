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
import static praktikum.EnvConfig.REGISTER_URL;

@RunWith(Parameterized.class)
public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registerPage;
    private Users user;
    String browser;

    @Parameterized.Parameters(name="Browser {0}")
    public static Object[][] initParams() {
        return new Object[][] {
                {"chrome"},
                {"yandex"}
        };
    }
    public RegistrationTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver(browser);
        driver.get(REGISTER_URL);
        driver.manage().window().maximize();
        registerPage = new RegistrationPage(driver);
        user = Users.random();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void checkUserRegistration() {
        Allure.parameter("Проверка в ", browser);
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegistration();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForm();
        Assert.assertTrue("Ошибка авторизации",driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Попытка регистрации с коротким паролем, менее 6 символов")
    public void checkShortPasswordRegistration() {
        Allure.parameter("Проверка в ", browser);
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword("Qwe12");
        registerPage.clickRegistration();
        Assert.assertEquals("Некорректный текст","Некорректный пароль",registerPage.getErrorPassword());
    }
}