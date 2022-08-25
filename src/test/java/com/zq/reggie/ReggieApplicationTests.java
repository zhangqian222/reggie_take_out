package com.zq.reggie;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zq.reggie.pojo.Employee;
import com.zq.reggie.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReggieApplicationTests {
    @Autowired
    EmployeeService employeeService;

    @Test
    void contextLoads() {
        //测试连接数据库
        Employee employee = new Employee();
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("username","admin");
        Employee one = employeeService.getOne(wrapper);
        System.out.println("one:" + one);
    }
}
