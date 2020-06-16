package com.revature.rms.employee.services;

import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepository repo) {
        super();
        this.employeeRepo = repo;
    }

    /**
     * getAll method: returns a list of all the employee objects in the database.
     * @return a list of all the employees
     * @throws ResourceNotFoundException when no employees are found
     */
    @Transactional(readOnly = true)
    public List<Employee> getAll() throws ResourceNotFoundException{
        Iterable<Employee> e = employeeRepo.findAll();
        return getListFromIterator(e);
    }
    public static <T> List<T> getListFromIterator(Iterable<T> iterable)
    {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    /**
     * findByFirstName method: returns an employee object when the firstName String matches a record in the database.
     * @return an employee with matching firstName
     * @throws ResourceNotFoundException when an employee is not found
     */
    @Transactional(readOnly = true)
    public Employee findByFirstName(String firstName) throws ResourceNotFoundException{
        return employeeRepo.findByFirstName(firstName);
    }

    /**
     * getById method: returns an employee object when the id int matches a record in the database.
     * @return an employee with matching id
     * @throws ResourceNotFoundException when an employee is not found
     */
    @Transactional(readOnly = true)
    public Employee getById(int id) throws ResourceNotFoundException{
        return employeeRepo.findById(id);
    }

    /**
     * Add method: Takes in a employee object as the input. The input employee
     * object is tested to ensure that it is not null. If the employee object
     * is null then it will throw a ResourceNotFoundException.
     * Once the employee object passes the test it is then saved or persisted
     * to the database.
     * @param newEmployee
     * @return the newly added employee object
     */
    @Transactional
    public Employee add(Employee newEmployee) {
        return employeeRepo.save(newEmployee);
    }

    /**
     * Update Method: The employee object is inputted and changes are saved.
     * The modified object is returned.
     * @param updatedEmployee
     * @return updated/modified employee object
     */
    @Transactional
    public Employee update(Employee updatedEmployee) {
        return employeeRepo.save(updatedEmployee);
    }
}
