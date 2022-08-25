package com.zq.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zq.reggie.mapper.EmployeeMapper;
import com.zq.reggie.pojo.Employee;
import com.zq.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【employee(员工信息)】的数据库操作Service实现
 * @createDate 2022-08-26 01:44:59
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements EmployeeService {
}




