package co.edu.escuelaing.cvds.lab7.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
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

    @Column(name = "COMPANY")
    public String company;

    @Column(name = "SEX")
    public String sex;


    public Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, String role, Double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.salary = salary;
        this.company = null;
        this.sex = null;
    }

    public Employee(Long employeeId, String firstName, String lastName, String role, Double salary,String company,String sex) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.salary = salary;
        this.company = company;
        this.sex = sex;
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

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getCompany() {
        return company;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", company='" + company + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

