package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void preencherUsuario(String usuario) {
        find(username).sendKeys(usuario);
    }

    public void preencherSenha(String senha) {
        find(password).sendKeys(senha);
    }

    public void clicarLogin() {
        click(loginButton);
    }

    public void realizarLogin(String usuario, String senha) {
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarLogin();
    }

    public String obterMensagemErro() {
        return driver.findElement(errorMessage).getText();
    }
}