package tests;

import base.BaseTest;
import data.Product;
import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest extends BaseTest {

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
    @DisplayName("Validar obrigatóriedade do campo nome")
    public void nomeObrigatorio() {

        Product produto = TestData.TSHIRTRED;

        productsPage().adicionarCarrinho(produto.getId());

        cartPage().acessarCarrinho();

        assertEquals(
                1,
                cartPage().obterQuantidadeProdutos()
        );

        cartPage().avançarCheckout();

        assertEquals(
                "Checkout: Your Information",
                checkoutPage().obterTitulo()
        );

        checkoutPage().preencherTodosCampos(
                "",
                faker.name().lastName(),
                faker.address().zipCode()
        );

        checkoutPage().clicarContinuar();

        assertEquals(
                "Error: First Name is required",
                checkoutPage().obterErro()
        );
    }

    @Test
    @DisplayName("Validar obrigatóriedade do campo sobrenome")
    public void sobrenomeObrigatorio() {
        Product produto = TestData.TSHIRTRED;

        productsPage().adicionarCarrinho(produto.getId());

        cartPage().acessarCarrinho();

        assertEquals(
                1,
                cartPage().obterQuantidadeProdutos()
        );

        cartPage().avançarCheckout();

        assertEquals(
                "Checkout: Your Information",
                checkoutPage().obterTitulo()
        );

        checkoutPage().preencherTodosCampos(
                faker.name().firstName(),
                "",
                faker.address().zipCode()
        );

        checkoutPage().clicarContinuar();

        assertEquals(
                "Error: Last Name is required",
                checkoutPage().obterErro()
        );
    }

    @Test
    @DisplayName("Validar obrigatóriedade do campo ZIP")
    public void zipObrigatorio() {
        Product produto = TestData.TSHIRTRED;

        productsPage().adicionarCarrinho(produto.getId());

        cartPage().acessarCarrinho();

        assertEquals(
                1,
                cartPage().obterQuantidadeProdutos()
        );

        cartPage().avançarCheckout();

        assertEquals(
                "Checkout: Your Information",
                checkoutPage().obterTitulo()
        );

        checkoutPage().preencherTodosCampos(
                faker.name().firstName(),
                faker.name().lastName(),
                ""
        );

        checkoutPage().clicarContinuar();

        assertEquals(
                "Error: Postal Code is required",
                checkoutPage().obterErro()
        );
    }

    @Test
    @DisplayName("Cancelar checkout")
    public void cancelarCheckout() {
        Product produto = TestData.TSHIRTRED;
        productsPage().adicionarCarrinho(produto.getId());

        cartPage().acessarCarrinho();

        assertEquals(
                1,
                cartPage().obterQuantidadeProdutos()
        );

        cartPage().avançarCheckout();

        assertEquals(
                "Checkout: Your Information",
                checkoutPage().obterTitulo()
        );

        checkoutPage().preencherTodosCampos(
                faker.name().firstName(),
                faker.name().lastName(),
                ""
        );

        checkoutPage().cancelarBtn();

        assertEquals(
                "Your Cart",
                productsPage().obterTitulo()
        );
    }

    @Test
    @DisplayName("Fazer o Overview do pedido e finalizar")
    public void finalizarPedido() {
        Product produto = TestData.TSHIRTRED;
        productsPage().adicionarCarrinho(produto.getId());

        cartPage().acessarCarrinho();

        assertEquals(1, cartPage().obterQuantidadeProdutos());

        cartPage().avançarCheckout();

        assertEquals("Checkout: Your Information", checkoutPage().obterTitulo());

        checkoutPage().preencherTodosCampos(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().zipCode()
        );

        checkoutPage().clicarContinuar();

        assertEquals("Checkout: Overview", checkoutPage().obterTitulo());

        assertEquals(
                List.of(
                        TestData.TSHIRTRED.getName()
                ),
                cartPage().obterNomesProdutos()
        );

        checkoutPage().finalizarPedido();

        assertEquals("Checkout: Complete!", checkoutPage().obterTitulo());
        assertEquals("Thank you for your order!", checkoutPage().obterTituloComplete());
        assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!", checkoutPage().obterDescricaoComplete());

    }
}
