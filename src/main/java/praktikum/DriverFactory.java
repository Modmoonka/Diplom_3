package praktikum;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver create(String browser) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--start-maximized", "--disable-notifications");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        switch (browser.toLowerCase()) {
            case "chrome":
                return openChromeDriver(options);
            case "yandex":
                return openYandexDriver(options);
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }
    }

    private static WebDriver openChromeDriver(ChromeOptions options) {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER"));
        return new ChromeDriver(options);
    }

    private static WebDriver openYandexDriver(ChromeOptions options) {
        System.setProperty("webdriver.chrome.driver",
                System.getenv("CHROMEDRIVER_132"));
        options.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
        return new ChromeDriver(options);
    }
}
