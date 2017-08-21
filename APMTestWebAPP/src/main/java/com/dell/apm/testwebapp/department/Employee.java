package com.dell.apm.testwebapp.department;

public class Employee implements java.io.Serializable
{
    private String name;

    public Employee() {
        this("Default-Name");
    }
    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
