package com.dell.apm.testwebapp.servlet;

import com.dell.apm.testwebapp.util.HttpConnUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class VisitUrlServlet extends HttpServlet{

    public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
        execute(req, res);
    }

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        execute(req, res);
    }

    protected void execute(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
        String url = req.getParameter("url");
        if (url == null || url.trim().length() == 0) {
            throw new IllegalArgumentException("Paramter url:[" + url + "] can not be empty.");
        }


        int count = 1;
        try {
            count = Integer.parseInt(req.getParameter("count"));
        } catch (Exception e) {
            count = 1;
        }
        if (count <= 0) {
            throw new IllegalArgumentException("Paramter count:[" + count + "] can not less than 1.");
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            long t0 = System.currentTimeMillis();
            String responseContent = HttpConnUtil.visitURL(url);
            long spentTime = System.currentTimeMillis() - t0;
            sb.append("--------------------------------------------------------------------------<br/>");
            sb.append("Index: " + i + "<br/>");
            sb.append("Visit URL: " + url + "<br/>");
            sb.append("Spent Time: " + spentTime + "ms. <br/>");
            sb.append("Response Content:<br/> " + responseContent);
        }
        outputContent(res, sb.toString());
    }

    private void outputContent(HttpServletResponse res, String content) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>VisitTheSameLinkServlet</title></head>");
        out.println("<body> current Time:" + new Date() + "<br/>" + content + "</body></html>");
        out.flush();
    }

}
