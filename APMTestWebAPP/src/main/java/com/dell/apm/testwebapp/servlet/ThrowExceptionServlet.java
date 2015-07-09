package com.dell.apm.testwebapp.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rlin2
 * Date: 14-7-4
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class ThrowExceptionServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {

            String exceptionName = req.getParameter("exceptionName");
            //int sleepTime = Integer.parseInt(req.getParameter("sleepTime"));
            //Thread.sleep(sleepTime * 1000L);

            StringBuilder sb = new StringBuilder();
            sb.append("Exception Name: " + exceptionName + "s. <br/>");
            outputContent(res, sb.toString());

            Class exceptionClass = Class.forName(exceptionName);
            Exception exception = (Exception)exceptionClass.newInstance();
            throw exception;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        this.doGet(req, res);
    }

    private void outputContent(HttpServletResponse res, String content) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>ThrowExceptionServlet</title></head>");
        out.println("<body> current Time:" + new Date() + "<br/>" + content + "</body></html>");
        out.flush();
    }
}
