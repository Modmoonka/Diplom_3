package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                return new ChromeDriver(chromeOptions);

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\admin\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                return new ChromeDriver(options);

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
