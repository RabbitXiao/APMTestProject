package com.dell.apm.testwebapp.servlet;

import com.dell.apm.testwebapp.com.dell.apm.testwebapp.servlet.util.DBUtil;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rxiao on 7/23/14.
 */
public class AcquireMultipleDataSourceServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AcquireMultipleDataSourceServlet.class.getName());
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

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        String dataSourceCount = request.getParameter("dataSourceCount");
        Integer dataDourceIntValue = 1;
        try {
            dataDourceIntValue = Integer.parseInt(dataSourceCount);
        } catch (Exception e) {}

        String release = request.getParameter("release");
        Boolean releaseBooleanValue = true;
        try {
            releaseBooleanValue = Boolean.parseBoolean(release);
        } catch (Exception e) {}

        StringBuilder sb = new StringBuilder();
        doLog(sb, "dataSourceCount="+dataSourceCount+", release="+release);
        for (int i=0; i<dataDourceIntValue; ++i) {
            getConnection(sb, i, releaseBooleanValue);
        }

        response.getWriter().println(sb.toString());
    }

    private void getConnection(StringBuilder sb, int number, boolean release) throws ServletException {
        java.sql.Connection connection = null;
        try {
            doLog(sb, "Start to get DataSource["+number+"]");
            DataSource dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
            connection = dataSource.getConnection();
            DBUtil.excuteQueryForDB(connection);
            doLog(sb, "Finish quering by using DataSource["+number+"]");
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            if (release && connection != null) {
                try {
                    connection.close();
                    doLog(sb, "Release DataSource["+number+"]");
                } catch (SQLException e) {
                    throw new ServletException("Error occurs when close connection", e);
                }
            } else {
                doLog(sb, "DataSource["+number+"] won't be released");
        }
        }
    }
    
    private void doLog(StringBuilder sb, String msg) {
        logger.info(msg);
        sb.append(new java.util.Date());
        sb.append(msg);
        sb.append("<br/>");
    }
}
