package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver criarDriver(String navegador) {

        if (navegador.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");

            return new ChromeDriver(options);
        }

        if (navegador.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            options.addArguments("--headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");

            return new FirefoxDriver(options);
        }

        throw new IllegalArgumentException(
                "Navegador não suportado: " + navegador
        );
    }
}