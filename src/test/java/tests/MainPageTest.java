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
import praktikum.pages.MainPage;
import praktikum.DriverFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static praktikum.EnvConfig.BASE_URL;
import static praktikum.pages.MainPage.BUNS_BUTTON;

@RunWith(Parameterized.class)
public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    String browser;

    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Object[] browsers() {
        return new Object[]{"chrome", "yandex"};
    }

    public MainPageTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver(browser);
        driver.get(BASE_URL);
        mainPage = new MainPage(driver);
        mainPage.waitPageLoad();

    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    @DisplayName("Открыть раздел булки")
    public void checkClickBunsButton(){
        Allure.parameter("Проверка в ", browser);
        mainPage.openMainPage();
        mainPage.clickSauces();
        mainPage.clickBuns();
        mainPage.waitBunsHeader();
        Assert.assertTrue("Не перешли на раздел Булки",mainPage.choiceSection("Булки"));
    }

    @Test
    @DisplayName("Открыть раздел соусы")
    public void choiceSauces() {
        Allure.parameter("Проверка в ", browser);
        mainPage.openMainPage();
        mainPage.clickSauces();
        Assert.assertTrue("Не перешли на раздел Соусы",mainPage.choiceSection("Соусы"));
    }

    @Test
    @DisplayName("Открыть раздел начинки")
    public void checkSelectFillingsSection() {
        Allure.parameter("Проверка в ", browser);
        mainPage.openMainPage();
        mainPage.clickStaffing();
        Assert.assertTrue("Не перешли на раздел Начинки",mainPage.choiceSection("Начинки"));
    }
}
