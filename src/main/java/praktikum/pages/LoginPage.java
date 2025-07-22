package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static praktikum.EnvConfig.*;

public class LoginPage {

    private final WebDriver driver;

    //Локатор для заголовка "Вход"
    private final By headingInput = By.xpath(".//*[@id='root']//h2[.='Вход']");
    //Локатор для поля "Email"
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    //Локатор для поля "Пароль"
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    //Локатор для кнопки "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    //Локатор для ссылки "Восстановить пароль"
    private final By forgotPassword = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка текста 'Вход'")
    public String getTextInput() {
        return driver.findElement(headingInput).getText();
    }

    @Step("Вводим email")
    public void emailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Вводим пароль")
    public void passwordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Вводим пароль")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Проверка ссылки Восстановить пароль")
    public void clickForgotPassword() {
        driver.findElement(forgotPassword).click();
    }

    @Step("Проверка ссылки Зарегистрироваться")
    public void openURL() {
        driver.get(REGISTER_URL);
    }

    @Step("Метод ввода данных")
    public void login(String email, String password) {
        emailField(email);
        passwordField(password);
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        clickLoginButton();
    }
}
