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

    private final EmployeeService employeeService;
    private final ResourceMetadataService service;

    @Autowired
    public EmployeeController(EmployeeService employeeService,ResourceMetadataService service) {
        this.employeeService = employeeService;
        this.service =service;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping (value = "/getallbyid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllById (@RequestBody List<Integer> ids){
        List<Employee> employees = new ArrayList<>();
        for (int s : ids) {
            employees.add(employeeService.getById(s));
        }
        return employees;
    }

    @GetMapping(value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable @RequestBody int id) {
        return employeeService.getById(id);
    }

    @PostMapping(value = "/getbyid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getByid(@RequestBody @Valid EmployeeCreds employee) {
        int id = employee.getId();
        return employeeService.getById(id);
    }

    @GetMapping(value="/group/{ids}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Employee> getEmployeesById(@PathVariable @RequestBody Set<Integer> ids){
        Set<Employee> employees = new HashSet<>();
        for (int s : ids) {
            employees.add(employeeService.getById(s));
        }
        return employees;
    }

    @PostMapping(value = "/getbyfirstname", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getByfirstName(@RequestBody @Valid EmployeeCreds employee) {
        String firstName = employee.getFirstName();
        return employeeService.findByFirstName(firstName);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee addNewEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.add(employee);
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
        return employeeService.add(emp);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.update(employee);
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
        return employeeService.add(emp);
    }

    @PostMapping(value = "/updateresource", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateResource(@RequestBody @Valid EmployeeCreds employee) {
        Employee emp = employeeService.getById(employee.getId());
        emp.setResourceMetadata(service.findById(employee.getResourceId()));
        return employeeService.add(emp);
    }

    @GetMapping("/test")
    public @ResponseBody String test() {
        return "employeeController loaded";
    }
}
