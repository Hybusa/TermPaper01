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
    void removeEmployeeById() {

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
    void changeSalaryByName() {
        testBook.addEmployee(testEmployee);

        assertThat(testBook.changeSalary("Test Joe", 888_888)).isFalse();

        assertThat(testBook.changeSalary("Joe Test", 888_888)).isTrue();

        assertThat(testEmployee.getSalary()).isEqualTo(888_888);
    }

    @org.junit.jupiter.api.Test
    void changeDepartment() {
    }

    @org.junit.jupiter.api.Test
    void changeDepartmentByName() {
    }

    @org.junit.jupiter.api.Test
    void indexSalary() {
    }

    @org.junit.jupiter.api.Test
    void testIndexSalary() {
    }

    @org.junit.jupiter.api.Test
    void getAllEmployees() {
    }

    @org.junit.jupiter.api.Test
    void getPayrollExpense() {
    }

    @org.junit.jupiter.api.Test
    void getMinSalary() {
    }

    @org.junit.jupiter.api.Test
    void testGetMinSalary() {
    }

    @org.junit.jupiter.api.Test
    void getMaxSalary() {
    }

    @org.junit.jupiter.api.Test
    void testGetMaxSalary() {
    }

    @org.junit.jupiter.api.Test
    void getAvarageSalary() {
    }

    @org.junit.jupiter.api.Test
    void testGetAvarageSalary() {
    }

    @org.junit.jupiter.api.Test
    void getEmployeeByID() {
    }

    @org.junit.jupiter.api.Test
    void printEmployeesBelowSalary() {
    }

    @org.junit.jupiter.api.Test
    void printEmployeesAboveSalary() {
    }

    @org.junit.jupiter.api.Test
    void printAllEmployees() {
    }

    @org.junit.jupiter.api.Test
    void testPrintAllEmployees() {
    }
}