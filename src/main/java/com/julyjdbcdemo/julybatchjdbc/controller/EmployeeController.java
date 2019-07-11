package com.julyjdbcdemo.julybatchjdbc.controller;

import com.julyjdbcdemo.julybatchjdbc.impl.EmployeeImpl;
import com.julyjdbcdemo.julybatchjdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeImpl employeeImpl;

    @GetMapping(value="/getemployees")
    public List<Employee> getData(){
        List<Employee> list=employeeImpl.getEmployees();
        return list;
    }

    @GetMapping(value="/getemployee/{id}")
    public Employee getData1(@PathVariable Integer id){
        Employee emp=employeeImpl.getEmployee(id);
        return emp;
    }

    @PostMapping(value="/saveemp")
    public String saveData(@RequestBody Employee employee){
        String result=employeeImpl.saveEmployee(employee);
        return "saved data";
    }

    @GetMapping(value="/updateemp/{id}/{name}")
    public String updateData(@PathVariable Integer id,
                             @PathVariable String name){
        String res=employeeImpl.updateEmployee(id,name);
        return "saved";
    }

    @GetMapping(value="/joindata")
    public List<Map<String,Object>> getCombineddata(){
          return employeeImpl.getCombinedData();
    }

    @GetMapping(value="/deleteemp/{id}")
    public String deletdata(@PathVariable Integer id){
        String res=employeeImpl.deleteEmployee(id);
        return res;
    }
}
