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

    private EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepository repo) {
        super();
        this.employeeRepo = repo;
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(int id) throws ResourceNotFoundException{
        return employeeRepo.findById(id);
    }

    @Transactional
    public Employee update(Employee updatedEmp) {
        return employeeRepo.save(updatedEmp);
    }


    @Transactional
    public Employee addEmployee(Employee newEmployee) {
        return employeeRepo.save(newEmployee);
    }

    @Transactional(readOnly = true)
    public Employee findByFirstname(String name) throws ResourceNotFoundException{
        return employeeRepo.findByFirstName(name);

    }

    @Transactional(readOnly = true)
    public List<Employee> getall() throws ResourceNotFoundException{
        Iterable<Employee> e = employeeRepo.findAll();
        List<Employee> list = getListFromIterator(e);
        return list;


    }
    public static <T> List<T> getListFromIterator(Iterable<T> iterable)
    {

        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }




}
