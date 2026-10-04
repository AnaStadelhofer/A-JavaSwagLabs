package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    private By title = By.cssSelector("[data-test='title']");
    private By btnCheckout = By.id("checkout");
    private By btnCancel = By.id("continue-shopping");
    private By produtos = By.className("cart_item");
    private By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    private By nomesProdutos = By.cssSelector("[data-test='inventory-item-name']");
    private By precosProdutos = By.cssSelector("[data-test='inventory-item-price']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Metodos

    public void acessarCarrinho() {
        click(cartIcon);
        esperarUrl("cart.html");
    }

    public String obterTitulo() {
        return find(title).getText();
    }

    public void avançarCheckout() {
        click(btnCheckout);
        esperarUrl("checkout-step-one.html");
    }

    public void cancelarCarrinho() {
        click(btnCancel);
        esperarUrl("inventory.html");
    }

    public int obterQuantidadeProdutos() {
        return driver.findElements(produtos).size();
    }

    public List<String> obterNomesProdutos() {
        return driver.findElements(nomesProdutos)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<Double> obterPrecosProdutos() {
        return driver.findElements(precosProdutos)
                .stream()
                .map(WebElement::getText)
                .map(preco -> preco.replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    public void removerItemDoCarinho(String produto) {
        click(By.name("remove-" + produto));
    }
}