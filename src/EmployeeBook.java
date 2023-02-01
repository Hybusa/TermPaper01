import java.util.Arrays;
import java.util.Random;

public class EmployeeBook {
    private Employee[] employees = new Employee[0];

    public void addEmployee(Employee employee) {
        if (isInArray(employee)) {
            System.out.println("Employee is already in the Book\n");
            return;
        }
        employees = Arrays.copyOf(employees, employees.length + 1);
        employees[employees.length - 1] = employee;
        System.out.println("Employee added\n");
    }

    public void removeEmployeeByID(int id) {
        if (employees.length - 1 < 0) {
            System.out.println("Employee Book is Empty\n");
            return;
        }
        if (!isInArrayByID(id)) {
            System.out.println("No such employee\n");
            return;
        }
        Employee[] newEmployees = new Employee[employees.length - 1];
        for (int i = 0, k = 0; i < employees.length; i++) {
            if (employees[i].getEmployeeID() != id) {
                newEmployees[k] = employees[i];
                k++;
            }
        }
        employees = newEmployees;
        System.out.println("Employee removed\n");
    }

    public void removeEmployeeByFullName(String fullName) {
        if (employees.length - 1 < 0) {
            System.out.println("EmployeeBook is Empty\n");
            return;
        }
        if (isNotInArrayByFullName(fullName)) {
            System.out.println("No such employee, or name was wrong\n");
            return;
        }
        Employee[] newEmployees = new Employee[employees.length - 1];
        for (int i = 0, k = 0; i < employees.length; i++) {
            if (!employees[i].getEmployeeFullName().equals(fullName)) {
                newEmployees[k] = employees[i];
                k++;
            }
        }
        employees = newEmployees;
        System.out.println("Employee removed\n");
    }

    public void changeSalaryByName(String fullName, float newSalary) {
        if (isNotInArrayByFullName(fullName)) {
            System.out.println("No such employee, or name was wrong\n");
            return;
        }

        for (Employee employee : employees) {
            if (employee.getEmployeeFullName().equals(fullName)) {
                if (employee.getSalary() == newSalary) {
                    System.out.println("Employee is already has that salary\n");
                    return;
                }
                employee.setSalary(newSalary);
                System.out.println("Employee salary is changed\n");
            }
        }
    }

    public void changeDepartmentByName(String fullName, int newDepartment) {
        if (isNotInArrayByFullName(fullName)) {
            System.out.println("No such employee, or name was wrong\n");
            return;
        }

        for (Employee employee : employees) {
            if (employee.getEmployeeFullName().equals(fullName)) {
                if (employee.getDepartment() == newDepartment) {
                    System.out.println("Employee is already in that department\n");
                    return;
                }
                employee.setDepartment(newDepartment);
                System.out.println("Employee department is changed \n");
            }
        }
    }


    public void printAllEmployees() {
        for (Employee employee : employees)
            System.out.println(employee);
    }

    public float getPayrollExpense() {
        int totalPayrollExpense = 0;
        for (Employee employee : employees) {
            totalPayrollExpense += employee.salary;
        }
        return totalPayrollExpense;
    }

    public Employee getMinSalary() {
        return sortCopyOfArray()[0];
    }

    public Employee getMaxSalary() {
        return sortCopyOfArray()[employees.length - 1];
    }

    public float getAvarageSalary() {
        return getPayrollExpense() / employees.length;
    }

    public void fillEmployeeFieldsWithRandoms() {
        for (Employee employee : employees) {
            employee.salary = getRandomIntInRange(250_000, 500_000);
            employee.department = getRandomIntInRange(1, 5);
        }
    }

    //Second part
    public void indexSalary(int indexPercent) {
        float indexMod = indexPercent / 100f;
        for (Employee employee : employees) {
            employee.salary += employee.salary * indexMod;
        }
    }

/*    public Employee getMinSalaryFromDepartment(int department) {
        Employee[] sortedEmployees = sortCopyOfArray();
        for (Employee employee : sortedEmployees) {
            if (employee.department == department)
                return employee;
        }
        return ;
    }*/

    public Employee getMaxSalaryFromDepartment(int department) {
        Employee[] sortedEmployees = sortCopyOfArray();
        for (int i = sortedEmployees.length - 1; i >= 0; i--) {
            if (sortedEmployees[i].department == department)
                return sortedEmployees[i];
        }
        return null;
    }

    public float getAvarageSalaryFromDepartment(int department) {
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

    public void indexSalaryForDepartment(int department, int indexPercent) {
        float indexMod = indexPercent / 100f;
        for (Employee employee : employees) {
            if (employee.department == department)
                employee.salary += employee.salary * indexMod;
        }
    }

    public void printEmployeesBelowSalary(int salaryCeiling) {
        for (Employee employee : employees) {
            if (employee.salary < salaryCeiling) {
                employee.printIDFullNameSalary();
            }
        }
    }

    public void printEmployeesAboveSalary(int salaryFloor) {
        for (Employee employee : employees) {
            if (employee.salary >= salaryFloor) {
                employee.printIDFullNameSalary();
            }
        }
    }

    public void printAllEmployeesFromDepartment(int department) {
        for (Employee employee : employees) {
            if (employee.department == department)
                System.out.println(employee);
        }
    }

   /* public Employee getEmployeeByID(int id) {
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == id)
                return employee;
        }
        return null;
    }*/

    private int getRandomIntInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private Employee[] sortCopyOfArray() {
        return Arrays.copyOf(employees, employees.length);
    }

    private boolean isInArray(Employee employee) {
        for (Employee obj : employees) {
            if (obj.getEmployeeID() == employee.getEmployeeID())
                return true;
        }
        return false;
    }

    private boolean isInArrayByID(int id) {
        for (Employee obj : employees) {
            if (obj.getEmployeeID() == id)
                return true;
        }
        return false;
    }

    private boolean isNotInArrayByFullName(String fullName) {
        for (Employee obj : employees) {
            if (obj.getEmployeeFullName().equals(fullName))
                return false;
        }
        return true;
    }
}
