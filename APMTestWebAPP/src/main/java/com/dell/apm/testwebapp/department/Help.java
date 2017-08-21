package com.dell.apm.testwebapp.department;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxiao on 8/9/2017.
 */
public class Help {
    private Department department;

    public Help() {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee());
        employees.add(new Employee("Rabbit"));
        employees.add(new EmployeeChild("Child-1"));
        Department department = new Department();
        department.setEmployees(employees);
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public static void main(String[] args) {
        Help help = new Help();
        Department department = help.getDepartment();
        System.out.println(department.getEmployees());
    }
}
