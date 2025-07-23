package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static praktikum.EnvConfig.*;

/**
 * Тест для проверки работы страницы регистрации
 */

public class RegistrationPage {
    private final WebDriver driver;
    //Локатор поля Имя
    private final By USER_NAME = By.xpath("//label[text()='Имя']/following-sibling::input");
    //Локатор поля Email
    private final By EMAIL_FIELD = By.xpath("//label[text()='Email']/following-sibling::input");
    //Локатор поля Пароль
    private final By PASSWORD = By.xpath("//input[@type='password']");
    //Локатор для кнопки Зарегистрироваться
    private final By REGISTRATION_BUTTON = By.className("button_button__33qZ0");
    //Локатор для кнопки Войти
    private final By authLinkByRegForm = By.className("Auth_link__1fOlj");
    //Локатор для ошибки
    private final By ERROR_MESSAGE= By.xpath(".//fieldset[3]/div/p[contains(text(), 'Некорректный пароль')]");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открытие страницы регистрации")
    public void openRegPage (){
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Ввод значения в поле Имя")
    public void clickName(String name) {
        driver.findElement(USER_NAME).sendKeys(name);
    }

    @Step("Вводим email")
    public void setEmail(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Ввод пароля ")
    public void enterPassword(String password) {
        driver.findElement(PASSWORD).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegister() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    @Step("Проверка отображения ошибки о некорректности пароля")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void waitForm() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTRATION_BUTTON));
    }

    @Step("Клик по кнопке Войти")
    public void clickAuthLink() {
        driver.findElement(authLinkByRegForm).click();
    }
}
