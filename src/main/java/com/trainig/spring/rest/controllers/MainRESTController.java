package com.trainig.spring.rest.controllers;

import com.trainig.spring.beans.Employee;
import com.trainig.spring.dao.EmployeeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainRESTController {

    @Autowired
    private EmployeeDAO employeeDAO;

    private final Logger logger = LoggerFactory.getLogger(MainRESTController.class);

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome(){
        return "Welcome to new Rest Page";
    }

    @GetMapping(value = "/employees",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Employee> getEmployees(){
        return employeeDAO.getAllEmployees();
    }

    @GetMapping(value = "/employee/{empNo}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") String empNo){
        return employeeDAO.getEmployee(empNo);
    }

    @PostMapping(value = "/employee",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {
        logger.info("(Service Side) Creating employee: " + emp.getEmpNo());
        return employeeDAO.addEmployee(emp);
    }

    @PutMapping(value = "employee",
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {
        logger.info("(Service Side) Editing employee: " + emp.getEmpNo());
        return employeeDAO.updateEmployee(emp);
    }

    @DeleteMapping(value = "/employee{empNo}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo) {
        logger.info("(Service Side) Deleting employee: " + empNo);
        employeeDAO.deleteEmployee(empNo);
    }

}
