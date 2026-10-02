package base;

import config.Config;
import driver.DriverFactory;
import extensions.ScreenshotOnFailureExtension;
import factory.PageFactory;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.*;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class BaseTest implements ScreenshotOnFailureExtension.HasDriver {

    protected WebDriver driver;
    private PageFactory pageFactory;
    protected Faker faker;

    @BeforeEach
    void setUp() {

        driver = DriverFactory.criarDriver(Config.BROWSER);

        driver.manage().window().maximize();

        driver.get(Config.BASE_URL);

        pageFactory = new PageFactory(driver);

        faker = new Faker();
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    protected LoginPage loginPage() {
        return pageFactory.loginPage();
    }

    protected ProductsPage productsPage() {
        return pageFactory.productsPage();
    }

    protected DetailsProductsPage detailsProductsPage() {
        return pageFactory.detailsProductsPage();
    }

    protected CartPage cartPage() {
        return pageFactory.cartPage();
    }

    protected CheckoutPage checkoutPage() {
        return pageFactory.checkoutPage();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}