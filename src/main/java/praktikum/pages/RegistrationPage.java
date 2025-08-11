package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static praktikum.EnvConfig.*;

/**
 * Тест для проверки работы страницы регистрации
 */

public class RegistrationPage {
    private final WebDriver driver;
    //Локатор поля Имя
    private final By USER_NAME = By.xpath("//input[contains(@class, 'input__textfield') and contains(@name, 'name')]");
    //Локатор поля Email
    private final By EMAIL_FIELD = By.xpath("//label[text()='Email']/following-sibling::input[@type='text' and @name='name']");
    //Локатор поля Пароль
    private final By PASSWORD = By.xpath("//input[@name='Пароль' and @type='password']");
    //Локатор для кнопки Зарегистрироваться
    private final By REGISTRATION_BUTTON = By.className("button_button__33qZ0");
    //Локатор для кнопки Войти
    private final By authLinkByRegForm = By.xpath("//a[text()='Войти']");
    //Локатор для ошибки
    private final By ERROR_MESSAGE= By.xpath(".//fieldset[3]/div/p[contains(text(), 'Некорректный пароль')]");
    public static final String EXPECTED_ERROR_PASSWORD = "Некорректный пароль";

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(REGISTER_URL);
    }

    //Кликнуть в поле Имя
    public void clickUserName(){
        driver.findElement(USER_NAME).click();
    }

    //Ввести имя в поле
    public void setName(String name){
        driver.findElement(USER_NAME).sendKeys(name);
    }

    //Кликнуть в поле Email
    public void clickEmailField(){
        driver.findElement(EMAIL_FIELD).click();
    }

    //Ввести значение в поле Email
    public void setEmail(String email){
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    //Кликнуть в поле Пароль
    public void clickPassword(){
        driver.findElement(PASSWORD).click();
    }

    //Ввести пароль в поле Пароль
    public void setPassword(String password){
        driver.findElement(PASSWORD).sendKeys(password);
    }

    // Ожидание страницы регистрации
    public void waitlRegistrationPage() {
        new WebDriverWait(driver, EXPLICIT_WAIT)
                .until(ExpectedConditions.urlToBe(REGISTER_URL));
    }

    // скролл до кнопки Регистрация
    public void scrollRegistrationButton() {
        WebElement element = driver.findElement(REGISTRATION_BUTTON);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Клик по кнопке регистрация
    public void clickRegistration() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    //Клик по кнопке входа
    public void clickLoginLButton() {
        driver.findElement(authLinkByRegForm).click();
    }
    //Вывод сообщения об ошибке
    public String getErrorPassword(){
        return driver.findElement(ERROR_MESSAGE).getText();
    }
    public void inputRegistrationForm(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
    }
}
