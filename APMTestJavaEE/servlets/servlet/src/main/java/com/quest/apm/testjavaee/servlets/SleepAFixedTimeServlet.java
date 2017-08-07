package com.quest.apm.testjavaee.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: rlin2
 * Date: 14-7-4
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class SleepAFixedTimeServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {

            int sleepTime = Integer.parseInt(req.getParameter("sleepTime"));
            Thread.sleep(sleepTime * 1000L);

            StringBuilder sb = new StringBuilder();
            sb.append("Sleep Time: " + sleepTime + "s. <br/>");

            outputContent(res, sb.toString());
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
        out.println("<html><head><title>SleepAFixedTimeServlet</title></head>");
        out.println("<body> current Time:" + new Date() + "<br/>" + content + "</body></html>");
        out.flush();
    }

}
