import java.util.Objects;

public class Employee {

    private static int ID;

    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final int employeeID;

    int department;
    float salary;


    public Employee(String firstName, String middleName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        this.employeeID = ID;
        ID++;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getDepartment() {
        return department;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return String.format("Name: %s %s %s%nID: %d%nDepartment: %d%nSalary: %f%n%n",
                this.lastName,
                this.firstName,
                this.middleName,
                this.employeeID,
                this.department,
                this.salary);
    }

    public void printIDFullNameSalary() {
        System.out.printf("%d%n%s%n%f%n",
                this.getEmployeeID(),
                getEmployeeFullName(),
                this.getSalary());
    }

    public String getEmployeeFullName() {
        return String.format("%s %s %s",
                this.getLastName(),
                this.getFirstName(),
                this.getMiddleName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeID == employee.employeeID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, firstName, lastName);
    }
}
