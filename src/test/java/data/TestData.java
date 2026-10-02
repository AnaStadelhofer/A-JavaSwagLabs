package data;

public class TestData {

    public static final String STANDARD_USER =
            "standard_user";

    public static final String PASSWORD =
            "secret_sauce";

    public static final String LOCKED_USER = "locked_out_user";

    public static final String INVALID_USER = "usuarioInvalido";

    public static final String INVALID_PASSWORD = "senhaInvalida";

    // Products
    public static final Product BACKPACK = new Product(
            "sauce-labs-backpack",
            "Sauce Labs Backpack",
            29.99,
            "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."
    );

    public static final Product BIKE_LIGHT = new Product(
            "sauce-labs-bike-light",
            "Sauce Labs Bike Light",
            9.99,
            "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."
    );

    public static final Product TSHIRT = new Product(
            "sauce-labs-bolt-t-shirt",
            "Sauce Labs Bolt T-Shirt",
            15.99,
            "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt."
    );

    public static final Product JACKET = new Product(
            "sauce-labs-fleece-jacket",
            "Sauce Labs Fleece Jacket",
            49.99,
            "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office."
    );

    public static final Product ONESIE = new Product(
            "sauce-labs-onesie",
            "Sauce Labs Onesie",
            7.99,
            "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel."
    );

    public static final Product TSHIRTRED = new Product(
            "test.allthethings()-t-shirt-(red)",
            "Test.allTheThings() T-Shirt (Red)",
            15.99,
            "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton."
    );

    // Filters

    public static final String FILTER_ATOZ = "az";
    public static final String FILTER_ZTOA = "za";
    public static final String FILTER_LOWTOHIGH = "lohi";
    public static final String FILTER_HIGHTOLOW = "hilo";
}