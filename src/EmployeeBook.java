import java.util.*;


public class EmployeeBook {
    private final Map<Integer, Employee> employeeHashMap = new HashMap<>();


    public void addEmployee(Employee employee) {
        employeeHashMap.put(employee.getEmployeeID(), employee);
        System.out.println("Employee Added");
    }

    public void removeEmployee(int id) {
        if (employeeHashMap.isEmpty()) {
            System.out.println("The book is Empty");
            return;
        }
        if (employeeHashMap.remove(id, employeeHashMap.get(id)))
            System.out.println("Employee Removed");
        else
            System.out.println("No such employee");
    }

    public void removeEmployee(String fullName) {
        if (removeEmployeeByName(fullName))
            System.out.println("Employee(s) removed");
        else
            System.out.println("No such employee");
    }

    private boolean removeEmployeeByName(String fullName) {

        ArrayList<Integer> ids = new ArrayList<>();
        if (employeeHashMap.isEmpty()) {
            System.out.println("The book is Empty");
            return false;
        }
        if (employeeHashMap.values().stream().anyMatch(employee -> employee.getEmployeeFullName().equals(fullName))) {
            employeeHashMap.values().stream()
                    .filter(employee -> employee.getEmployeeFullName().equals(fullName))
                    .forEach(employee -> ids.add(employee.getEmployeeID()));
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
    public void changeSalaryByName(String fullName, double newSalary) {
        try {
            employeeHashMap.values().stream()
                    .filter(employee -> employee.getEmployeeFullName().equals(fullName))
                    .findAny().orElseThrow(NoSuchElementException::new).setSalary(newSalary);
        } catch (NoSuchElementException e) {
            System.out.println("No such Person, or the name was typed wrong");
        }
    }

    public void changeDepartmentByName(String fullName, Department newDepartment) {
        try {
            employeeHashMap.values().stream()
                    .filter(employee -> employee.getEmployeeFullName().equals(fullName))
                    .findAny().orElseThrow(NoSuchElementException::new).setDepartment(newDepartment);
        } catch (NoSuchElementException e) {
            System.out.println("No such Person, or the name was typed wrong");
        }
    }

    public void indexSalary(int indexPercent) {
        for (Employee employee : employeeHashMap.values()) {
            employee.setSalary(indexSalaryByPercent(employee.getSalary(), indexPercent));
        }
    }

    public void indexSalary(Department department, int indexPercent) {
        indexSalaryForDepartment(department, indexPercent);
    }


    private void indexSalaryForDepartment(Department department, int indexPercent) {
        employeeHashMap.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> employee.setSalary(indexSalaryByPercent(employee.getSalary(), indexPercent)));
    }


    //Get methods
    public double getPayrollExpense() {
        return employeeHashMap.values().stream().mapToDouble(Employee::getSalary).sum();
    }

    public Optional<Employee> getMinSalary() {
        return Optional.ofNullable(employeeHashMap.values().stream()
                .min(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new));
    }

    public Optional<Employee> getMinSalary(Department department) {
        return getMinSalaryFromDepartmentWithStream(department);
    }

    public Optional<Employee> getMaxSalary() {
        return Optional.ofNullable(employeeHashMap.values().stream()
                .max(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new));
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


    private double getAvarageSalaryFromDepartmentWithStream(Department department) {
        if (!employeeHashMap.isEmpty()) {
            double avarage = employeeHashMap.values().stream().filter(employee -> employee.getDepartment() == department)
                    .mapToDouble(Employee::getSalary).sum();
            avarage /= employeeHashMap.values().stream().filter(employee -> employee.getDepartment() == department).count();
            return avarage;
        }
        System.out.println("Book is empty");
        return 0;
    }


    private Optional<Employee> getMinSalaryFromDepartmentWithStream(Department department) {
        return Optional.ofNullable(employeeHashMap.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary)).orElseThrow(NoSuchElementException::new));
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
        if(employeeHashMap.values().stream().anyMatch(employee -> employee.getSalary() < salaryCeiling))
        employeeHashMap.values().stream()
                .filter(employee -> employee.getSalary() < salaryCeiling)
                .forEach(System.out::println);
        else
            System.out.println("No such elements");
    }

    public void printEmployeesAboveSalary(int salaryFloor) {
        if (employeeHashMap.isEmpty()) {
            System.out.println("No employees in the Book");
            return;
        }
        if(employeeHashMap.values().stream().anyMatch(employee -> employee.getSalary() >= salaryFloor))
        employeeHashMap.values().stream()
                .filter(employee -> employee.getSalary() >= salaryFloor)
                .forEach(System.out::println);
        else
            System.out.println("No suck element");
    }

    public void printAllEmployees() {
        for (Employee employee : employeeHashMap.values())
            System.out.println(employee);
    }

    public void printAllEmployees(Department department) {
        printAllEmployeesFromDepartment(department);
    }


    //Privates
    private void printAllEmployeesFromDepartment(Department department) {
        for (Employee employee : employeeHashMap.values()) {
            if (employee.getDepartment() == department)
                System.out.println(employee);
        }
    }

    private double indexSalaryByPercent(double salary, int percent) {
        double index = percent / 100d;
        salary += salary * index;
        return salary;
    }
}


