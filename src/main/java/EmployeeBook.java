import java.util.*;
import java.util.stream.Stream;

public class EmployeeBook {
    private final Map<Integer, Employee> employeeHashMap = new HashMap<>();

    //Adders Creators
    public boolean addEmployee(Employee employee) {

        if (employee == null) {
            System.out.println("Employee can't be equal to null");
            return false;
        }

        employeeHashMap.put(employee.getEmployeeID(), employee);
        System.out.println("Employee Added");

        return true;
    }

    public Employee createEmployeeInTheBook(String fullName, Department department, double salary)
    {
        Employee employee = new Employee(fullName,department,salary);
        this.addEmployee(employee);
        return employee;
    }

    public boolean removeEmployee(Employee employee) {
        if (removeEmployeeById(employee.getEmployeeID())) {
            System.out.println("Employee Removed");
            return true;
        }
        return false;
    }

    public boolean removeEmployee(int id) {
        if (removeEmployeeById(id)) {
            System.out.println("Employee Removed");
            return true;
        }
        return false;
    }

    public boolean removeEmployee(String fullName) {
        if (removeEmployeeByName(fullName)) {
            System.out.println("Employee(s) removed");
            return true;
        }
        return false;
    }

    private boolean removeEmployeeById(int id) {
        if (employeeHashMap.isEmpty()) {
            System.out.println("The book is Empty");
            return false;
        }
        return employeeHashMap.remove(id, employeeHashMap.get(id));
    }

    private boolean removeEmployeeByName(String fullName) {
        if (employeeHashMap.isEmpty()) {
            System.out.println("The book is Empty");
            return false;
        }

        if(!employeeHashMap.entrySet().removeIf(entry -> entry.getValue().getEmployeeFullName().equals(fullName))) {
            System.out.println("No such element");
            return false;
        }
        return true;
    }


    //Change salary by name

    public boolean changeSalary(String fullName, double newSalary) {
        if (changeSalaryByName(fullName, newSalary)) {
            System.out.println("Salary for " + fullName + " changed to " + newSalary);
            return true;
        }
        System.out.println("No such element");
        return false;

    }

    private boolean changeSalaryByName(String fullName, double newSalary) {
        if(checkIfNameIsInTheBook(fullName)) {
            employeeHashMap.values().stream()
                    .filter(employee -> employee.getEmployeeFullName().equals(fullName))
                    .forEach(employee -> employee.setSalary(newSalary));
            return true;
        }
        return false;
    }


    //Change Department

    public boolean changeDepartment(String fullName, Department newDepartment) {
        if (changeDepartmentByName(fullName, newDepartment)) {
            System.out.println("Department for " + fullName + " changed to " + newDepartment);
            return true;
        }
        System.out.println("No such element");
        return false;
    }

    private boolean changeDepartmentByName(String fullName, Department newDepartment) {
       if(checkIfNameIsInTheBook(fullName)) {
           employeeHashMap.values().stream()
                   .filter(employee -> employee.getEmployeeFullName().equals(fullName))
                   .forEach(employee -> employee.setDepartment(newDepartment));
           return true;
       }
        return false;
    }


    //Index salary

    public boolean indexSalary(int indexPercent) {
        if (employeeHashMap.isEmpty()) {
            System.out.println("Book is empty");
            return false;
        }
        for (Employee employee : employeeHashMap.values()) {
            employee.setSalary(indexSalaryByPercent(employee.getSalary(), indexPercent));
        }
        return true;
    }

    public boolean indexSalary(Department department, int indexPercent) {
        if (indexSalaryForDepartment(department, indexPercent)) {
            System.out.println("Salary for department " + department + "was increased by " + indexPercent + "%");
            return true;
        }
        System.out.println("No such element");
        return false;
    }


    private boolean indexSalaryForDepartment(Department department, int indexPercent) {
        if (employeeHashMap.values().stream().anyMatch(employee -> employee.getDepartment() == department)) {
            getDepartmentEmplyeesStream(department)
                    .forEach(employee -> employee.setSalary(indexSalaryByPercent(employee.getSalary(), indexPercent)));
            return true;
        }
        return false;
    }


