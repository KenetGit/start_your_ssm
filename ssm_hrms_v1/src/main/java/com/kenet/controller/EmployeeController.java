package com.kenet.controller;

import com.kenet.bean.Employee;
import com.kenet.service.EmployeeService;
import com.kenet.util.EmpInfo;
import com.kenet.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/hrms/emp")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /*
    * 指定empId，删除员工信息
    * */
    @RequestMapping(value = "/deleteEmp/{empId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteEmp(@PathVariable("empId") Integer empId) {
        int res = 0;
        if(empId > 0) {
            res = employeeService.deleteEmpById(empId);
        }
        if(res != 1) {
            return JsonMsg.fail().addInfo("emp_del_error", "员工删除异常");
        }
        return JsonMsg.success();
    }

    /*
    * 更改员工信息
    * */
    @RequestMapping(value = "/updateEmp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateEmp(@PathVariable("empId") Integer empId, Employee employee) {
        System.out.println(employee);
        int res = employeeService.updateEmpById(empId, employee);
        if(res != 1) {
            return JsonMsg.fail().addInfo("emp_update_err","更改异常");
        }
        return JsonMsg.success();
    }

    /*
    * 检查employee名字是否存在
    * */
    @RequestMapping(value = "/checkEmpExits", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkEmpExits(@RequestParam("empName") String empName) {
        String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]){2,5}";
        if(!empName.matches(regName)) {
            return JsonMsg.fail().addInfo("name_reg_error","输入姓名为2-5位中文或者6-16位英文和数组结合");
        }
        Employee employee = employeeService.getEmpByName(empName);
        if(employee != null) {
            return JsonMsg.fail().addInfo("name_reg_error","该员工名已经存在");
        } else {
            return JsonMsg.success();
        }
    }

    /*
    * 新增员工
    * */
    @RequestMapping(value = "/addEmp", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addEmp(Employee employee) {
        int res = employeeService.addEmp(employee);
        if(res == 1) {
            return JsonMsg.success();
        } else {
            return JsonMsg.fail().addInfo("add_emp_error","添加员工出现异常");
        }
    }

    /*
    * 新增记录后，查询最新的页数
    * */
    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPage() {
        int totalItems = employeeService.getEmpCount();
        int temp = totalItems / 5;
        int totalPages = (totalItems % 5 == 0)? temp : temp + 1;
        return JsonMsg.success().addInfo("totalPages", totalPages);
    }

    /*
    * 根据员工ID查询员工信息
    *
    * */

    @RequestMapping(value = "/getEmpById/{empId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getEmpById(@PathVariable("empId") Integer empId) {
        Employee employee = employeeService.getEmpById(empId);
        if(employee != null) {
            return JsonMsg.success().addInfo("employee", employee);
        } else {
            return JsonMsg.fail().addInfo("query_emp_error","查找不到对应ID的员工");
        }
    }

    /*
    * 查看指定页码对应的员工信息所在的页面
    * 返回的视图包括数据
    * */
    @RequestMapping(value = "/getEmpInfoList", method = RequestMethod.GET)
    public ModelAndView getEmpPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        ModelAndView mv = new ModelAndView("employeePage");

        int limit = 5;
        // 跳过pageNo-1页的信息，每页大小是limit
        int offset = (pageNo-1)*limit;
        // 获取指定页面的员工信息列表
        List<Employee> employeeList = employeeService.getEmpList(offset, limit);
        // 获取总的员工记录数
        int totalItems = employeeService.getEmpCount();
        // 获取总的页数
        int tmp = totalItems / limit;
        int totalPages = (totalItems % limit == 0)? tmp : tmp + 1;
        // 当前页数
        int curPage = pageNo;

        // 将结果放到视图中
        mv.addObject("employees",employeeList)
                .addObject("totalItems",totalItems)
                .addObject("totalPages",totalPages)
                .addObject("curPage",curPage);

        return mv;
    }



/*
    jsp视图在返回前已经写死了，没法在前端通过js脚本修改
    这种方法只有在前后端分离中才适用，也就是渲染页面的工作交给前端

    @RequestMapping(value = "/getEmpList",method = RequestMethod.GET)
    @ResponseBody
    public EmpInfo getEmpList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        int limit = 5;
        // 跳过pageNo-1页的信息，每页大小是limit
        int offset = (pageNo-1)*limit;
        // 获取指定页面的员工信息列表
        List<Employee> employeeList = employeeService.getEmpList(offset, limit);
        // 获取总的员工记录数
        int totalItems = employeeService.getEmpCount();
        // 获取总的页数
        int tmp = totalItems / limit;
        int totalPages = (totalItems % limit == 0)? tmp : tmp + 1;
        // 当前页数
        int curPage = pageNo;

        return new EmpInfo(employeeList,totalItems, totalPages,curPage);
    }
*/

}
