package com.kenet.util;


import com.kenet.bean.Employee;

import java.util.List;

/*
* Employees页面的前后页查询
* ajax异步请求，server返回json
* */
public class EmpInfo {
    private List<Employee> employees;
    private int totalItems;
    private int totalPages;
    private int curPage = 1;
    public EmpInfo(List<Employee> employees, int totalItems, int totalPages, int curPage) {
        this.employees = employees;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.curPage = curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}