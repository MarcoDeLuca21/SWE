import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private String name;
    private String surname;
    private int id;
    private List<Employee> employees;
    private Warehouse warehouse;
    private static Manager instance;

    private Manager(String name,String surname,int id,Warehouse warehouse){
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.employees = new ArrayList<>();
        this.warehouse = warehouse;
    }

    public static Manager getInstance(String name,String surname,int id,Warehouse warehouse){
        if(instance == null){
            instance = new Manager(name,surname,id,warehouse);
        }
        return instance;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Manager [nome= "+name+" ,surname= "+surname+" ,id= "+id+" ]";
    }

    public void addEmployee(Employee employee){//riguarda chatgpt addOsservatore
        employees.add(employee);
    }

    public void removeEmployee(Employee employee){//riguarda
        employees.remove(employee);
    }

    public List<Employee> getEmployees() {//riguarda
        return employees;
    }

    public void viewAllEmployees(){ //riguarda
        System.out.println("Elenco dipendenti: \n");
        for (Employee employee:employees){
            System.out.println(employee.toString());
        }
    }

    //riguarda metodo
    public void notify(Employee employee, String message){//riguarda
        System.out.println("Il manager "+name+" ha ricevuto il messaggio: "+message+".");
        for (Employee e:employees){
            if(e != employee){
                System.out.println();
                e.update(message);
            }
        }
    }

    public void notifyAllEmployee(String message){//riguarda
        System.out.println("Il manager "+name+" notifica il seguente messaggio a tutti i dipendenti: "+message);
        for (Employee e:employees){
            System.out.println();
            e.update(message);
        }
    }

    public void viewAllProducts(){//riguarda
        warehouse.viewProducts();
    }

    public void viewProductsByType(String type){//riguarda
        warehouse.viewProductsByType(type);
    }

    public void setWeekSchedule(Employee employee, DayOfWeek day,DailySchedule dailySchedule){ //riguarda
        WorkingWeek weekSchedule = employee.getWeekSchedule();
        weekSchedule.setDailySchedule(day,dailySchedule);
    }
}