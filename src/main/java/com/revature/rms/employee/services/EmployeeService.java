package com.revature.rms.employee.services;

import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.exceptions.InvalidRequestException;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository repo) {
        super();
        this.employeeRepository = repo;
    }

    /**
     * getEmployeeById method: Returns an employee object when the id int matches a record in the database.
     * @param id employeeId int value
     * @return an employee with matching id
     * @throws ResourceNotFoundException when an employee is not found
     */
    @Transactional(readOnly = true)
    public Employee getEmployeeById(int id) throws ResourceNotFoundException{
        return employeeRepository.findById(id);
    }

    /**
     * update method: The employee object is inputted and changes are saved.
     * The modified object is returned.
     * @param updatedEmp newly updated employee object
     * @return updated/modified employee object
     */
    @Transactional
    public Employee update(Employee updatedEmp) {
        return employeeRepository.save(updatedEmp);
    }

    /**
     * addEmployee method: Takes in a employee object as the input. The input employee
     * object is tested to ensure that it is not null. If the employee object
     * is null then it will throw a ResourceNotFoundException.
     * Once the employee object passes the test it is then added or persisted
     * to the database.
     * @param newEmployee newly persisted employee object
     * @return the newly added employee object
     */
    @Transactional
    public Employee addEmployee(Employee newEmployee) {
        if(newEmployee == null){
            throw new ResourceNotFoundException();
        }
        return employeeRepository.save(newEmployee);
    }

    /**
     * findByFirstName method: Returns an employee object when the firstName String matches a record in the database.
     * @param name employeeFirstName String value
     * @return an employee with matching firstName
     * @throws ResourceNotFoundException when an employee is not found
     */
    @Transactional(readOnly = true)
    public Employee findByFirstname(String name) throws ResourceNotFoundException{
        return employeeRepository.findByFirstName(name);

    }

    /**
     * getall method: Returns a list of all the employee objects in the database.
     * @return a list of all the employees
     * @throws ResourceNotFoundException when no employees are found
     */
    @Transactional(readOnly = true)
    public List<Employee> getall() throws ResourceNotFoundException{
        Iterable<Employee> e = employeeRepository.findAll();
        List<Employee> list = getListFromIterator(e);
        return list;
    }
    public static <T> List<T> getListFromIterator(Iterable<T> iterable)
    {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    /**
     *
     * @param id
     * @return
     */
    @Transactional
    public Employee delete(int id) {
        if (id <= 0) {
            throw new InvalidRequestException();
        }
        Employee deleteEmployee = employeeRepository.findById(id);
        return update(deleteEmployee);
    }
}
