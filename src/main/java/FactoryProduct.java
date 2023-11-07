import java.util.Date;

public class FactoryProduct {
    public Product createFrozenProduct(String name, double price, int quantity, Date expiryDate){
        return new FrozenProduct(name, price, quantity, expiryDate);
    }

    public Product createVeggyProduct(String name, double price, int quantity, Date expiryDate){
        return new VeggyProduct(name, price, quantity, expiryDate);
    }
}
