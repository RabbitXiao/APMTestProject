package com.dell.apm.testwebapp.servlet;

import com.dell.apm.testwebapp.department.Department;
import com.dell.apm.testwebapp.department.Employee;
import com.dell.apm.testwebapp.department.Help;
import javassist.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DepartmentServlet extends HttpServlet{

    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
        execute(req, res);
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        execute(req, res);
    }

    protected void execute(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        StringBuilder sb = new StringBuilder();
        test();
        sb.append("test() is called.<br/>");
        test2();
        sb.append("test2() is called.<br/>");
        try {
            test3();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Help help = new Help();
        /*
        Collection<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee());
        employees.add(new Employee("Rabbit"));
        Department department = new Department();
        department.setEmployees(employees);
        */
        Department department1 = help.getDepartment();

        department1.meth1(1);
        department1.meth2(1, new ArrayList<String>(), 3);

        sb.append("department1.employees size():" + department1.getEmployees().size() + " <br/>");
        Iterator<Employee> itr = department1.getEmployees().iterator();
        while (itr.hasNext()) {
            Employee employee = itr.next();
            sb.append(employee.getName() + " <br/>");
        }

        Department department2 = new Department("department2", department1.getEmployees(), 1);
        sb.append("department2.employees size():" + department2.getEmployees().size() + " <br/>");

        Department department3 = new Department(department1, null, 1);
        sb.append("department3.employees size():" + department3.getEmployees().size() + " <br/>");
        outputContent(res, sb.toString());
    }

    private void outputContent(HttpServletResponse res, String content) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>DepartmentServlet</title></head>");
        out.println("<body> current Time:" + new Date() + "<br/>" + content + "</body></html>");
        out.flush();
    }

    protected void test() {
        KontofondsDTO dto1 = new KontofondsDTO();
        KontofondsDTO dto2 = new KontofondsDTO();

        ArrayList kfeList = new ArrayList();
        kfeList.add(new KontofondsEntityDTO(dto1));
        kfeList.add(new KontofondsEntityDTO(dto2));
        kontoFonds = kfeList;
    }

    protected void test2() {
        java.util.List<String> l = new java.util.ArrayList<String>();

        l.add("String1");
        l.add("String2");
        l.add("String3");

        java.util.HashMap <Double,Integer> ht = new java.util.HashMap<Double,Integer>();
    }

    protected void test4() {
        ClassPool cp = ClassPool.getDefault();
        cp.insertClassPath(new LoaderClassPath(this.getClass().getClassLoader()));
        CtClass ctClass = null;
        try {
            ctClass = cp.get("com.dell.apm.testwebapp.servlet.Test1");
            CtMethod m = ctClass.getDeclaredMethod("run");
            m.addParameter(CtClass.intType);

            ctClass.toClass();
            System.out.println("test3() is called");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void test3() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class cl = Class.forName("com.dell.apm.testwebapp.servlet.Test1");
        Constructor ct = cl.getConstructor(Integer.TYPE);
        Test1 test1;
        try {
            test1 = (Test1) ct.newInstance(3);
            Method md = cl.getMethod("run", Integer.TYPE);
            md.invoke(test1, 2);
            System.out.println("test3() is called");
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        DepartmentServlet ds = new DepartmentServlet();
        ds.test3();
    }
    public static class KontofondsDTO {

    }
    public static class KontofondsEntityDTO {
        private KontofondsDTO dto;

        public KontofondsEntityDTO(KontofondsDTO dto) {
            this.dto = dto;
        }
    }
    private List<KontofondsEntityDTO> kontoFonds;
}
