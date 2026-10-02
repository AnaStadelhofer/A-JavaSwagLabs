package tests;

import base.BaseTest;
import data.TestData;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    @DisplayName("Realizar login com sucesso")
    @Test
    void deveRealizarLoginComSucesso() {

        loginPage().realizarLogin(
                TestData.STANDARD_USER,
                TestData.PASSWORD
        );

        assertEquals(
                "Products",
                productsPage().obterTitulo()
        );
    }

    @DisplayName("Login com senha inválida")
    @Test
    void naoDeveLogarComSenhaInvalida() {
        loginPage().realizarLogin(
                TestData.STANDARD_USER,
                TestData.INVALID_PASSWORD
        );

        assertEquals(
                "Epic sadface: Username and password do not match any user in this service",
                loginPage().obterMensagemErro()
        );
    }

    @DisplayName("Login com usuário inválido")
    @Test
    void naoDeveLogarComUsuarioInvalido() {
        loginPage().realizarLogin(
                TestData.INVALID_USER,
                TestData.PASSWORD
        );

        assertEquals(
                "Epic sadface: Username and password do not match any user in this service",
                loginPage().obterMensagemErro()
        );
    }

    @DisplayName("Login com campos vazios")
    @Test
    void naoDeveLogarComCamposVazios() {
        loginPage().realizarLogin(
                "",
                ""
        );

        assertEquals(
                "Epic sadface: Username is required",
                loginPage().obterMensagemErro()
        );
    }

    @DisplayName("Login com usuário bloqueado")
    @Test
    void naoDeveLogarUsuarioBloqueado() {
        loginPage().realizarLogin(
                TestData.LOCKED_USER,
                TestData.PASSWORD
        );

        assertEquals(
                "Epic sadface: Sorry, this user has been locked out.",
                loginPage().obterMensagemErro()
        );
    }
}