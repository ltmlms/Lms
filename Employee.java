import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String State;
    private String employeeId;
    private String title;
    private String Department;
    private String salary;
    private String hireDate;

    public Employee(String firstName, String lastName, String phoneNumber, String address, String city, String State,
                    String employeeId, String title, String Department, String salary, String hireDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.State = State;
        this.employeeId = employeeId;
        this.title = title;
        this.Department = Department;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    // Accessor methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return State;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return Department;
    }

    public String getSalary() {
        return salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    // Mutator methods (setters) can be added if needed

    @Override
    public String toString() {
        return "Employee{" +
                "First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Phone Number='" + phoneNumber + '\'' +
                ", Address='" + address + '\'' +
                ", City='" + city + '\'' +
                ", State='" + State + '\'' +
                ", employeeId=" + employeeId + '\'' +
                ", title='" + title + '\'' +
                ", Department='" + Department + '\'' +
                ", salary=" + salary + '\'' +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }

    // @Override
    // public int hashCode() {
    //     // Implement hashCode method based on your requirements
    //     return employeeId;
    // }

    @Override
    public boolean equals(Object obj) {
        // Implement equals method based on your requirements
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employee employee = (Employee) obj;
        return employeeId == employee.employeeId;
    }
}
