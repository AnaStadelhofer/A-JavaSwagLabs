package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private By title = By.cssSelector("[data-test='title']");
    private By nome = By.id("first-name");
    private By sobrenome = By.id("last-name");
    private By zip = By.id("postal-code");
    private By btnContinue = By.id("continue");
    private By btnCancel = By.id("cancel");
    private By mensagemErro = By.cssSelector("[data-test='error']");
    private By btnFinalizar = By.id("finish");

    private By titleComplete = By.cssSelector("[data-test='complete-header']");
    private By descricaoComplete = By.cssSelector("[data-test='complete-text']");

    private By btnGerarPDF = By.id("generate-pdf-order");
    private By btnVoltar = By.id("back-to-products");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String obterErro() {
        return find(mensagemErro).getText();
    }

    public String obterTituloComplete() {
        return find(titleComplete).getText();
    }

    public String obterDescricaoComplete() {
        return find(descricaoComplete).getText();
    }

    public String obterTitulo() {
        return find(title).getText();
    }

    public void preencherNome(String valor) {
        find(nome).sendKeys(valor);
    }

    public void preencherSobrenome(String valor) {
        find(sobrenome).sendKeys(valor);
    }

    public void preencherZIP(String valor) {
        find(zip).sendKeys(valor);
    }

    public void clicarContinuar() {
        click(btnContinue);
        wait.until(
                ExpectedConditions.urlContains("cart.html")
        );
    }

    public void finalizarPedido() {
        click(btnFinalizar);
        wait.until(
                ExpectedConditions.urlContains("checkout-complete.html")
        );
    }

    public void cancelarBtn() {
        click(btnCancel);
        esperarUrl("cart.html");
    }

    public void preencherTodosCampos(String nome, String sobrenome, String zip) {
        preencherNome(nome);
        preencherSobrenome(sobrenome);
        preencherZIP(zip);
    }
}