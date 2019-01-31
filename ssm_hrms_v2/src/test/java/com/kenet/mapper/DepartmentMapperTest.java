package com.kenet.mapper;

import com.kenet.bean.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class DepartmentMapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void insertDepartTest() {
        Department department = new Department(4,"杨逍","光明左使");
        int resNum = departmentMapper.insertDept(department);
        System.out.println(resNum);
    }

    @Test
    public void updateDeptTest() {
        Department department = new Department(1, "James","设计部");
        int resNum = departmentMapper.updateDeptById(0,department);
        System.out.println(resNum);
    }


    @Test
    public void deleteDeptTest() {
        int res = departmentMapper.deleteDeptById(0);
        System.out.println(res);
    }

    @Test
    public void selectOneByIdTest() {
        Department department = departmentMapper.selectOneById(1);
        System.out.println(department);
    }

    @Test
    public void selectOneByLeaderTest() {
        Department department = departmentMapper.selectOneByLeader("甘木");
        System.out.println(department);
    }

    @Test
    public void selectOneByNameTest() {
        Department department = departmentMapper.selectOneByName("软测部");
        System.out.println(department);
    }

    @Test
    public void selectDeptList() {
        List<Department> departmentList = departmentMapper.selectDeptList();
        for(Department department:departmentList)
            System.out.println(department);
    }

    @Test
    public void selectDeptsByLimitAndOffsetTest() {
        List<Department> departmentList = departmentMapper.selectDeptsByLimitAndOffset(1,2);
        System.out.println(departmentList.size());
        for(Department department:departmentList)
            System.out.println(department);
    }
    @Test
    public void countDeptsTest() {
        int count = departmentMapper.countDepts();
        System.out.println(count);
    }
}
