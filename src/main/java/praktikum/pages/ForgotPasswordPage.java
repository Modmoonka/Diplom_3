package praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static praktikum.EnvConfig.EXPLICIT_WAIT;
import static praktikum.EnvConfig.FORGOT_PASSWORD_URL;

/**
 * Тест для проверки работы страницы восстановления пароля
 */

public class ForgotPasswordPage {
    private final WebDriver driver;

    //Локатор для кнопки Войти
    private final By AUTH_LINK = By.xpath(".//div/main/div/div/p/a");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы восстановления пароля")
    public void openRestorePage (){
        driver.get(FORGOT_PASSWORD_URL);
    }

    public void waitFormIsLoad() {
        new WebDriverWait(driver, EXPLICIT_WAIT)
                .until(ExpectedConditions.visibilityOfElementLocated(AUTH_LINK));
    }

    @Step("Клик по кнопке входа")
    public void clickAuthLink(){
        driver.findElement(AUTH_LINK).click();
    }
}
