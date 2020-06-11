package com.revature.rms.employee.controller;

import com.revature.rms.employee.dtos.EmployeeCreds;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.services.EmployeeService;
import com.revature.rms.employee.services.ResourceMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private ResourceMetadataService service;

    @Autowired
    public EmployeeController(EmployeeService employeeService,ResourceMetadataService service) {
        this.employeeService = employeeService;
        this.service =service;
    }

    @GetMapping(value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable @RequestBody int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(value="/group/{ids}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Employee> getEmployeesById(@PathVariable @RequestBody Set<Integer> ids){
        Set<Employee> employees = new HashSet<>();
        for (int s : ids) {
            employees.add(employeeService.getEmployeeById(s));
        }
        return employees;
    }

    @PostMapping(value = "/add2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee addNewEmployeeWithResource(@RequestBody @Valid EmployeeCreds employee) {
        Employee emp = new Employee();
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmail(employee.getEmail());
        emp.setTitle(employee.getTitle());
        emp.setDepartment(employee.getDepartment());

        emp.setResourceMetadata(service.findById(employee.getResourceId()));

        return employeeService.addEmployee(emp);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee addNewEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.update(employee);
    }

    @PostMapping(value = "/updateresource", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateResource(@RequestBody @Valid EmployeeCreds employee) {
        Employee emp = employeeService.getEmployeeById(employee.getId());
        emp.setResourceMetadata(service.findById(employee.getResourceId()));

        return employeeService.addEmployee(emp);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateAll(@RequestBody @Valid EmployeeCreds employee) {
        Employee emp = new Employee();
        emp.setId(employee.getId());
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmail(employee.getEmail());
        emp.setTitle(employee.getTitle());
        emp.setDepartment(employee.getDepartment());

        emp.setResourceMetadata(service.findById(employee.getResourceId()));

        return employeeService.addEmployee(emp);
    }


    @GetMapping (value = "/getallbyid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllById (@RequestParam List<Integer> ids){
        List<Employee> employees = new ArrayList<>();
        for (int s : ids) {
            employees.add(employeeService.getEmployeeById(s));
        }
        return employees;
    }

    @PostMapping(value = "/getbyid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getByid(@RequestBody @Valid EmployeeCreds employee) {
        int id = employee.getId();

        return employeeService.getEmployeeById(id);
    }
    @PostMapping(value = "/getbyfirstname", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getByfirstname(@RequestBody @Valid EmployeeCreds employee) {
        String fname = employee.getFirstName();

        return employeeService.findByFirstname(fname);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getall();
    }

    @GetMapping("/test")
    public @ResponseBody String test() {
        return "employeeController loaded";
    }

}
