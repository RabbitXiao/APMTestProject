package com.dell.apm.testwebapp.servlet;

import com.dell.apm.testwebapp.util.DBUtil;
import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

public class MySQLDBServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MySQLDBServlet.class.getName());
    private static final String DATASOURCE_NAME = "jdbc/MYSQLTestDataSource";
    private InitialContext initCtx;
    private Context envContext;

    public void init(ServletConfig config) throws ServletException {
        try {
            initCtx = new InitialContext();
            envContext = (Context) initCtx.lookup("java:comp/env");
        } catch (NamingException e) {
            throw new ServletException(e);


    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        java.sql.Connection connection = null;
        try {
            int sleepTime = Integer.parseInt(request.getParameter("sleepTime"));
            if (sleepTime > 0) {
                Thread.sleep(sleepTime * 1000L);
            }
            DataSource dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
            connection = dataSource.getConnection();
            StringBuffer sb = new StringBuffer();
            sb.append("Sleep Time: " + sleepTime + "s. <br/>");
            sb.append(DBUtil.excuteQueryForDB(connection));
            //logger.info("Excute DB Query:<br/> " + sb.toString());
            outputContent(response, sb.toString());
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new ServletException("Error occurs when close connection", e);
                }
            }
        }
    }

    private void outputContent(HttpServletResponse res, String content) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>AccessMySQL</title></head>");
        out.println("<body> current Time:" + new Date() + "<br/>" + content + "</body></html>");
        out.flush();
    }
}
