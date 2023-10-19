package co.edu.escuelaing.cvds.lab7.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "EMPLOYEE_ID")
    public Long employeeId;

    @Column(name = "FIRST_NAME")
    public String firstName;

    @Column(name = "LAST_NAME")
    public String lastName;

    @Column(name = "ROLE")
    public String role;

    @Column(name = "SALARY")
    public Double salary;

    public Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, String role, Double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.salary = salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSalary(String salary) {
        this.salary = Double.valueOf(salary);
    }

    public void setEmployeeId(Long id) {
        this.employeeId = id;
    }

    public Long getEmployeeId() {

        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public Double getSalary() {
        return salary;
    }


    @Override
    public String toString() {
        return "Employee [employeeId = " + employeeId + ", firstName = " + firstName + ", lastName = " + lastName
                + ", role = " + role + ", salary = " + salary + "]";
    }
}

