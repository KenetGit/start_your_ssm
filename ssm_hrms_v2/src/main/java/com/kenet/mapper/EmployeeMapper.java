package com.kenet.mapper;

import com.kenet.bean.Employee;
import javafx.scene.control.Tab;
import org.apache.ibatis.annotations.*;

import javax.swing.plaf.ToolBarUI;
import java.util.List;

public interface EmployeeMapper {

    String TABLE_NAME = "tbl_emp";
    String INSERT_FIELDS = "emp_name, emp_email,gender,department_id";
    String SELECT_FIELDS = "emp_id," + INSERT_FIELDS;

    /*========================增加======================*/
    @Insert({" INSERT INTO ", TABLE_NAME, "(", INSERT_FIELDS,")" +
            " VALUES(#{empName}, #{empEmail}, #{gender}, #{departmentId})"})
    int insertOne(Employee employee);

    /*========================删除======================*/
    @Delete({" DELETE FROM ", TABLE_NAME, " WHERE emp_id=#{empId}"})
    int deleteOneById(@Param("empId") Integer empId);

    /*=======================更改=======================*/

    // 员工信息修改时不需要更新员工姓名
    @Update({" UPDATE ", TABLE_NAME, " SET emp_email=#{employee.empEmail},gender=#{employee.gender},department_id=#{employee.departmentId}" +
            " WHERE emp_id=#{empId}"})
    int updateOneById(@Param("empId") Integer empId, @Param("employee") Employee employee);

    /*=======================查询=======================*/

    /* 包含了关联对象，需要手动配置查询结果，否则结果不正确*/
    @Select({" SELECT * FROM ", TABLE_NAME, " WHERE emp_id=#{empId} LIMIT 1"})
    @Results({
            @Result(property = "empId" ,column = "emp_id"),
            @Result(property = "empName" ,column = "emp_name"),
            @Result(property = "empEmail" ,column = "emp_email"),
            @Result(property = "gender" ,column = "gender"),
            @Result(property = "departmentId" ,column = "department_id"),
            @Result(property = "department",
                    column = "department_id",
                    one = @One(select = "com.kenet.mapper.DepartmentMapper.selectOneById"))
    })
    Employee selectOneById(@Param("empId") Integer empId);

    /* 包含了关联对象，需要手动配置查询结果，否则结果不正确*/
    @Select({" SELECT * FROM", TABLE_NAME, " WHERE emp_name=#{empName} LIMIT 1"})
    @Results({
            @Result(property = "empId" ,column = "emp_id"),
            @Result(property = "empName" ,column = "emp_name"),
            @Result(property = "empEmail" ,column = "emp_email"),
            @Result(property = "gender" ,column = "gender"),
            @Result(property = "departmentId" ,column = "department_id"),
            @Result(property = "department",
                    column = "department_id",
                    one = @One(select = "com.kenet.mapper.DepartmentMapper.selectOneById"))
    })
    Employee selectOneByName(@Param("empName") String empName);

    /*
    * mybatis使用注解进行关联查询时,需要使用@results手动配置结果映射
    * 一对一关联查询:使用@one(一对多关联查询:使用@many)
    * */
    @Select({" SELECT ", SELECT_FIELDS, " FROM ",TABLE_NAME," WHERE emp_id=#{empId}"})
    @Results({
            @Result(property = "empId" ,column = "emp_id"),
            @Result(property = "empName" ,column = "emp_name"),
            @Result(property = "empEmail" ,column = "emp_email"),
            @Result(property = "gender" ,column = "gender"),
            @Result(property = "departmentId" ,column = "department_id"),
            @Result(property = "department",
                    column = "department_id",
                    one = @One(select = "com.kenet.mapper.DepartmentMapper.selectOneById"))
    })
    Employee selectWithDeptById(@Param("empId") Integer empId);

    @Select({" SELECT * FROM ", TABLE_NAME, " LIMIT #{limit} OFFSET #{offset} "})
    @Results({
            @Result(property = "empId" ,column = "emp_id"),
            @Result(property = "empName" ,column = "emp_name"),
            @Result(property = "empEmail" ,column = "emp_email"),
            @Result(property = "gender" ,column = "gender"),
            @Result(property = "departmentId" ,column = "department_id"),
            @Result(property = "department",
                    column = "department_id",
                    one = @One(select = "com.kenet.mapper.DepartmentMapper.selectOneById"))
    })
    List<Employee> selectByLimitAndOffset(@Param("offset") Integer offset,
                                          @Param("limit") Integer limit);

    @Select(" SELECT COUNT(*) FROM " + TABLE_NAME)
    int countEmployees();
}
