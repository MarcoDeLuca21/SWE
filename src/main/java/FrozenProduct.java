import java.util.Date;

public class FrozenProduct implements Product{
    private String name;
    private double price;
    private int quantity;
    private Date expiryDate;

    public FrozenProduct(String name, double price, int quantity,Date expiryDate){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    @Override//riguarda
    public String getName(){
        return name;
    }

    @Override//riguarda
    public void setName(String name){
        this.name = name;
    }

    @Override//riguarda
    public double getPrice(){
        return price;
    }

    @Override//riguarda
    public void setPrice(double price) {
        this.price = price;
    }

    @Override//riguarda
    public int getQuantity() {
        return quantity;
    }

    @Override//riguarda
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public Date getExpiryDate(){ //riguarda
        return expiryDate;
    }

    @Override
    public void setExpiryDate(Date expiryDate){ //riguarda
        this.expiryDate = expiryDate;
    }

    @Override
    public void viewInfo() {//riguarda
        System.out.println("Prodotto [nome= "+name+" ,prezzo= "+price+" ,quantità= "+quantity+" ,data scadenza: "+expiryDate+" ]");
    }
}
