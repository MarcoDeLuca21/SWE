import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private List<Product> productsList = new ArrayList<>();

    public void addProduct(Product product){
        productsList.add(product);
    }

    public void removeProducts(Product product){
        productsList.remove(product);
    }

    public void viewProducts(){
        System.out.println("I prodotti all'interno del magazzino sono: \n");
        for(Product product:productsList){
            product.viewInfo();
        }
    }

    public List<Product> getProducts(){//riguarda
        return productsList;
    }

    public List<Product> getProductsByType(String type){ //riguarda
        List<Product> productTypeList = new ArrayList<>();

        for (Product product:productsList){
            String nameClassProduct = product.getClass().getSimpleName();

            if(type.equals(nameClassProduct)){
                productTypeList.add(product);
            }
        }
        return productTypeList;
    }

    public void viewProductsByType(String type){//riguarda
        System.out.println("I prodotti del reparto "+type+" sono: \n");
        for (Product product:getProductsByType(type)){
            product.viewInfo();
        }
    }
}
