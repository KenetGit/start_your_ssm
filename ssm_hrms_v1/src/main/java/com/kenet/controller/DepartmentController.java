package com.kenet.controller;

import com.kenet.bean.Department;
import com.kenet.service.DepartmentService;
import com.kenet.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/hrms/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /*
    * 查询所有部门名称
    * @return
    * */
    @RequestMapping(value = "/getDeptName", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeptName() {
        List<Department> departmentList = departmentService.getDeptNames();
        if(departmentList != null)
            return JsonMsg.success().addInfo("departmentList", departmentList);
        return JsonMsg.fail().addInfo("getDeptName_err","查询部门名称出错");
    }

    /*
    * 删除部门
    * @Param deptId
    * @return
    * */

    @RequestMapping(value = "/delDept/{deptId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteDept(@PathVariable("deptId") Integer deptId) {
        int res = 0;
        if(deptId > 0) {
            res = departmentService.deleteDeptById(deptId);
        }
        if(res != 1) {
            return JsonMsg.fail().addInfo("del_dept_err","删除异常");
        }
        return JsonMsg.success();
    }

    /*
    * 更改部门信息
    * @Param deptId
    * @return
    * */
    @RequestMapping(value = "/updateDept/{deptId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateDeptById(@PathVariable("deptId") Integer deptId, Department department) {
        int res = 0;
        if(deptId > 0) {
            res = departmentService.updateDeptById(deptId,department);
        }
        if(res != 1) {
            return JsonMsg.fail().addInfo("update_dept_err","部门更新失败");
        }
        return JsonMsg.success();
    }

    /*
    * 新增部门
    * @Param department
    * @return
    * */
    @RequestMapping(value = "/addDept", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addDept(Department department) {
        int res = departmentService.addDept(department);
        if(res != 1) {
            return JsonMsg.fail().addInfo("add_dept_error","添加部门异常");
        }
        return JsonMsg.success();
    }

    /*
    * 查询部门信息总页码数
    * @return
    * */
    @RequestMapping(value = "/getTotalPages", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPages() {

        int limit = 5;
        int totalItems = departmentService.getDeptCount();
        int temp = totalItems / limit;
        int totalPages = (totalItems%limit==0)?temp:temp+1;
        return JsonMsg.success().addInfo("totalPages",totalPages);
    }

    @RequestMapping(value = "/getDeptById/{deptId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeptById(@PathVariable("deptId") Integer deptId) {
        Department department = null;
        if (deptId > 0) {
            department = departmentService.getDeptById(deptId);
        }
        if (department != null) {
            return JsonMsg.success().addInfo("department",department);
        }
        return JsonMsg.fail().addInfo("get_dept_error","无部门信息");
    }

    @RequestMapping(value = "getDeptList",method = RequestMethod.GET)
    public ModelAndView getDeptList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        ModelAndView mv = new ModelAndView("departmentPage");
        int limit = 5;
        int totalItems = departmentService.getDeptCount();
        int temp = totalItems / limit;
        int totalPages = (totalItems % limit == 0)?temp:temp+1;
        int offset = (pageNo - 1) * limit;

        List<Department> departmentList = departmentService.getDeptList(offset, limit);

        mv.addObject("departments",departmentList)
                .addObject("totalItems", totalItems)
                .addObject("totalPages",totalPages)
                .addObject("curPageNo",pageNo);
        return mv;
    }


}
