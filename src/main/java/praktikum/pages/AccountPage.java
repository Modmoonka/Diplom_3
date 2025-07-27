package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static praktikum.EnvConfig.EXPLICIT_WAIT;

public class AccountPage {
    private WebDriver driver;

    //Локатор логотипа
    private static By LOGO = By.xpath(".//div[starts-with(@class, 'AppHeader_header')]/a[@href='/']");
    //Локатор кнопки Выход
    private static By EXIT_BUTTON = By.xpath(".//button[text()='Выход']");
    //Локатор кнопки Конструктор
    private static By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");
    // кнопка главного экрана
    private final By mainPageButton = By.xpath("//div[@class = 'AppHeader_header__logo__2D0X2']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Выход")
    public void clickExit() {
        driver.findElement(EXIT_BUTTON).click();
    }

    @Step("Клик на логотип")
    public void clickLogo() {
        driver.findElement(LOGO).click();
    }

    //клик по кнопке Главного экрана
    public void clickMainPageButton() {
        driver.findElement(mainPageButton).click();
    }

    @Step("Клик по кнопке Конструктор")
    public void clickConstructor() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    public void waitForm() {
        WebElement until = new WebDriverWait(driver, EXPLICIT_WAIT)
                .until(ExpectedConditions.visibilityOfElementLocated(EXIT_BUTTON));
    }
}