    //Get info
    public List<Employee> getAllEmployees() {
        if (employeeHashMap.isEmpty()) {
            System.out.println("The book is empty");
        }
        return new ArrayList<>(employeeHashMap.values());
    }

    //Get methods
    public double getPayrollExpense() {
        return employeeHashMap.values().stream().mapToDouble(Employee::getSalary).sum();
    }

    public Optional<Employee> getMinSalary() {
        return employeeHashMap.values().stream()
                .min(Comparator.comparing(Employee::getSalary));
    }
            //Get mins
    public Optional<Employee> getMinSalary(Department department) {
        return getMinSalaryFromDepartmentWithStream(department);
    }

    private Optional<Employee> getMinSalaryFromDepartmentWithStream(Department department) {
        return getDepartmentEmplyeesStream(department)
                .min(Comparator.comparing(Employee::getSalary));
    }

            //Get maxes
    public Optional<Employee> getMaxSalary() {
        return employeeHashMap.values().stream()
                .max(Comparator.comparing(Employee::getSalary));
    }

    public Optional<Employee> getMaxSalary(Department department) {
        return getMaxSalaryEmployeeFromDepartmentWithStream(department);
    }

    private Optional<Employee> getMaxSalaryEmployeeFromDepartmentWithStream(Department department) {
        return getDepartmentEmplyeesStream(department)
                .max(Comparator.comparing(Employee::getSalary));
    }


            //Get Averages
    public double getAvarageSalary() {
        if (!employeeHashMap.isEmpty()) {
            return getPayrollExpense() / employeeHashMap.size();
        }
        return 0;
    }

    public double getAvarageSalary(Department department) {
        if (!employeeHashMap.isEmpty()) {
            return getAvarageSalaryFromDepartmentWithStream(department);
        }
        return 0;
    }

    private Double getAvarageSalaryFromDepartmentWithStream(Department department) {
        if (employeeHashMap.isEmpty()) {
            System.out.println("Book is empty");
            return 0d;
        }
        OptionalDouble avarage = getDepartmentEmplyeesStream(department)
                .mapToDouble(Employee::getSalary).average();
        if (avarage.isEmpty()) {
            return 0d;
        }
        return avarage.getAsDouble();
    }


        //Other

    public Optional<Employee> getEmployeeByID(int id) {
        return Optional.ofNullable(employeeHashMap.get(id));
    }

    //Printers
    public void printEmployeesBelowSalary(int salaryCeiling) {
        if (employeeHashMap.isEmpty()) {
            System.out.println("No employees in the Book");
            return;
        }
        employeeHashMap.values().stream()
                .filter(employee -> employee.getSalary() < salaryCeiling)
                .forEach(System.out::println);

    }

    public void printEmployeesAboveSalary(int salaryFloor) {
        if (employeeHashMap.isEmpty()) {
            System.out.println("No employees in the Book");
            return;
        }
        employeeHashMap.values().stream()
                .filter(employee -> employee.getSalary() >= salaryFloor)
                .forEach(System.out::println);
    }

    public void printAllEmployees() {
        if (employeeHashMap.isEmpty()) {
            System.out.println("No employees in the Book");
            return;
        }
        for (Employee employee : employeeHashMap.values())
            System.out.println(employee);
    }

    public void printAllEmployees(Department department) {
        if (employeeHashMap.isEmpty()) {
            System.out.println("No employees in the Book");
            return;
        }
        printAllEmployeesFromDepartment(department);
    }

    private void printAllEmployeesFromDepartment(Department department) {
        getDepartmentEmplyeesStream(department)
                .forEach(System.out::println);
    }


    //Tools
    private boolean checkIfNameIsInTheBook(String name)
    {
       return employeeHashMap.values().stream().anyMatch(employee -> employee.getEmployeeFullName().equals(name));
    }


    private  Stream<Employee> getDepartmentEmplyeesStream(Department department) {
        return employeeHashMap.values().stream().filter(employee -> employee.getDepartment() == department);
    }


    private double indexSalaryByPercent(double salary, int percent) {
        double index = percent / 100d;
        salary += salary * index;
        return salary;
    }
}


