package com.revature.rms.employee.entities;

import com.revature.rms.core.metadata.*;
import com.revature.rms.employee.dtos.EmployeeDto;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Employee extends Resource{

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false,unique=true)
    private String email;

    @Column(nullable=false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Department department;

    public Employee() {super();}

    public Employee(String firstName) {this.firstName = firstName;}

    public Employee(String firstName, String lastName, String email, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
    }

    public Employee(String firstName, String lastName, String email, String title, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.department = department;
    }

    public Employee(String firstName, String lastName, String email, String title, ResourceMetadata resourceMetadata) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.setResourceMetadata(resourceMetadata);
    }

    public Employee(String firstName, String lastName, String email, String title, Department department, ResourceMetadata resourceMetadata) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.department = department;
        this.setResourceMetadata(resourceMetadata);
    }

    public Employee(int id, String firstName, String lastName, String email, String title, Department department, ResourceMetadata resourceMetadata) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.department = department;
        this.setResourceMetadata(resourceMetadata);
    }

    public Employee(EmployeeDto employeeDto) {
        this.id = employeeDto.getId();
        this.firstName = employeeDto.getFirstName();
        this.lastName = employeeDto.getLastName();
        this.email = employeeDto.getEmail();
        this.title = employeeDto.getTitle();
        this.department = employeeDto.getDepartment();
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public Department getDepartment() {return department;}

    public void setDepartment(Department department) {this.department = department;}

    @Override
    public ResourceMetadata getResourceMetadata() {return resourceMetadata;}

    @Override
    public void setResourceMetadata(ResourceMetadata resourceMetadata) {
        this.resourceMetadata = resourceMetadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(title, employee.title) &&
                department == employee.department &&
                Objects.equals(this.getResourceMetadata(), employee.getResourceMetadata());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, title, department, this.getResourceMetadata());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", department=" + department +
                ", resourceMetadata=" + this.getResourceMetadata() +
                '}';
    }
}
