import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Employee[] employees = new Employee[10];
        employees[0] = new Employee("Nicolle", "Galilea", "Hoskins",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));
        employees[1] = new Employee("Tate", "Antony", "Villalobos",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));
        employees[2] = new Employee("Valencia", "Claire", "Alonso",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));
        employees[3] = new Employee("Noa", "Marlene", "Pina",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));
        employees[4] = new Employee("Zariah", "Eunice", "Boswell",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));
        employees[5] = new Employee("Paul", "Nevin", "Bragg",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));
        employees[6] = new Employee("Quinn", "Brandi", "Clemons",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));
        employees[7] = new Employee("Kyler", "Nasir", "Satterfield",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));
        employees[8] = new Employee("Keara", "Selene", "Champion",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));
        employees[9] = new Employee("Elisha", "Ulysses", "Salazar",
                getRandomIntInRange(1, 6), getRandomIntInRange(100_000, 500_000));

        printAllEmployees(employees);
        System.out.println("total: " + getPayrollExpense(employees));
        System.out.println();
        System.out.println("min: " + getMinSalary(employees));
        System.out.println("max: " + getMaxSalary(employees));
        System.out.println("avarageSalary: " + getAvarageSalary(employees));
        System.out.println();

        indexSalary(employees, 11);

        printAllEmployees(employees);
        System.out.println("min from dep 3: " + getMinSalaryFromDepartment(employees, 3));
        System.out.println("max from dep 1: " + getMaxSalaryFromDepartment(employees, 1));
        System.out.println("avarage from department 2: " + getAvarageSalaryFromDepartment(employees, 2));
        System.out.println();
        indexSalaryForDepartment(employees, 4, 15);
        printAllEmployeesFromDepartment(employees, 5);

        System.out.println("Below 200000: ");
        printEmployeesBelow(employees, 200_000);
        System.out.println("Above 200000: ");
        printEmployeesAbove(employees, 200_000);


    }

    public static void printAllEmployees(Employee[] employees) {
        for (Employee employee : employees)
            System.out.println(employee);
    }

    public static float getPayrollExpense(Employee[] employees) {
        int totalPayrollExpense = 0;
        for (Employee employee : employees) {
            totalPayrollExpense += employee.salary;
        }
        return totalPayrollExpense;
    }

    public static Employee getMinSalary(Employee[] employees) {
        return sortCopyOfArray(employees)[0];
    }

    public static Employee getMaxSalary(Employee[] employees) {
        return sortCopyOfArray(employees)[employees.length - 1];
    }

    public static float getAvarageSalary(Employee[] employees) {
        return getPayrollExpense(employees) / employees.length;
    }

    public static void fillEmployeeFieldsWithRandoms(Employee[] employees) {
        for (Employee employee : employees) {
            employee.salary = getRandomIntInRange(250_000, 500_000);
            employee.department = getRandomIntInRange(1, 5);
        }
    }

    //Second part
    public static void indexSalary(Employee[] employees, int indexPercent) {
        float indexMod = indexPercent / 100f;
        for (Employee employee : employees) {
            employee.salary += employee.salary * indexMod;
        }
    }

    public static Employee getMinSalaryFromDepartment(Employee[] employees, int department) {
        Employee[] sortedEmployees = sortCopyOfArray(employees);
        for (Employee employee : sortedEmployees) {
            if (employee.department == department)
                return employee;
        }
        return null;
    }

    public static Employee getMaxSalaryFromDepartment(Employee[] employees, int department) {
        Employee[] sortedEmployees = sortCopyOfArray(employees);
        for (int i = sortedEmployees.length - 1; i >= 0; i--) {
            if (sortedEmployees[i].department == department)
                return sortedEmployees[i];
        }
        return null;
    }

    public static float getAvarageSalaryFromDepartment(Employee[] employees, int department) {
        float avarage = 0;
        int counter = 0;
        for (Employee employee : employees) {
            if (employee.department == department) {
                avarage += employee.salary;
                counter++;
            }
        }
        return avarage / counter;
    }

    public static void indexSalaryForDepartment(Employee[] employees, int department, int indexPercent) {
        float indexMod = indexPercent / 100f;
        for (Employee employee : employees) {
            if (employee.department == department)
                employee.salary += employee.salary * indexMod;
        }
    }

    public static void printEmployeesBelow(Employee[] employees, int salaryCeiling)
    {
        for(Employee employee:employees)
        {
            if(employee.salary < salaryCeiling)
            {
                printIDFullNameSalary(employee);
            }
        }
    }

    public static void printEmployeesAbove(Employee[] employees, int salaryFloor)
    {
        for(Employee employee:employees)
        {
            if(employee.salary >= salaryFloor)
            {
                printIDFullNameSalary(employee);
            }
        }
    }

    public static void printIDFullNameSalary(Employee employee){
        System.out.printf("%d%n%s%n%f%n",
                employee.getEmployeeID(),
                getEmployeeFullName(employee),
                employee.getSalary());
    }
    public static String getEmployeeFullName(Employee employee)
    {
        return String.format("%s %s %s", employee.getLastName(),employee.getFirstName(),employee.getMiddleName());
    }

    public static int getRandomIntInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void printAllEmployeesFromDepartment(Employee[] employees, int department) {
        for (Employee employee : employees) {
            if (employee.department == department)
                System.out.println(employee);
        }
    }
    private static Employee[] sortCopyOfArray(Employee[] employees) {
        return Arrays.copyOf(employees, employees.length);
    }
}