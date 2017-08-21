package com.dell.apm.testwebapp.department;

import java.util.ArrayList;
import java.util.Collection;

public class Department implements java.io.Serializable
{
    private Collection<Employee> employees;

    public Department() {
        java.util.List<String> l = new java.util.ArrayList<String>();

        l.add("String1");
        l.add("String2");
        l.add("String3");
        System.out.println("Department: java.util.List<String> l:" + l);

        java.util.HashMap <Double,Integer> ht = new java.util.HashMap<Double,Integer>();
        System.out.println("Department: java.util.HashMap <Double,Integer> ht:" + ht);
    }

    public Department(String prefix, Collection<Employee> employees, int i) {
        System.out.println("Department: param prefix:" + prefix);
        System.out.println("Department: param employees:" + employees);
        this.employees = employees;
        System.out.println("Department: param i:" + i);
    }

    public Department(Department d, Collection<Employee> employees, int i) {
        System.out.println("Department: param d.getEmployees():" + d.getEmployees());
        System.out.println("Department: param employees:" + employees);
        System.out.println("Department: param i:" + i);
        if (d.getEmployees() != null) {
            this.employees = d.getEmployees();
        } else {
            this.employees = employees;
        }
        System.out.println("Department: employees:" + employees);

    }

    public Collection<Employee> getEmployees()
    { return employees; }

    public void setEmployees(Collection<Employee> employees)
    { this.employees = employees; }

    public void meth1(int arg1)
    {
        @SuppressWarnings("unused")
           int local1 = arg1;
      }

   public void meth2(int arg1, ArrayList<String> arg2, int arg3)
    	    {
    	        @SuppressWarnings("unused")
    	        int local1 = arg1;
      }
}
