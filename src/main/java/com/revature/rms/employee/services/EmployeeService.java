package com.revature.rms.employee.services;

import com.revature.rms.core.metadata.*;
import com.revature.rms.employee.dtos.EmployeeDto;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.core.exceptions.InvalidRequestException;
import com.revature.rms.core.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Uses the EmployeeRepository
 *
 * Methods:
 *      Constructor:
 *          public EmployeeService(EmployeeRepository repo)
 *
 *      public Employee getEmployeeById(int id) throws ResourceNotFoundException
 *      public List<Employee> findEmployeeByOwnerId(int id)
 *      public Employee update(EmployeeCreds updatedEmp, int id)
 *      public Employee save(EmployeeCreds newEmployee, int id)
 *      public Employee findByFirstname(String name) throws ResourceNotFoundException
 *      public List<Employee> findAll() throws ResourceNotFoundException
 *      public static <T> List<T> getListFromIterator(Iterable<T> iterable)
 *      public void delete(int id)
 *
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository repo) {
        super();
        this.employeeRepository = repo;
    }

    /**
     * save method: Takes in a employee object as the input. It is then added or persisted
     * to the database.
     *
     * @param newEmployee newly persisted employee object
     * @return the newly added employee object
     */
    @Transactional
    public Employee save(EmployeeDto newEmployee, int id) {
        if(newEmployee == null){
            throw new InvalidRequestException("New employee cannot be null!");
        }
        Employee employee = new Employee(newEmployee);
        return employeeRepository.save(employee);
    }

    /**
     * findAll method: Returns a list of all the employee objects in the database.
     * @return a list of all the employees
     *
     * @throws ResourceNotFoundException when no employees are found
     */
    @Transactional(readOnly = true)
    public List<Employee> findAll() throws ResourceNotFoundException{
        Iterable<Employee> e = employeeRepository.findAll();
        List<Employee> list = getListFromIterator(e);
        if (list.isEmpty()){
            throw new ResourceNotFoundException("No employees Found!");
        }
        return list;
    }

    /**
     * getEmployeeById method: Returns an employee object when the id int matches a record in the database.
     *
     * @param id employeeId int value
     * @return an employee with matching id
     * @throws ResourceNotFoundException when an employee is not found
     */
    @Transactional(readOnly = true)
    public Employee findById(int id) throws ResourceNotFoundException{
        if (id <= 0){
            throw new InvalidRequestException("ID cannot be less than or equal to zero!");
        }
        Employee employee = employeeRepository.findById(id);
        if (employee == null){
            throw new ResourceNotFoundException("No employee found with the ID: " + id);
        }
        return employee;
    }

    /**
     * findByFirstName method: Returns an employee object when the firstName String matches a record in the database.
     * @param name employeeFirstName String value
     *
     * @return an employee with matching firstName
     * @throws ResourceNotFoundException when an employee is not found
     */
    @Transactional(readOnly = true)
    public Employee findByFirstname(String name) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findByFirstName(name);
        if (employee == null){
            throw new ResourceNotFoundException("No employee found with first name: " + name);
        }
        return employee;
    }

    /**
     * findEmployeeByOwnerId method: Retrieves a list of employees based on boss' ID
     *
     * @param id Boss' ID
     * @return List of employees
     */
    @Transactional(readOnly = true)
    public List<Employee> findByOwnerId(int id){

        if(id <= 0){
            throw new InvalidRequestException("ID cannot be less than or equal to zero!");
        }
        Iterable<Employee> allEmps = employeeRepository.findAll();
        List<Employee> emps = new ArrayList<>();
        for(Employee emp : allEmps){
            ResourceMetadata data = emp.getResourceMetadata();
            if(data.getResourceOwner() == id){
                emps.add(emp);
            }
        }
        if(emps.isEmpty()){
            throw new ResourceNotFoundException("No employees found with the owner ID: " + id);
        }
        return emps;

    }

    /**
     * update method: The employee object is inputted and changes are saved.
     * The modified object is returned.
     *
     * @param updatedEmp newly updated employee object
     * @return updated/modified employee object
     */
    @Transactional
    public Employee update(EmployeeDto updatedEmp) {
        Employee emp = new Employee(updatedEmp);
        Employee oldEmp = employeeRepository.findById(emp.getId());
        if (oldEmp == null){
            throw new ResourceNotFoundException("No employee exists with ID:" + updatedEmp.getId());
        }
        ResourceMetadata metadata = oldEmp.getResourceMetadata();
        metadata.setLastModifier(updatedEmp.getId());
        metadata.setLastModifiedDateTime(LocalDateTime.now().toString());
        emp.setResourceMetadata(metadata);

        return employeeRepository.save(emp);
    }

    /**
     * delete method: Deletes an employee object based on its id int
     * @param id employeeId int value
     */
    @Transactional
    public void delete(int id) {
        if (id <= 0) {
            throw new InvalidRequestException("ID cannot be less than or equal to zero!");
        }
        employeeRepository.deleteById(id);
    }

    /**
     * Convenience Method
     * @param iterable
     * @param <T>
     * @return
     */
    public static <T> List<T> getListFromIterator(Iterable<T> iterable)
    {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

}
