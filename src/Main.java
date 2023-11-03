import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        FactoryProduct factory = new FactoryProduct();

        Date date = new Date(System.currentTimeMillis());

        Product prodotto1 = factory.createFrozenProduct("A",25.50,10,date);
        Product prodotto2 = factory.createVeggyProduct("B",15.99,15,date);

        Manager manager = Manager.getInstance("Mario","Rossi",4,warehouse);
        Employee employee1 = new Employee("marco","de luca",1, manager,warehouse);
        Employee employee2 = new Employee("roberto","frocio",3,manager,warehouse);
        Employee employee3 = new Employee("leonardo","verdi", 8, manager,warehouse);

        manager.addEmployee(employee1);
        manager.addEmployee(employee2);
        manager.addEmployee(employee3);

        employee2.addProduct(prodotto1);
        employee1.addProduct(prodotto2);

        manager.viewProductsByType(prodotto1.getClass().getSimpleName());

        employee3.viewProductsByType(prodotto2.getClass().getSimpleName());

        employee3.modifyPrice(prodotto1,30);

        manager.notifyAllEmployee("Ciao come state");
    }
}