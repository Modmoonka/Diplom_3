package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static praktikum.EnvConfig.*;

/**
 * Тест для проверки работы главной страницы
 */

public class MainPage {
    private final WebDriver driver;

    //Локатор через кнопку "Личный кабинет"
    private static final By ACCOUNT_BUTTON = By.xpath("//a[@href='/account']");
    //Локатор по кнопке "Войти в аккаунт" на главной
    private static final By AUTH_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    //Локатор конструктора
    private static final By CONSTRUCTOR_BURGERS = By.xpath(".//p[text() = 'Конструктор']");
    //Локатор для кнопки "Оформить заказ"
    private static final By ORDER_BUTTON = By.xpath(".//button[text()='Оформить заказ']");
    //Локаторы для булок
    public static final By BUNS_BUTTON = By.xpath(".//span[text() = 'Булки']");;
    //Локаторы для соусов
    public static final By SAUCES_BUTTON = By.xpath(".//span[text() = 'Соусы']");
    private final By sousesHeader = By.xpath("//h2[@class = 'text text_type_main-medium mb-6 mt-10' and text() = 'Соусы']");
    //Локаторы для начинки
    public static final By STAFFING_BUTTON = By.xpath(".//span[text() = 'Начинки']");
    //Локаторы для раздела
    public static final By CHOISE_SECTION = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(BASE_URL);
    }

    public void waitUntilMainPAgeUrlIsVisible(){
        new WebDriverWait(driver, EXPLICIT_WAIT)
                .until(ExpectedConditions.urlToBe(BASE_URL));
    }

    @Step("Клик на кнопку 'Войти в аккаунт'")
    public void clickAuthButton() {
        driver.findElement(AUTH_BUTTON).click();
    }

    @Step("Нажимаем на кнопку Личный Кабинет")
    public void clickPersonalAccount() {
        driver.findElement(ACCOUNT_BUTTON).click();
    }

    public void waitPageLoad() {
        new WebDriverWait(driver, EXPLICIT_WAIT)
                .until(ExpectedConditions.visibilityOfElementLocated(CONSTRUCTOR_BURGERS));
    }
    @Step("Проверка отображение Конструктора")
    public boolean isConstructorVisible() {
        return driver.findElement(CONSTRUCTOR_BURGERS).isDisplayed();
    }

    @Step("Проверка отображение кнопки 'Оформить заказ'")
    public boolean isOrderButtonVisible() {
        return driver.findElement(ORDER_BUTTON).isDisplayed();
    }

    @Step("Клик по разделу булок")
    public void clickBuns() {
        driver.findElement(BUNS_BUTTON).click();
    }

    @Step("Ждем появления заголовка Булочки")
    public void waitBunsHeader() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(BUNS_BUTTON));
    }

    @Step("Клик по разделу соусов")
    public void clickSauces() {
        driver.findElement(SAUCES_BUTTON).click();
    }

    public void waitUntilSousesHeaderBecomesVisible() {
        new WebDriverWait(driver, EXPLICIT_WAIT)
                .until(ExpectedConditions.visibilityOfElementLocated(sousesHeader));
    }

    @Step("Клик по разделу начинок")
    public void clickStaffing() {
        driver.findElement(STAFFING_BUTTON).click();
    }

    @Step("Выбрать раздел")
    public boolean choiceSection(String section) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_WAIT);
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(CHOISE_SECTION, section));
    };
}