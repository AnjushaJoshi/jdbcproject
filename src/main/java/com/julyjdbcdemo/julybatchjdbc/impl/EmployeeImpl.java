package com.julyjdbcdemo.julybatchjdbc.impl;

import com.julyjdbcdemo.julybatchjdbc.model.Employee;
import com.julyjdbcdemo.julybatchjdbc.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//DAO
@Repository
public class EmployeeImpl implements EmployeeRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getEmployees() {

        String sql="select * from employee";
        List<Employee> list=jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Employee.class));
        return list;
    }

    @Override
    public Employee getEmployee(Integer id) {

        String sql="select * from employee where id=?";

        Employee emp= (Employee) jdbcTemplate
                .queryForObject(sql,new Object[]{id},
                        new BeanPropertyRowMapper(Employee.class));
        return emp;
    }

    @Override
    public String saveEmployee(Employee employee) {

        String sql="insert into employee values(?,?,?)";
        jdbcTemplate.update(sql,new Object[]{
                employee.getId(),employee.getName(),employee.getCity()});
        return "Data saved";
    }

    @Override
    public String updateEmployee(Integer id, String name) {

        String sql="update employee set name=? where id=?";
        jdbcTemplate.update(sql,new Object[]{name,id});
        return "Data updated";
    }

    @Override
    public List<Map<String, Object>> getCombinedData() {
        String sql="select b.deptName, a.name, a.city from employee a,department b " +
                "where b.id=a.deptid";

        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        return list;
    }
    @Override
    public String deleteEmployee(Integer id) {

        String sql="delete from  employee where id=?";
        jdbcTemplate.update(sql,new Object[]{id});
        return "Data deleted";
    }

}
