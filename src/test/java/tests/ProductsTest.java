package tests;

import base.BaseTest;
import data.Product;
import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductsTest extends BaseTest {

    @BeforeEach
    void login() {
        loginPage().realizarLogin(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        assertEquals(
                "Products",
                productsPage().obterTitulo()
        );
    }

    @Test
    @DisplayName("Adicionar um produto no carrinho e validar quantidade")
    void adicionarUmProdutoNoCarrinho() throws InterruptedException {
        Product produto = TestData.BACKPACK;

        productsPage().adicionarCarrinho(produto.getId());

        assertEquals(1, productsPage().obterQuantidadeCarrinho());
    }

    @Test
    @DisplayName("Adicionar dois produtos no carrinho e validar quantidade")
    void adicionarDoisProdutosNoCarrinho() {
        Product produto1 = TestData.BACKPACK;
        Product produto2 = TestData.BIKE_LIGHT;

        productsPage().adicionarCarrinho(produto1.getId());
        productsPage().adicionarCarrinho(produto2.getId());

        assertEquals(2, productsPage().obterQuantidadeCarrinho());
    }

    @Test
    @DisplayName("Adicionar um produto e remove-ló do carrinho")
    void removerProdutoCarrinho() {
        Product produto1 = TestData.BACKPACK;
        Product produto2 = TestData.BIKE_LIGHT;

        productsPage().adicionarCarrinho(produto1.getId());
        productsPage().adicionarCarrinho(produto2.getId());
        productsPage().removerCarrinho(produto2.getId());

        assertEquals(1, productsPage().obterQuantidadeCarrinho());
    }

    @Test
    @DisplayName("Validar quantidade de produtos na tela inicial")
    void validarQuantidadeInicialDeProdutos() {
        assertEquals(6, productsPage().obterQuantidadeProduto());
    }

    @Test
    @DisplayName("Validar o nome e preço do primeiro produto")
    void validarNomeEPrecoProduto() {
        Product produto = TestData.BACKPACK;

        assertEquals(
                produto.getName(),
                productsPage().obterNomePrimeiroProduto()
        );

        assertEquals(
                "$" + produto.getPrice(),
                productsPage().obterPrecoPrimeiroProduto()
        );
    }

    @Test
    @DisplayName("Visualizar produto pela imagem e validar dados dele")
    void visualizarProdutoPorImg() {
        Product produto = TestData.BACKPACK;

        productsPage().abrirProdutoPorImg(produto.getId());

        assertEquals(
                produto.getName(),
                detailsProductsPage().obterNomeProduto()
        );

        assertEquals(
                "$" + produto.getPrice(),
                detailsProductsPage().obterPrecoProduto()
        );

        assertEquals(
                produto.getDescription(),
                detailsProductsPage().obterDescricaoProduto()
        );
    }

    @Test
    @DisplayName("Visualizar produto pelo título e validar dados dele")
    void visualizarProdutoPorTitulo() {
        Product produto = TestData.BACKPACK;

        productsPage().abrirProdutoPorTitulo(produto.getName());

        assertEquals(
                produto.getName(),
                detailsProductsPage().obterNomeProduto()
        );

        assertEquals(
                "$" + produto.getPrice(),
                detailsProductsPage().obterPrecoProduto()
        );

        assertEquals(
                produto.getDescription(),
                detailsProductsPage().obterDescricaoProduto()
        );

    }

    @Test
    @DisplayName("Aplicar filtro de nome ascendente")
    void aplicarFiltroNomeAtoZ() {

        productsPage().aplicarFiltroProduto(TestData.FILTER_ATOZ);

        List<String> produtos =
                productsPage().obterNomesProdutos();

        List<String> produtosOrdenados =
                produtos.stream()
                        .sorted()
                        .toList();

        assertEquals(
                produtosOrdenados,
                produtos
        );
    }

    @Test
    @DisplayName("Aplicar filtro de nome descendente")
    void aplicarFiltroNomeZtoA() {
        productsPage().aplicarFiltroProduto(TestData.FILTER_ZTOA);

        List<String> produtos =
                productsPage().obterNomesProdutos();

        List<String> produtosOrdenados =
                produtos.stream()
                        .sorted(Comparator.reverseOrder())
                        .toList();

        assertEquals(
                produtosOrdenados,
                produtos
        );
    }

    @Test
    @DisplayName("Aplicar filtro de preço menor para maior")
    void aplicarFiltroPrecoMenor() {
        productsPage().aplicarFiltroProduto(TestData.FILTER_LOWTOHIGH);

        List<Double> precos =
                productsPage().obterPrecosProdutos();

        List<Double> precosOrdenados =
                precos.stream()
                        .sorted()
                        .toList();

        assertEquals(
                precosOrdenados,
                precos
        );
    }

    @Test
    @DisplayName("Aplicar filtro de preço maior para menor")
    void aplicarFiltroPrecoMaior() {
        productsPage().aplicarFiltroProduto(TestData.FILTER_HIGHTOLOW);

        List<Double> precos =
                productsPage().obterPrecosProdutos();

        List<Double> precosOrdenados =
                precos.stream()
                        .sorted(Comparator.reverseOrder())
                        .toList();

        assertEquals(
                precosOrdenados,
                precos
        );
    }

    @Test
    @DisplayName("Adicionar produto no carrinho na tela de detalhes")
    public void adicionarCarrinhoEmDetalhes() {
        Product produto = TestData.BACKPACK;

        productsPage().abrirProdutoPorTitulo(produto.getName());

        detailsProductsPage().adicionarNoCarrinho();

        assertEquals(
                1,
                productsPage().obterQuantidadeCarrinho()
        );
    }
}
