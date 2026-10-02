package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Double.parseDouble;

public class DetailsProductsPage extends BasePage {

    private By productName = By.cssSelector("[data-test='inventory-item-name']");
    private By productDescription = By.cssSelector("[data-test='inventory-item-desc']");
    private By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By btnAddToCart = By.id("add-to-cart");

    public DetailsProductsPage(WebDriver driver) {
        super(driver);
    }

    // Metodos

    public String obterNomeProduto() {
        return find(productName).getText();
    }

    public String obterPrecoProduto() {
        return find(productPrice).getText();
    }

    public String obterDescricaoProduto() {
        return find(productDescription).getText();
    }

    public void adicionarNoCarrinho() {
        click(btnAddToCart);
    }
}
