import java.util.Objects;

public class Employee {

    private static int ID;
    private final String firstName;
    private final String lastName;
    private final Integer employeeID;
    private Department department;
    double salary;


    public Employee(String fullName, Department department, int salary) {
        String[] names = fullName.split(" ");
        this.firstName = names[0];
        this.lastName = names[1];
        this.department = department;
        this.salary = salary;
        this.employeeID = ID;
        ID++;
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

    public  double getSalary() {
        return salary;
    }

    public String getEmployeeFullName() {
        return String.format("%s %s",
                this.getLastName(),
                this.getFirstName());
    }

    //Setters
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public void printIDFullNameSalary() {
        System.out.printf("%d%n%s%n%f%n",
                this.getEmployeeID(),
                getEmployeeFullName(),
                this.getSalary());
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeID.equals(employee.employeeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, firstName, lastName);
    }
}
