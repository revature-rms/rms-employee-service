package com.revature.rms.employee.services;

import com.netflix.discovery.converters.Auto;
import com.revature.rms.employee.dtos.EmployeeCreds;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.entities.ResourceMetadata;
import com.revature.rms.employee.exceptions.InvalidRequestException;
import com.revature.rms.employee.exceptions.BadRequestException;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.repositories.EmployeeRepository;
import com.revature.rms.employee.repositories.ResourceMetadataRepository;
import jdk.jfr.internal.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private ResourceMetadataRepository metadataRepository;

    @Autowired
    public EmployeeService(EmployeeRepository repo, ResourceMetadataRepository metadataRepository) {
        super();
        this.employeeRepository = repo;
        this.metadataRepository = metadataRepository;
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
     * findEmployeeByOwnerId method: Retrieves a list of employees based on boss' ID
     * @param id Boss' ID
     * @return List of employees
     */
    @Transactional(readOnly = true)
    public List<Employee> findEmployeeByOwnerId(int id){

        if(id < 1){
            throw new BadRequestException();
        }

        Iterable<Employee> allEmps = employeeRepository.findAll();

        List<Employee> emps = new ArrayList<Employee>();

        for(Employee emp : allEmps){
            ResourceMetadata data = emp.getResourceMetadata();
            if(data.getResourceOwner() == id){
                emps.add(emp);
            }
        }

        if(emps.isEmpty()){
            throw new ResourceNotFoundException();
        }

        return emps;

    }

    /**
     * update method: The employee object is inputted and changes are saved.
     * The modified object is returned.
     * @param updatedEmp newly updated employee object
     * @return updated/modified employee object
     */
    @Transactional
    public Employee update(EmployeeCreds updatedEmp, int id) {
        Employee emp = new Employee(updatedEmp);
        Employee oldEmp = employeeRepository.findById(emp.getId());
        ResourceMetadata metadata = oldEmp.getResourceMetadata();
        metadata.setLastModifier(id);
        metadata.setLastModifiedDateTime(LocalDateTime.now().toString());
        metadataRepository.save(metadata);
        emp.setResourceMetadata(metadata);

        return employeeRepository.save(emp);
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
    public Employee addEmployee(EmployeeCreds newEmployee, int id) {
        if(newEmployee == null){
            throw new ResourceNotFoundException();
        }
        Employee employee = new Employee(newEmployee);
        ResourceMetadata metadata = new ResourceMetadata(id, id, id);
        metadataRepository.save(metadata);
        employee.setResourceMetadata(metadata);

        return employeeRepository.save(employee);
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
     * delete method: Deletes an employee object based on its id int
     * @param id employeeId int value
     */
    @Transactional
    public void delete(int id) {
        if (id <= 0) {
            throw new InvalidRequestException();
        }
        employeeRepository.deleteById(id);
    }
}
