import java.util.*;

public class EmployeeBook {
    private final Map<Integer, Employee> employeeHashMap = new HashMap<>();


    public boolean addEmployee(Employee employee) {

        if (employee == null) {
            System.out.println("Employee can't be equal to null");
            return false;
        }

        employeeHashMap.put(employee.getEmployeeID(), employee);
        System.out.println("Employee Added");

        return true;
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

        ArrayList<Integer> ids = new ArrayList<>();
        if (employeeHashMap.isEmpty()) {
            System.out.println("The book is Empty");
            return false;
        }
        try {
            if (employeeHashMap.values().stream().anyMatch(employee -> employee.getEmployeeFullName().equals(fullName))) {
                employeeHashMap.values().stream()
                        .filter(employee -> employee.getEmployeeFullName().equals(fullName))
                        .forEach(employee -> ids.add(employee.getEmployeeID()));
            }
        } catch (NoSuchElementException e) {
            System.out.println("No such element " + e.getLocalizedMessage());
            return false;
        }
        if (ids.size() == 0)
            return false;
        for (int i : ids) {
            if (!employeeHashMap.remove(i, employeeHashMap.get(i)))
                return false;
        }
        return true;
    }


    //Changers

    public boolean changeSalary(String fullName, double newSalary) {
        if (changeSalaryByName(fullName, newSalary)) {
            System.out.println("Salary for " + fullName + " changed to " + newSalary);
            return true;
        }
        return false;

    }

    public boolean changeDepartment(String fullName, Department newDepartment) {
        if (changeDepartmentByName(fullName, newDepartment)) {
            System.out.println("Department for " + fullName + " changed to " + newDepartment);
            return true;
        }
        return false;
    }


    private boolean changeSalaryByName(String fullName, double newSalary) {
        try {
            employeeHashMap.values().stream()
                    .filter(employee -> employee.getEmployeeFullName().equals(fullName))
                    .findAny().orElseThrow(NoSuchElementException::new).setSalary(newSalary);
        } catch (NoSuchElementException e) {
            System.out.println("No such element" + e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    private boolean changeDepartmentByName(String fullName, Department newDepartment) {
        try {
            employeeHashMap.values().stream()
                    .filter(employee -> employee.getEmployeeFullName().equals(fullName))
                    .findAny().orElseThrow(NoSuchElementException::new).setDepartment(newDepartment);
        } catch (NoSuchElementException e) {
            System.out.println("No such element" + e.getLocalizedMessage());
            return false;
        }
        return true;
    }

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
        return false;
    }


    private boolean indexSalaryForDepartment(Department department, int indexPercent) {
        if (employeeHashMap.values().stream().anyMatch(employee -> employee.getDepartment() == department)) {
            employeeHashMap.values().stream()
                    .filter(employee -> employee.getDepartment() == department)
                    .forEach(employee -> employee.setSalary(indexSalaryByPercent(employee.getSalary(), indexPercent)));
            System.out.println("No such element");
            return true;
        }
        return false;
    }

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

    public Optional<Employee> getMinSalary(Department department) {
        return getMinSalaryFromDepartmentWithStream(department);
    }

    public Optional<Employee> getMaxSalary() {
        return employeeHashMap.values().stream()
                .max(Comparator.comparing(Employee::getSalary));
    }

    public Optional<Employee> getMaxSalary(Department department) {
        return getMaxSalaryEmployeeFromDepartmentWithStream(department);
    }

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
        OptionalDouble avarage = employeeHashMap.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary).average();
        if (avarage.isEmpty()) {
            return 0d;
        }
        return avarage.getAsDouble();
    }


    private Optional<Employee> getMinSalaryFromDepartmentWithStream(Department department) {
        return employeeHashMap.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary));
    }

    private Optional<Employee> getMaxSalaryEmployeeFromDepartmentWithStream(Department department) {
        return employeeHashMap.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary));
    }


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


    //Privates
    private void printAllEmployeesFromDepartment(Department department) {
        employeeHashMap.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .forEach(System.out::println);
    }

    private double indexSalaryByPercent(double salary, int percent) {
        double index = percent / 100d;
        salary += salary * index;
        return salary;
    }
}


