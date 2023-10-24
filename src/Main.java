import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        FactoryProduct factory = new FactoryProduct();

        Date date = new Date(System.currentTimeMillis());

        Product prodotto1 = factory.createFrozenProduct("A",20.23,10,date);
        Product prodotto2 = factory.createVeggyProduct("B",10.99,12,date);

        prodotto1.viewInfo();
        prodotto2.viewInfo();

        Manager manager = Manager.getInstance("Mario","Rossi",4,warehouse);
        Employee employee1 = new Employee("marco","de luca",1, manager,warehouse);
        Employee employee2 = new Employee("roberto","frocio",3,manager,warehouse);

        manager.addObservable(employee1);
        manager.addObservable(employee2);

        manager.addEmployee(employee1);
        manager.addEmployee(employee2);

        manager.notifyAll("Forse funziona");

        warehouse.addProduct(prodotto1);
        warehouse.addProduct(prodotto2);
        employee1.addProdotto(prodotto2);

        warehouse.viewProducts();

        System.out.println("vedi se funziona");
        System.out.println();

        employee1.modifyPrice(prodotto1,50.99);

        System.out.println();
        System.out.println();

        employee2.modifyQuantity(prodotto2, 32);

        employee2.modifyPrice(prodotto1, 21.01);

        System.out.println();

        manager.viewAllProducts();

        employee1.addProdotto(prodotto1);
        employee1.modifyQuantity(prodotto1,26);
        employee2.addProdotto(prodotto1);
        employee2.addProdotto(prodotto2);
        employee1.modifyQuantity(prodotto1,1);

        warehouse.viewProducts();

        System.out.println();
        manager.viewAllEmployees();

        System.out.println();
        System.out.println("prova riserva scorte");
        System.out.println();

        employee2.modifyQuantity(prodotto1,0);
        employee2.modifyQuantity(prodotto2,10);
        employee1.modifyQuantity(prodotto1,0);
        employee1.notifyProductOutOfStock();
        System.out.println();

        manager.setWeekSchedule(employee1, DayOfWeek.MONDAY, new DailySchedule(LocalTime.of(12,30,00),LocalTime.of(18,30,00)));
        manager.setWeekSchedule(employee1, DayOfWeek.THURSDAY, new DailySchedule(LocalTime.of(15,30,00),LocalTime.of(21,30,00)));
        manager.setWeekSchedule(employee1, DayOfWeek.FRIDAY, new DailySchedule(LocalTime.of(6,30,00),LocalTime.of(12,30,00)));

        manager.setWeekSchedule(employee2, DayOfWeek.MONDAY, new DailySchedule(LocalTime.of(6,00,00),LocalTime.of(13,00,00)));
        manager.setWeekSchedule(employee2, DayOfWeek.TUESDAY, new DailySchedule(LocalTime.of(12,30,00),LocalTime.of(18,30,00)));
        manager.setWeekSchedule(employee2, DayOfWeek.WEDNESDAY, new DailySchedule(LocalTime.of(14,30,00),LocalTime.of(22,30,00)));


        String scheduleEmployee1 = employee1.viewWeekSchedule();
        String scheduleEmployee2 = employee2.viewWeekSchedule();
        System.out.println("orario "+employee1.toString()+" : \n"+scheduleEmployee1);
        System.out.println("orario "+employee2.toString()+" : \n"+scheduleEmployee2);

        manager.viewAllProducts();
        System.out.println();

        System.out.println("prova prodotti per tipo");

        System.out.println();

        manager.viewProductsByType(prodotto2.getClass().getSimpleName());
        System.out.println();
        manager.viewProductsByType(prodotto1.getClass().getSimpleName());

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("prova scadenza prodotti");

        Date oggi = new Date();
        Date datascadenza = new Date(oggi.getTime()+(24 * 60 * 60 * 1000));
        Date secondadata = new Date(oggi.getTime()-(24 * 60 * 60 * 1000));
        prodotto2.setExpiryDate(datascadenza);
        employee2.removeProdotto(prodotto2);
        prodotto1.setExpiryDate(secondadata);
        employee1.notifyExpiredProduct(prodotto1.getClass().getSimpleName());
        employee1.notifyExpiredProduct(prodotto2.getClass().getSimpleName());
        
    }
}