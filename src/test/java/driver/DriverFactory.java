package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver criarDriver(String navegador) {

        if (navegador.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            return new ChromeDriver(options);
        }


        if (navegador.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        }

        throw new IllegalArgumentException(
                "Navegador não suportado: " + navegador
        );
    }
}