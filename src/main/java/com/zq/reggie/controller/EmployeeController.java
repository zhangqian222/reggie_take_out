package com.zq.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zq.reggie.commen.R;
import com.zq.reggie.pojo.Employee;
import com.zq.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 员工登陆
     *
     * @param employee
     * @param request
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {
        //1.获取页面提交的密码，进行md5加密
        String password = employee.getPassword();
        String username = employee.getUsername();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2.根据获取到的用户名查询数据库，进行比对
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Employee emp = employeeService.getOne(wrapper);
        //2.1数据库中未查到
        if (emp == null) {
            return R.error("账号有误，请重新输入");
        }
        //2.2.数据库中查到emp，但是密码不正确
        else if (!password.equals(emp.getPassword())) {
            return R.error("密码有误，请重新输入");
        }
        //2.3.查看员工状态，若为1则可以登陆，为0则禁用登陆
        else if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }
        //2.4.其余情况，登陆成功，将emp放入session中
        else {
            request.getSession().setAttribute("employee", emp);
            log.info("emp=" + emp);
            R<Employee> success = R.success(emp);
            return success;
        }
    }
}
