package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DriverFactory {
    public static WebDriver createDriver(String browserName) {
        if (browserName == null) {
            browserName = "chrome"; // если ничего не передали, по умолчанию Chrome
        }
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                return new FirefoxDriver(firefoxOptions);

            default:
                throw new IllegalArgumentException("Браузер " + browserName + " не работает");
        }
    }


    private static final Properties properties = new Properties();
    static {
        try (InputStream input = DriverFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Файл config.properties не найден!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    public static String getBrowser() {
        String browser = System.getProperty("browser");
        if (browser != null) {
            return browser;
        }
        browser = System.getenv("BROWSER");
        if (browser != null) {
            return browser;
        }
        browser = DriverFactory.getProperty("browser");
        if (browser != null) {
            return browser;
        }
        return "chrome";
    }
}
