package com.revature.rms.employee.controller;

import com.revature.rms.employee.dtos.EmployeeCreds;
import com.revature.rms.employee.entities.Employee;
import com.revature.rms.employee.exceptions.InvalidRequestException;
import com.revature.rms.employee.services.EmployeeService;
import com.revature.rms.employee.services.ResourceMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    private ResourceMetadataService service;

    @Autowired
    public EmployeeController(EmployeeService employeeService,ResourceMetadataService service) {
        this.employeeService = employeeService;
        this.service =service;
    }

    /**
     * getEmployeeById method: Returns an employee object when the id int matches a record in the database.
     * @param id employeeId int value
     * @return an employee with matching id
     */
    @GetMapping(value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    /**
     * getEmployeesById method: Returns a set of employee objects when the ids- ints match records in the database.
     * @param ids employeeId int values
     * @return a set of employees with matching ids
     */
    @GetMapping(value="/ids/{ids}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Employee> getEmployeesByIds(@PathVariable @RequestBody Set<Integer> ids){
        Set<Employee> employees = new HashSet<>();
        for (int s : ids) {
            employees.add(employeeService.getEmployeeById(s));
        }
        return employees;
    }
    // same method as addNewEmployee
//    /**
////     * addNewEmployeeWithResource method: Takes in a employee object as the input, along with a resourceId.
////     * @param employee employeeCreds DTO object
////     * @return the newly added employee object
////     */
////    @PostMapping(value = "/add2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
////    public Employee addNewEmployeeWithResource(@RequestBody @Valid EmployeeCreds employee,
////                                               @RequestHeader(value = "Authorization") int id) {
////
////        return employeeService.addEmployee(employee, id);
////    }

    /**
     * addEmployee method: Takes in a employee object as the input.
     * @param employee newly persisted employee object
     * @return the newly added employee object
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee addNewEmployee(@RequestBody @Valid EmployeeCreds employee,
                                   @RequestHeader(value = "Authorization") int id) {
        return employeeService.addEmployee(employee, id);
    }

    /**
     * update method: The employee object is inputted and changes are saved.
     * @param employee newly updated employee object
     * @return updated/modified employee object
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@RequestBody @Valid EmployeeCreds employee,
                                   @RequestHeader(value = "Authorization") int id) {
        return employeeService.update(employee, id);
    }

    // duplicate method, we'd rather have the one that returns a set
//    /**
//     * getAllById method:
//     * @param ids employeeId int values
//     * @return a set of employees with matching ids
//     */
//    @GetMapping (value = "/getallbyid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Employee> getAllById (@RequestParam List<Integer> ids){
//        List<Employee> employees = new ArrayList<>();
//        for (int s : ids) {
//            employees.add(employeeService.getEmployeeById(s));
//        }
//        return employees;
//    }

//    not used, may get rid of because of confusing mapping (its also already a method with just a id param)
//    /**
//     * getByid method: Returns an employee object when the id int matches a record in the database.
//     * @param employee employeeCreds DTO object
//     * @return an employee with matching id
//     */
//    @PostMapping(value = "/getbyid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Employee getByid(@RequestBody @Valid EmployeeCreds employee) {
//        int id = employee.getId();
//
//        return employeeService.getEmployeeById(id);
//    }

    /**
     * getByfirstname method: Returns an employee object when the firstName String matches a record in the database.
     * @param String firstName object
     * @return an employee with matching firstName
     */
    @GetMapping(value = "/firstname", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getByFirstName(@RequestParam @Valid String firstName) {

        return employeeService.findByFirstname(firstName);
    }

    /**
     * getAllEmployees method: Returns a list of all the employee objects in the database.
     * @return a list of all the employees
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    /**
     * deleteEmployeeById method: Deletes an employee object based on its id int
     * @param id employeeId int value
     */
    @DeleteMapping(value = "/{id}")
    public void deleteEmployeeById(@PathVariable int id) {
        if (id <= 0) {
            throw new InvalidRequestException();
        }
        employeeService.delete(id);
    }

    /**
     * getEmployeesByOwnerId method: Retrieves a list of employees based on the boss' ID
     * @param id Boss' ID
     * @return List of employees
     */

    @GetMapping(value = "/owners/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getEmployeesByOwnerId(@PathVariable int id){
        return employeeService.findEmployeeByOwnerId(id);
    }

    /**
     * test method: test endpoint to ensure controller is working
     * @return String saying "employeeController loaded"
     */
    @GetMapping("/test")
    public @ResponseBody String test() {
        return "employeeController loaded";
    }
}
