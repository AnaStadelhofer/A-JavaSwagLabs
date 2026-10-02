package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver criarDriver(String navegador) {

        if (navegador.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        }

        if (navegador.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        }

        throw new IllegalArgumentException(
                "Navegador não suportado: " + navegador
        );
    }
}