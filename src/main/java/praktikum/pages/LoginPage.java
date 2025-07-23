package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static praktikum.EnvConfig.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import praktikum.Users.Users;

/**
 * Тест для проверки работы страницы авторизации
 */

public class LoginPage {

    private final WebDriver driver;

    //Локатор для заголовка "Вход"
    private final By HEADING_INPUT= By.xpath(".//*[@id='root']//h2[.='Вход']");
    //Локатор для поля "Email"
    private final By EMAIL_FIELD = By.xpath("//label[text()='Email']/following-sibling::input");
    //Локатор для поля "Пароль"
    private final By PASSWORD_FIELD = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    //Локатор для кнопки "Войти"
    private final By LOGIN = By.xpath(".//button[text()='Войти']");
    //Локатор для кнопки "Зарегистрироваться"
    private final By REGISTER_LINK = By.xpath(".//a[@href='/register']");
    //Локатор для ссылки "Восстановить пароль"
    private final By FORGOT_PASS = By.xpath(".//a[text()='Восстановить пароль']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка текста 'Вход'")
    public String getTextInput() {
        return driver.findElement(HEADING_INPUT).getText();
    }

    @Step("Вводим email")
    public void emailField(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Вводим пароль")
    public void passwordField(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Кликаем на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(LOGIN).click();
    }

    @Step("Проверка ссылки Восстановить пароль")
    public void clickForgotPassword() {
        driver.findElement(FORGOT_PASS).click();
    }

    @Step("Проверка ссылки Зарегистрироваться")
    public void openRegister() {
        driver.findElement(REGISTER_LINK).click();
    }

    @Step("Метод ввода данных")
    public void login(Users user) {
        waitForm();
        emailField(user.getEmail());
        passwordField(user.getPassword());
        clickLoginButton();
    }

    public void waitForm() {
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN));
    }
}
