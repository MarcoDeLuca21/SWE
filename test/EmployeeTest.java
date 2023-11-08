import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Manager manager;
   private Employee employee;
   private Product product1;
   private Product product2;

   @BeforeEach
    public void setUp(){
       Warehouse warehouse = new Warehouse();
       manager = Manager.getInstance("Mario","Rossi",1,warehouse);
       employee = new Employee("Roberto","Verdi",2,manager,warehouse);
       Date date = new Date(System.currentTimeMillis());
       FactoryProduct factory = new FactoryProduct();
       product1 = factory.createFrozenProduct("ITALPIZZA",5.99,5, date);
       product2 = factory.createVeggyProduct("TOFU",2.49,10,date);
   }

   @Test
    public void testAddProduct(){
       int initialSize = employee.getListProducts().size();
       assertTrue(employee.getListProducts().isEmpty());
       employee.addProduct(product1);
       assertEquals(initialSize+1,employee.getListProducts().size());
       employee.addProduct(product2);
       assertEquals(initialSize+2,employee.getListProducts().size());
   }

   @Test
    public void testRemoveProduct(){
       employee.addProduct(product1);
       employee.addProduct(product2);
       int size = employee.getListProducts().size();
       employee.removeProduct(product1);
       assertFalse(employee.getListProducts().contains(product1));
       assertEquals(size-1,employee.getListProducts().size());
       employee.removeProduct(product2);
       assertFalse(employee.getListProducts().contains(product2));
       assertEquals(size-2,employee.getListProducts().size());
       assertTrue(employee.getListProducts().isEmpty());
   }

   @Test
    public void testModifyPrice(){
       double firstPrice = product1.getPrice();
       employee.modifyPrice(product1,20.99);
       assertNotEquals(firstPrice, product1.getPrice());
   }

   @Test
    public void testModifyQuantity(){
       int firstQuantity = product1.getQuantity();
       employee.modifyQuantity(product1,product1.getQuantity()-1);
       assertNotEquals(firstQuantity,product1.getQuantity());
   }

   @Test
    public void testNotifyProductOutOfStock(){

   }

   @Test
    public void testNotifyExpiredProduct(){

   }
}