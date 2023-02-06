import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class EmployeeBookTest {
    EmployeeBook testBook = new EmployeeBook();
    Employee testEmployee = new Employee("Joe Test", Department.DESIGN, 200_000);

    @org.junit.jupiter.api.Test
    void testAddEmployee() {

        assertThat(testBook.addEmployee(testEmployee)).isTrue();

        assertThat(testBook.getEmployeeByID(testEmployee.getEmployeeID()).isPresent()).isTrue();

        assertThat(testBook.addEmployee(null)).isFalse();
    }

    @org.junit.jupiter.api.Test
    void testCreateEmployeeInTheBook()
    {
        Employee employee = testBook.createEmployeeInTheBook(testEmployee.getEmployeeFullName(),
                testEmployee.getDepartment(),
                testEmployee.getSalary());
        assertThat(testBook.getAllEmployees().get(0)).isEqualTo(employee);
        assertThat(employee.getEmployeeFullName()).isEqualTo(testEmployee.getEmployeeFullName());
        assertThat(employee.getDepartment()).isEqualTo(testEmployee.getDepartment());
        assertThat(employee.getSalary()).isEqualTo(testEmployee.getSalary());
    }

    @org.junit.jupiter.api.Test
    void testRemoveEmployeeById() {

        testBook.addEmployee(testEmployee);

        assertThat(testBook.removeEmployee(132156)).isFalse();

        assertThat(testBook.removeEmployee(testEmployee.getEmployeeID())).isTrue();

        assertThat(testBook.removeEmployee(testEmployee.getEmployeeID())).isFalse();
    }

    @org.junit.jupiter.api.Test
    void testRemoveEmployeeByFullName() {

        testBook.addEmployee(testEmployee);

        assertThat(testBook.removeEmployee("Test Joe")).isFalse();

        assertThat(testBook.removeEmployee("Joe Test")).isTrue();

        assertThat(testBook.removeEmployee("")).isFalse();

        assertThat(testBook.removeEmployee("Joe Test")).isFalse();

    }

    @org.junit.jupiter.api.Test
    void testRemoveEmployeeByObject() {

        testBook.addEmployee(testEmployee);

        assertThat(testBook.removeEmployee(testEmployee)).isTrue();
    }

    @org.junit.jupiter.api.Test
    void testChangeSalaryByName() {
        testBook.addEmployee(testEmployee);

        assertThat(testEmployee.getSalary()).isEqualTo(200_000);

        assertThat(testBook.changeSalary("Test Joe", 888_888)).isFalse();

        assertThat(testBook.changeSalary("Joe Test", 888_888)).isTrue();

        assertThat(testEmployee.getSalary()).isEqualTo(888_888);
    }

    @org.junit.jupiter.api.Test
    void testChangeDepartmentByName() {
        testBook.addEmployee(testEmployee);


        assertThat(testEmployee.getDepartment()).isEqualTo(Department.DESIGN);

        assertThat(testBook.changeDepartment("Test Joe", Department.MANAGEMENT)).isFalse();

        assertThat(testEmployee.getDepartment()).isNotEqualTo(Department.MANAGEMENT);

        assertThat(testBook.changeDepartment("Joe Test", Department.MANAGEMENT)).isTrue();

        assertThat(testEmployee.getDepartment()).isEqualTo(Department.MANAGEMENT);

    }

    @org.junit.jupiter.api.Test
    void testIndexSalaryForAll() {

        assertThat(testBook.indexSalary(15)).isFalse();

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Test Jane", Department.MANAGEMENT, 210_000));
        testBook.addEmployee(new Employee("Test Lee", Department.DEVELOPMENT, 220_000));
        testBook.addEmployee(new Employee("Test Veronika", Department.DEVELOPMENT, 230_000));
        testBook.addEmployee(new Employee("Test Emily", Department.CONTENT, 240_000));
        testBook.addEmployee(new Employee("Test Fox", Department.PRODUCTION, 250_000));
        testBook.addEmployee(new Employee("Test Alex", Department.RELEASE, 260_000));

       List<Employee> optionalEmployees = testBook.getAllEmployees();
        ArrayList<Employee> employeesBefore = new ArrayList<>(optionalEmployees);
        double[] salariesBefore = new double[employeesBefore.size()];
        for(int i = 0; i < employeesBefore.size(); i++)
        {
            salariesBefore[i] = employeesBefore.get(i).getSalary();
        }

        assertThat(testBook.indexSalary(12)).isTrue();

        optionalEmployees = testBook.getAllEmployees();
        ArrayList<Employee> employeesAfter = new ArrayList<>(optionalEmployees);

        double[] salariesAfter = new double[employeesAfter.size()];
        for(int i = 0; i < employeesAfter.size(); i++)
        {
            salariesAfter[i] = employeesAfter.get(i).getSalary();
        }

        for(int i = 0; i < employeesBefore.size(); i++)
        {
            assertThat(salariesAfter[i]).isEqualTo(salariesBefore[i]+salariesBefore[i]*(12/100d));
        }
    }

    @org.junit.jupiter.api.Test
    void testIndexSalaryByDepartment() {

        assertThat(testBook.indexSalary(15)).isFalse();

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 210_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 220_000));
        testBook.addEmployee(new Employee("Veronika Test", Department.DEVELOPMENT, 230_000));
        testBook.addEmployee(new Employee("Emily Test", Department.CONTENT, 240_000));
        testBook.addEmployee(new Employee("Fox Test", Department.PRODUCTION, 250_000));
        testBook.addEmployee(new Employee("Alex Test", Department.RELEASE, 260_000));

        List<Employee> optionalEmployees = testBook.getAllEmployees();
        ArrayList<Employee> employeesBefore = new ArrayList<>(optionalEmployees);

        double[] salariesBefore = new double[employeesBefore.size()];
        for(int i = 0; i < employeesBefore.size(); i++)
        {
            salariesBefore[i] = employeesBefore.get(i).getSalary();
        }

        assertThat(testBook.indexSalary(Department.DEVELOPMENT,12)).isTrue();

        optionalEmployees = testBook.getAllEmployees();
        ArrayList<Employee> employeesAfter = new ArrayList<>(optionalEmployees);

        double[] salariesAfter = new double[employeesAfter.size()];
        for(int i = 0; i < employeesAfter.size(); i++)
        {
            salariesAfter[i] = employeesAfter.get(i).getSalary();
        }

        for(int i = 0; i < employeesBefore.size(); i++)
        {
            if(employeesBefore.get(i).getDepartment() == Department.DEVELOPMENT)
                assertThat(salariesAfter[i]).isEqualTo(salariesBefore[i]+salariesBefore[i]*(12/100d));
            else
                assertThat(salariesAfter[i]).isEqualTo(salariesBefore[i]);
        }

        testBook.removeEmployee("Veronika Test");
        testBook.removeEmployee("Lee Test");

        assertThat(testBook.indexSalary(Department.DEVELOPMENT,12)).isFalse();

    }

    @org.junit.jupiter.api.Test
    void testGetAllEmployees() {

        assertThat(testBook.getAllEmployees()).isEmpty();


        Employee Jane = new Employee("Jane Test", Department.MANAGEMENT, 210_000);
        Employee Lee = new Employee("Lee Test", Department.DEVELOPMENT, 220_000);
        Employee Veronika = new Employee("Veronika Test", Department.DEVELOPMENT, 230_000);
        Employee Emily = new Employee("Emily Test", Department.CONTENT, 240_000);
        Employee Fox = new Employee("Fox Test", Department.PRODUCTION, 250_000);
        Employee Alex = new Employee("Alex Test", Department.RELEASE, 260_000);


        testBook.addEmployee(testEmployee);
        testBook.addEmployee(Jane);
        testBook.addEmployee(Lee);
        testBook.addEmployee(Veronika);
        testBook.addEmployee(Emily);
        testBook.addEmployee(Fox);
        testBook.addEmployee(Alex);


        ArrayList<Employee> newListEmployees = new ArrayList<>();
        newListEmployees.add(testEmployee);
        newListEmployees.add(Jane);
        newListEmployees.add(Lee);
        newListEmployees.add(Veronika);
        newListEmployees.add(Emily);
        newListEmployees.add(Fox);
        newListEmployees.add(Alex);

        ArrayList<Employee> gotListEmployees = new ArrayList<>(testBook.getAllEmployees());

        for (Employee newEmployee : newListEmployees) {
            Employee tmp = new Employee(newEmployee);
            assertThat(gotListEmployees.stream().anyMatch(employee -> employee.equals(tmp))).isTrue();
        }
    }

    @org.junit.jupiter.api.Test
    void testGetPayrollExpense() {

        assertThat(testBook.getPayrollExpense()).isEqualTo(0);

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 210_000));

        assertThat(testBook.getPayrollExpense()).isEqualTo(410_000);
    }

    @org.junit.jupiter.api.Test
    void testGetMinSalary() {

        assertThat(testBook.getMinSalary().isPresent()).isFalse();

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 210_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 220_000));

        assertThat(testBook.getMinSalary().isPresent()).isTrue();

        assertThat(testBook.getMinSalary().get().getSalary()).isEqualTo(200_000);
    }

    @org.junit.jupiter.api.Test
    void testGetMinSalaryByDepartment() {

        assertThat(testBook.getMinSalary().isPresent()).isFalse();

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.DESIGN, 310_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 220_000));

        assertThat(testBook.getMinSalary(Department.DESIGN).isPresent()).isTrue();

        assertThat(testBook.getMinSalary(Department.DESIGN).get().getSalary()).isEqualTo(200_000);
    }

    @org.junit.jupiter.api.Test
    void testGetMaxSalary() {
        assertThat(testBook.getMaxSalary().isPresent()).isFalse();

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 210_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 220_000));

        assertThat(testBook.getMaxSalary().isPresent()).isTrue();

        assertThat(testBook.getMaxSalary().get().getSalary()).isEqualTo(220_000);
    }

    @org.junit.jupiter.api.Test
    void testGetMaxSalaryByDepartment() {
        assertThat(testBook.getMaxSalary().isPresent()).isFalse();

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.DESIGN, 310_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 220_000));

        assertThat(testBook.getMaxSalary(Department.DESIGN).isPresent()).isTrue();

        assertThat(testBook.getMaxSalary(Department.DESIGN).get().getSalary()).isEqualTo(310_000);
    }

    @org.junit.jupiter.api.Test
    void testGetAvarageSalary() {
        assertThat(testBook.getAvarageSalary()).isEqualTo(0);

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 210_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 220_000));

        assertThat(testBook.getAvarageSalary()).isEqualTo(210_000);
    }
    @org.junit.jupiter.api.Test
    void testGetAvarageSalaryByDepartment() {
        assertThat(testBook.getAvarageSalary()).isEqualTo(0);

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.DEVELOPMENT, 240_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 220_000));

        assertThat(testBook.getAvarageSalary(Department.DEVELOPMENT)).isEqualTo(230_000);
    }


    @org.junit.jupiter.api.Test
    void testGetEmployeeByID() {
        assertThat(testBook.getEmployeeByID(0).isPresent()).isFalse();

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 210_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 220_000));

        assertThat(testBook.getEmployeeByID(testEmployee.getEmployeeID()).isPresent()).isTrue();
        assertThat(testBook.getEmployeeByID(testEmployee.getEmployeeID()).get()).isEqualTo(testEmployee);

    }

    @org.junit.jupiter.api.Test
    void testPrintEmployeesBelowSalary() {
        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 300_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 500_000));

        testBook.printEmployeesBelowSalary(400_000);
    }

    @org.junit.jupiter.api.Test
    void testPrintEmployeesAboveSalary() {
        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 300_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 500_000));

        testBook.printEmployeesAboveSalary(400_000);
    }

    @org.junit.jupiter.api.Test
    void testPrintAllEmployees() {

        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 300_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 500_000));

        testBook.printAllEmployees();
    }

    @org.junit.jupiter.api.Test
    void testPrintAllEmployeesByDepartment() {
        testBook.addEmployee(testEmployee);
        testBook.addEmployee(new Employee("Jane Test", Department.MANAGEMENT, 300_000));
        testBook.addEmployee(new Employee("Lee Test", Department.DEVELOPMENT, 500_000));

        testBook.printAllEmployees(Department.DEVELOPMENT);
    }

}