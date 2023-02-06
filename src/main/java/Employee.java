import java.util.Objects;

public class Employee {

    private static int ID;
    private final String firstName;
    private final String lastName;
    private final int employeeID;
    private Department department;
    private double salary;


    public Employee(String fullName, Department department, double salary) {
        String[] names = fullName.split(" ");
        this.firstName = names[0];
        this.lastName = names[1];
        this.department = department;
        this.salary = salary;
        this.employeeID = ID;
        ID++;
    }

    public Employee(Employee other)
    {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.department = other.department;
        this.salary = other.salary;
        this.employeeID = other.employeeID;
    }



    //Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public Department getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmployeeFullName() {
        return String.format("%s %s",
                this.getFirstName(),
                this.getLastName());
    }

    //Setters
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    //Overrides
    @Override
    public String toString() {
        return String.format("Name: %s %s%nID: %d%nDepartment: %s%nSalary: %f%n%n",
                this.lastName,
                this.firstName,
                this.employeeID,
                this.department,
                this.salary);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Employee otherEmployee = (Employee) other;
        return this.hashCode() == otherEmployee.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, firstName, lastName);
    }
}
