# SauceDemo - Automação de Testes

Projeto de automação de testes desenvolvido para praticar e demonstrar conhecimentos em **QA Automation**, utilizando Selenium WebDriver com Java e JUnit 5.

Os testes cobrem funcionalidades como login, produtos, carrinho e checkout, utilizando **Page Object Model (POM)** e dados de teste centralizados.

## 🛠️ Stacks utilizadas

* Java 26
* Selenium WebDriver 4.35.0
* JUnit 5
* Maven
* DataFaker
* IntelliJ IDEA
* Git/GitHub

## 📋 Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

* Java JDK 26+
* Maven
* Google Chrome
* IntelliJ IDEA (opcional)

O Selenium Manager realiza automaticamente o gerenciamento do WebDriver.

## 📁 Estrutura de pastas

```text
src
└── test
    └── java
        ├── base
        │   ├── BasePage.java
        │   └── BaseTest.java
        │
        ├── config
        │   └── Config.java
        │
        ├── data
        │   ├── Product.java
        │   └── TestData.java
        │
        ├── driver
        │   └── DriverFactory.java
        │
        ├── factory
        │   └── PageFactory.java
        │
        ├── pages
        │   ├── LoginPage.java
        │   ├── ProductsPage.java
        │   ├── DetailsProductsPage.java
        │   ├── CartPage.java
        │   └── CheckoutPage.java
        │
        ├── suites
        │   └── RegressionSuite.java
        │
        └── tests
            ├── LoginTest.java
            ├── ProductsTest.java
            ├── CartTest.java
            └── CheckoutTest.java
```
