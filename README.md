# A-JavaSauceDemo

Projeto de automação de testes end-to-end da loja [Sauce Demo](https://www.saucedemo.com/), feito com **Java, Selenium WebDriver e JUnit 5**, usando o padrão **Page Object Model**. Os resultados são publicados em um relatório **Allure**, gerado automaticamente por uma pipeline no **GitHub Actions**.

## Tecnologias

| Ferramenta | Versão | Uso |
|---|---|---|
| Java | 26 | Linguagem |
| Maven | - | Build e gerenciamento de dependências |
| Selenium WebDriver | 4.50.0 | Automação do navegador |
| JUnit 5 | 5.13.4 | Execução e organização dos testes |
| JUnit Platform Suite | 1.13.4 | Suíte de regressão |
| Allure | 2.29.0 | Relatório de testes |
| DataFaker | 2.5.3 | Geração de dados aleatórios |
| GitHub Actions | - | Pipeline de CI |

## Cenários cobertos

Total: **27 testes**.

| Classe | Testes | O que valida |
|---|---|---|
| `LoginTest` | 5 | Login com sucesso, senha inválida, usuário inválido, campos vazios e usuário bloqueado |
| `ProductsTest` | 12 | Quantidade de produtos, nome e preço, detalhes do produto (por título e por imagem), adicionar e remover do carrinho, filtros de nome e preço |
| `CartTest` | 5 | Carrinho vazio, voltar para a loja, validar um e dois itens, remover item |
| `CheckoutTest` | 5 | Obrigatoriedade de nome, sobrenome e CEP, cancelar checkout e finalizar pedido |

## Estrutura do projeto

```
src/test/java
├── base          BasePage (esperas e ações comuns) e BaseTest (setup e teardown)
├── config        Config (URL base e navegador)
├── data          TestData e Product (usuários, produtos e filtros)
├── driver        DriverFactory (criação do WebDriver)
├── extensions    ScreenshotOnFailureExtension (screenshot ao falhar)
├── factory       PageFactory (instancia as páginas)
├── pages         Page Objects: Login, Products, DetailsProducts, Cart e Checkout
├── suite         RegressionSuite (executa todas as classes de teste)
└── tests         LoginTest, ProductsTest, CartTest e CheckoutTest
```

## Pré-requisitos

- JDK 26
- Maven
- Google Chrome (ou Firefox) instalado

O Selenium Manager baixa o driver do navegador automaticamente.

## Como executar

Rodar todos os testes:

```bash
mvn test
```

Escolher o navegador (o padrão é `chrome`):

```bash
mvn test -Dbrowser=firefox
```

Os testes rodam em modo **headless**. Para ver o navegador abrindo, remova os argumentos `--headless` em `DriverFactory`.

## Relatório Allure

Ao rodar os testes, os resultados são gravados em `target/allure-results`. Para gerar e abrir o relatório localmente (precisa do Node.js):

```bash
npx allure-commandline serve target/allure-results
```

Quando um teste falha, o screenshot do momento do erro:

- é anexado ao teste dentro do relatório Allure;
- é salvo na pasta `screenshots/`.

## Pipeline (GitHub Actions)

O workflow `.github/workflows/regression.yml` executa a regressão:

- a cada push na branch `master`;
- diariamente, às 14:02 UTC (agendamento via cron);
- manualmente, em **Actions → Regression Tests → Run workflow**.

Etapas principais:

1. Configura Java 26 e Node.js.
2. Roda `mvn test`.
3. Gera o relatório Allure a partir de `target/allure-results`.
4. Publica o relatório no **GitHub Pages**.
5. Guarda os screenshots e os resultados do Allure como artefatos da execução.

Para o Pages funcionar, ative em **Settings → Pages → Source → GitHub Actions**. A URL do relatório aparece no resumo de cada execução.

> Se precisar repetir uma execução, prefira **Run workflow** a **Re-run jobs**. O nome do artefato do relatório inclui o número da tentativa para evitar conflitos, mas uma execução nova é o caminho mais seguro.

## Boas práticas adotadas

- **Page Object Model:** cada tela tem sua classe, e os testes não conhecem seletores.
- **Esperas explícitas:** `WebDriverWait` em todas as interações, sem `Thread.sleep`.
- **Dados centralizados:** usuários, produtos e filtros ficam em `TestData`.
- **Testes independentes:** cada teste abre e fecha seu próprio navegador.
- **Evidência de falha:** screenshot automático em caso de erro.

## Observações

- O Chrome pode exibir avisos de senha vazada para o usuário `standard_user`. O `DriverFactory` desativa o gerenciador de senhas e esses avisos, para que não interfiram nos cliques dos testes.
- O aviso `Unable to find an exact match for CDP version` nos logs indica que o Chrome é mais novo que as ferramentas de desenvolvedor do Selenium. Não impede a execução.

## Usuários de teste

Fornecidos pelo próprio Sauce Demo:

| Usuário | Senha | Uso |
|---|---|---|
| `standard_user` | `secret_sauce` | Fluxos principais |
| `locked_out_user` | `secret_sauce` | Cenário de usuário bloqueado |