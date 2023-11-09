import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Manager manager;
   private Employee employee;
   private Product product1;
   private Product product2;
   private Product product3;
   private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

   @BeforeEach
    public void setUp(){
       Warehouse warehouse = new Warehouse();
       manager = Manager.getInstance("Mario","Rossi",1,warehouse);
       employee = new Employee("Roberto","Verdi",2,manager,warehouse);
       Date date = new Date(System.currentTimeMillis());
       FactoryProduct factory = new FactoryProduct();
       product1 = factory.createFrozenProduct("ITALPIZZA",5.99,5, date);
       product2 = factory.createVeggyProduct("TOFU",2.49,10,date);
       product3 = factory.createVeggyProduct("SEITAN", 3.70,4,date);
   }

   @Test
    public void testAddProduct(){
       int initialSize = employee.getListProducts().size();
       assertTrue(employee.getListProducts().isEmpty());
       employee.addProduct(product1);
       assertEquals(initialSize+1,employee.getListProducts().size());
       assertTrue(employee.getListProducts().contains(product1));
       employee.addProduct(product2);
       assertEquals(initialSize+2,employee.getListProducts().size());
       assertTrue(employee.getListProducts().contains(product2));
       employee.addProduct(product3);
       assertEquals(initialSize+3,employee.getListProducts().size());
       assertTrue(employee.getListProducts().contains(product3));
   }

   @Test
    public void testRemoveProduct(){
       employee.addProduct(product1);
       employee.addProduct(product2);
       employee.addProduct(product3);
       int size = employee.getListProducts().size();
       employee.removeProduct(product1);
       assertFalse(employee.getListProducts().contains(product1));
       assertEquals(size-1,employee.getListProducts().size());
       employee.removeProduct(product2);
       assertFalse(employee.getListProducts().contains(product2));
       assertEquals(size-2,employee.getListProducts().size());
       employee.removeProduct(product3);
       assertFalse(employee.getListProducts().contains(product3));
       assertEquals(size-3,employee.getListProducts().size());
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
      employee.addProduct(product1);
      employee.addProduct(product2);
      product1.setQuantity(0); //set quantità prodotto a 0 (prodotto esaurito)

      System.setOut(new PrintStream(outContent));
      employee.notifyProductOutOfStock();
      String output = outContent.toString();

      // Verifica che l'output contiene il messaggio corretto
      assertTrue(output.contains("Il dipendente "+employee.getName()+" ha notificato che il prodotto "+product1.getName() +" è esaurito"));

      product1.setQuantity(1); //set quantità prodotto a 1
      outContent.reset();

      // Invoca nuovamente il metodo
      employee.notifyProductOutOfStock();
      output = outContent.toString();

      // Verifica che il messaggio non è più presente nell'output
      assertFalse(output.contains("Il dipendente "+employee.getName()+" ha notificato che il prodotto "+product1.getName()+" è esaurito"));
   }

   @Test
    public void testNotifyExpiredProduct(){
       employee.addProduct(product1);
       employee.addProduct(product2);
       Date tomorrow = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
       product2.setExpiryDate(tomorrow); //set data di scadenza del product 2 a domani

       System.setOut(new PrintStream(outContent));
       employee.notifyExpiredProduct(product2.getClass().getSimpleName());
       String output = outContent.toString();

       assertTrue(output.contains("Il dipendente "+employee.getName()+" dice che il prodotto "+product2.getName()+" scade domani, rimuovere dal magazzino!"));

       Date nextWeek = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
       product2.setExpiryDate(nextWeek); //set data di scadenza del product 2 a settimana prossima
       outContent.reset();

       employee.notifyExpiredProduct(product2.getClass().getSimpleName());
       output = outContent.toString();

       assertFalse(output.contains("Il dipendente "+employee.getName()+" dice che il prodotto "+product2.getName()+" scade domani, rimuovere dal magazzino!"));
   }
}