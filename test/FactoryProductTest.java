import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FactoryProductTest {
    @Test
    public void testCreateFrozenProduct() {
        FactoryProduct factory = new FactoryProduct();
        String name = "ITALPIZZA";
        double price = 5.99;
        int quantity = 10;
        Date expiryDate = new Date();

        Product product = factory.createFrozenProduct(name, price, quantity, expiryDate);

        assertTrue(product instanceof FrozenProduct);
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(quantity, product.getQuantity());
        assertEquals(expiryDate, product.getExpiryDate());
    }

    @Test
    public void testCreateVeggyProduct() {
        FactoryProduct factory = new FactoryProduct();
        String name = "TOFU";
        double price = 3.49;
        int quantity = 7;
        Date expiryDate = new Date();

        Product product = factory.createVeggyProduct(name, price, quantity, expiryDate);

        assertTrue(product instanceof VeggyProduct);
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(quantity, product.getQuantity());
        assertEquals(expiryDate, product.getExpiryDate());
    }

}