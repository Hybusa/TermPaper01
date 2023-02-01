import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Employee[] employees = new Employee[10];
        employees[0] = new Employee("Nicolle", "Galilea", "Hoskins",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));
        employees[1] = new Employee("Tate", "Antony", "Villalobos",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));
        employees[2] = new Employee("Valencia", "Claire", "Alonso",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));
        employees[3] = new Employee("Noa", "Marlene", "Pina",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));
        employees[4] = new Employee("Zariah", "Eunice", "Boswell",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));
        employees[5] = new Employee("Paul", "Nevin", "Bragg",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));
        employees[6] = new Employee("Quinn", "Brandi", "Clemons",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));
        employees[7] = new Employee("Kyler", "Nasir", "Satterfield",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));
        employees[8] = new Employee("Keara", "Selene", "Champion",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));
        employees[9] = new Employee("Elisha", "Ulysses", "Salazar",
                getRandomIntInRange(1,5),getRandomIntInRange(100_000,500_000));

        printAllEmployees(employees);
        System.out.println("total: "+ payrollExpense(employees));
        System.out.println();
        System.out.println("min: "+ minSalary(employees));
        System.out.println("max: "+ maxSalary(employees));
        System.out.println("avarageSalary: "+ avarageSalary(employees));

    }

    public static void printAllEmployees(Employee[] employees) {
        for (Employee employee : employees)
            System.out.println(employee);
    }

    public static int payrollExpense(Employee[] employees) {
        int totalPayrollExpense = 0;
        for (Employee employee : employees) {
            totalPayrollExpense += employee.salary;
        }
        return totalPayrollExpense;
    }

    public static Employee minSalary(Employee[] employees) {
        Employee[] sortedEmployees = Arrays.copyOf(employees, employees.length);
        return sortedEmployees[0];

    }

    public static Employee maxSalary(Employee[] employees) {
        Employee[] sortedEmployees = Arrays.copyOf(employees, employees.length);
        return sortedEmployees[employees.length - 1];
    }

    public static int avarageSalary(Employee[] employees) {
        return payrollExpense(employees) / employees.length;
    }

    public static void fillEmployeeFieldsWithRandoms(Employee[] employees)
    {
        for (Employee employee : employees) {
            employee.salary = getRandomIntInRange(250_000, 500_000);
            employee.department = getRandomIntInRange(1, 5);
        }
    }

    public static int getRandomIntInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}