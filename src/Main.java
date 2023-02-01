import java.util.Random;

public class Main {
    public static void main(String[] args) {

        EmployeeBook book1 = new EmployeeBook();
        Employee Sariah = new Employee("Sariah", "Linsey", "Beam", getRandomIntInRange(1, 6),
                getRandomIntInRange(100_000, 500_000));
        Employee Jacob = new Employee("Jacob", "Haden", "Beebe", getRandomIntInRange(1, 6),
                getRandomIntInRange(100_000, 500_000));
        Employee Jami = new Employee("Jami", "Linsey", "Bernier", getRandomIntInRange(1, 6),
                getRandomIntInRange(100_000, 500_000));
        Employee Sebastian = new Employee("Sebastian", "Sherman", "Fraley", getRandomIntInRange(1, 6),
                getRandomIntInRange(100_000, 500_000));
        Employee Star = new Employee("Star", "Karyme", "Tanner", getRandomIntInRange(1, 6),
                getRandomIntInRange(100_000, 500_000));

        book1.addEmployee(Sariah);
        book1.addEmployee(Jacob);
        book1.addEmployee(Jami);
        book1.addEmployee(Sebastian);
        book1.addEmployee(Star);

        book1.removeEmployeeByID(Sariah.getEmployeeID());
        book1.removeEmployeeByID(Star.getEmployeeID());
        book1.removeEmployeeByID(0);

        book1.removeEmployeeByFullName("Tanner Star Karyme");
        book1.removeEmployeeByFullName("Fraley Sebastian Sherman");

        book1.printAllEmployees();

        book1.changeDepartmentByName(Jami.getEmployeeFullName(), 3);
        book1.changeSalaryByName(Jami.getEmployeeFullName(), 180_000);

        book1.printAllEmployees();

    }

    public static int getRandomIntInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}