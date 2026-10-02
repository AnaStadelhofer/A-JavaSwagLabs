package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    protected void click(By elemento) {
        wait.until(
                ExpectedConditions.elementToBeClickable(elemento)
        ).click();
    }
}