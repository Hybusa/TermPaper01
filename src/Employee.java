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

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return String.format("Name: %s %s %s%n ID: %d%n Department: %d%n Salary: %f%n%n",
                this.lastName,
                this.firstName,
                this.middleName,
                this.employeeID,
                this.department,
                this.salary);
    }
}
