import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Manager manager;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void setUp(){
        Warehouse warehouse = new Warehouse();
        manager = Manager.getInstance("Mario","Rossi",1,warehouse);
        employee1 = new Employee("Roberto","Verdi",2,manager,warehouse);
        employee2 = new Employee("Leonardo", "Bianchi",3,manager,warehouse);
    }

    @Test
    public void testAddEmployee(){
        int initialSize = manager.getEmployees().size();
        assertTrue(manager.getEmployees().isEmpty());
        manager.addEmployee(employee1);
        assertTrue(manager.getEmployees().contains(employee1));
        assertEquals(initialSize+1,manager.getEmployees().size());
        manager.addEmployee(employee2);
        assertTrue(manager.getEmployees().contains(employee2));
        assertEquals(initialSize+2,manager.getEmployees().size());
    }

    @Test
    public void testRemoveEmployee(){
        manager.addEmployee(employee1);
        manager.addEmployee(employee2);
        int size = manager.getEmployees().size();
        manager.removeEmployee(employee1);
        assertFalse(manager.getEmployees().contains(employee1));
        assertEquals(size-1,manager.getEmployees().size());
        manager.removeEmployee(employee2);
        assertFalse(manager.getEmployees().contains(employee2));
        assertEquals(size-2,manager.getEmployees().size());
    }

    /*@Test
    public void testNotify(){
        manager.addEmployee(employee1);
        manager.addEmployee(employee2);

        String message = "Messaggio di test";

        manager.notify(employee1,message);

        verify(employee1).update(message);
    }*/

    @Test
    public void testSetWeekSchedule(){
        DayOfWeek day1 = DayOfWeek.MONDAY;
        DayOfWeek day2 = DayOfWeek.TUESDAY;
        DailySchedule dailySchedule1 = new DailySchedule(LocalTime.of(14,00),LocalTime.of(22,00));
        DailySchedule dailySchedule2 = new DailySchedule(LocalTime.of(6,00),LocalTime.of(14,00));

        manager.setWeekSchedule(employee1,day1,dailySchedule1);
        assertEquals(dailySchedule1,employee1.getWeekSchedule().getDailySchedule(day1));
        manager.setWeekSchedule(employee1,day2,dailySchedule2);
        assertEquals(dailySchedule2,employee1.getWeekSchedule().getDailySchedule(day2));
    }
}