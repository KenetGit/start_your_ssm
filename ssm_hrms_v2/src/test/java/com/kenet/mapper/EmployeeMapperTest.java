package com.kenet.mapper;

import com.kenet.bean.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    // 用于批量增加
    @Autowired
    SqlSession sqlSession;

    // 插入一项
    @Test
    public void insertOneTest() {
        Employee employee = new Employee(1,"甘木","ganmu@qq.com","男",1);
        int resNum = employeeMapper.insertOne(employee);
        System.out.println(resNum);
    }

    /*
    * 批量增加
    * */
    @Test
    public void insertEmpsBatchTest() {
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 1; i < 10; i++) {
            String uid = UUID.randomUUID().toString().substring(0,5);
            employeeMapper.insertOne(new Employee(i,"name_"+uid, uid+"@qq.com",i%2==0?"男":"女",i%5));
        }
    }


    /*
    * 查询一项
    * */
    @Test
    public void selectOneByIdTest() {
        Employee employee = employeeMapper.selectOneById(1);
        System.out.println(employee);
    }

    @Test
    public void selectOneByNameTest() {
        Employee employee = employeeMapper.selectOneByName("name_79caf");
        System.out.println(employee);
    }

    @Test
    public void selectWithDeptByIdTest() {
        Employee employee = employeeMapper.selectWithDeptById(2);
        System.out.println(employee);
    }

    @Test
    public void countEmpsTest() {
        System.out.println(employeeMapper.countEmployees());
    }

    @Test
    public void selectByLimitAndOffsetTest() {
        List<Employee> employeeList = employeeMapper.selectByLimitAndOffset(2,4);
        System.out.println(employeeList.size());
        for (Employee employee:employeeList)
            System.out.println(employee);
    }
}
