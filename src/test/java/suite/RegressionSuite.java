package suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import tests.CartTest;
import tests.CheckoutTest;
import tests.LoginTest;
import tests.ProductsTest;
@Suite
@SelectClasses({
        LoginTest.class,
        ProductsTest.class,
        CartTest.class,
        CheckoutTest.class
})
public class RegressionSuite {
}