package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.Integer.parseInt;

public class ProductsPage extends BasePage {

    private By title = By.className("title");
    private By qtdCarrinho = By.cssSelector("[data-test='shopping-cart-badge']");
    private By qtdProdutos = By.cssSelector("[data-test=\"inventory-item\"]");
    private By primeiroProdutoNome = By.cssSelector(".inventory_item:first-child .inventory_item_name");
    private By primeiroProdutoPreco = By.cssSelector(".inventory_item:first-child .inventory_item_price");
    private By filtro = By.className("product_sort_container");
    private By nomesProdutos = By.className("inventory_item_name");
    private By precosProdutos = By.className("inventory_item_price");

    // METODOS
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String obterNomePrimeiroProduto() {
        return find(primeiroProdutoNome).getText();
    }

    public String obterPrecoPrimeiroProduto() {
        return find(primeiroProdutoPreco).getText();
    }

    public String obterTitulo() {
        return find(title).getText();
    }

    public Integer obterQuantidadeProduto() {
        return driver.findElements(qtdProdutos).size();
    }

    public Integer obterQuantidadeCarrinho() {
        return parseInt(find(qtdCarrinho).getText());
    }

    private By botaoAdicionarProduto(String nomeProduto) {
        return By.id("add-to-cart-" + nomeProduto);
    }

    public void adicionarCarrinho(String nomeProduto) {
        int quantidadeAtual = driver.findElements(qtdCarrinho).isEmpty()
                ? 0
                : obterQuantidadeCarrinho();

        clickAndWait(
                botaoAdicionarProduto(nomeProduto),
                ExpectedConditions.textToBePresentInElementLocated(
                        qtdCarrinho, String.valueOf(quantidadeAtual + 1))
        );
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        qtdCarrinho,
                        String.valueOf(quantidadeAtual + 1)
                )
        );
    }

    private By botaoRemoverProduto(String nomeProduto) {
        return By.id("remove-" + nomeProduto);
    }

    public void removerCarrinho(String nomeProduto) {
        int quantidadeAtual = obterQuantidadeCarrinho();

        click(botaoRemoverProduto(nomeProduto));

        if (quantidadeAtual == 1) {
            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(qtdCarrinho)
            );
        } else {
            wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            qtdCarrinho,
                            String.valueOf(quantidadeAtual - 1)
                    )
            );
        }
    }

    public void aplicarFiltroProduto(String filtro) {

        Select select = new Select(find(this.filtro));

        select.selectByValue(filtro);
    }

    public List<String> obterNomesProdutos() {
        find(title);
        return driver.findElements(nomesProdutos).stream()
                .map(WebElement::getText).toList();
    }

    public List<Double> obterPrecosProdutos() {
        find(title);
        return driver.findElements(precosProdutos).stream()
                .map(WebElement::getText)
                .map(p -> p.replace("$", ""))
                .map(Double::parseDouble).toList();
    }

    public void abrirProdutoPorTitulo(String produto) {

        By produtoLocator = By.xpath(
                "//div[@data-test='inventory-item-name' and text()='" + produto + "']"
        );

        clickAndWait(
                produtoLocator,
                ExpectedConditions.urlContains("inventory-item.html")
        );
    }

    public void abrirProdutoPorImg(String produto) {

        By produtoLocator = By.cssSelector(
                "[data-test='inventory-item-" + produto + "-img']"
        );

        clickAndWait(
                produtoLocator,
                ExpectedConditions.urlContains("inventory-item.html")
        );
    }
}