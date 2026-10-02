package tests;

import base.BaseTest;
import data.Product;
import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest extends BaseTest {

    @BeforeEach
    public void login() {
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
    @DisplayName("Acessar carrinho sem nenhum item")
    public void carrinhoVazio() {
        cartPage().acessarCarrinho();

        assertEquals(
                "Your Cart",
                cartPage().obterTitulo()
        );

        assertEquals(
                0,
                cartPage().obterQuantidadeProdutos()
        );
    }

    @Test
    @DisplayName("Voltar do carrinho para o Shopping")
    public void voltarCarrinhoParaShopping() {
        cartPage().acessarCarrinho();
        cartPage().cancelarCarrinho();

        assertEquals(
                "Products",
                productsPage().obterTitulo()
        );
    }

    @Test
    @DisplayName("Validar item do carrinho")
    public void validarUmItemNoCarrinho() {
        Product produto = TestData.BACKPACK;

        productsPage().adicionarCarrinho(produto.getId());

        cartPage().acessarCarrinho();

        assertEquals(
                1,
                cartPage().obterQuantidadeProdutos()
        );

        assertEquals(
                List.of(
                        TestData.BACKPACK.getName()
                ),
                cartPage().obterNomesProdutos()
        );

        assertEquals(
                List.of(
                        TestData.BACKPACK.getPrice()
                ),
                cartPage().obterPrecosProdutos()
        );
    }

    @Test
    @DisplayName("Validar dois itens no carrinho")
    public void validarDoisItemNoCarrinho() {
        Product produto1 = TestData.BACKPACK;
        Product produto2 = TestData.ONESIE;

        productsPage().adicionarCarrinho(produto1.getId());
        productsPage().adicionarCarrinho(produto2.getId());

        cartPage().acessarCarrinho();

        assertEquals(
                2,
                cartPage().obterQuantidadeProdutos()
        );

        assertEquals(
                List.of(
                        TestData.BACKPACK.getName(),
                        TestData.ONESIE.getName()
                ),
                cartPage().obterNomesProdutos()
        );

        assertEquals(
                List.of(
                        TestData.BACKPACK.getPrice(),
                        TestData.ONESIE.getPrice()
                ),
                cartPage().obterPrecosProdutos()
        );
    }

    @Test
    @DisplayName("Remover item do carrinho")
    public void removerItemDoCarrinho() {
        Product produto1 = TestData.BACKPACK;
        Product produto2 = TestData.ONESIE;

        productsPage().adicionarCarrinho(produto1.getId());
        productsPage().adicionarCarrinho(produto2.getId());

        cartPage().acessarCarrinho();

        assertEquals(
                2,
                cartPage().obterQuantidadeProdutos()
        );

        cartPage().removerItemDoCarinho(produto1.getId());

        assertFalse(
                cartPage().obterNomesProdutos().contains(produto1.getName()));

        assertEquals(
                1,
                cartPage().obterQuantidadeProdutos()
        );

    }
}
