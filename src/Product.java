import java.util.Date;

public interface Product { //riguarda tutta l'interfaccia
    void viewInfo();

    void setPrice(double price);
    double getPrice();

    void setQuantity(int quantity);
    int getQuantity();

    void setName(String name);
    String getName();

    void setExpiryDate(Date expiryDate);
    Date getExpiryDate();

}
