package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    protected WebElement find(By elemento) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(elemento)
        );
    }

    protected void clickAndWait(By locator, ExpectedCondition<?> efeitoEsperado) {
        WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(3));

        for (int tentativa = 1; tentativa <= 3; tentativa++) {
            if (!driver.findElements(locator).isEmpty()) {
                try {
                    click(locator);
                } catch (StaleElementReferenceException ignored) {
                    // a página re-renderizou, tenta de novo
                }
            }
            try {
                espera.until(efeitoEsperado);
                return;
            } catch (TimeoutException ignored) {
                // clique pode ter sido perdido, repete
            }
        }
        wait.until(efeitoEsperado); // última chance, com erro claro se falhar
    }

    protected void click(By elemento) {
        wait.until(
                ExpectedConditions.elementToBeClickable(elemento)
        ).click();
    }

    protected void esperarUrl(String url) {
        wait.until(
                ExpectedConditions.urlContains(url)
        );
    }
}