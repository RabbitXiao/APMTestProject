package com.dell.apm.testwebapp.com.dell.apm.testwebapp.servlet.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by rxiao on 7/23/14.
 */
public class DBUtil {
    /**
     * Excute a query from DB, return the result string.
     * @param connection <code>java.sql.Connection</code>
     * @return String
     * @throws SQLException
     */
    public static String excuteQueryForDB(Connection connection) throws SQLException {
        String sql = "select TABLE_NAME, TABLE_TYPE from information_schema.TABLES";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        StringBuilder sb = new StringBuilder();
        sb.append("Executed SQL : " + sql + "<br/>");
        sb.append("Executed Result : <br/> ");

        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            String tableType = resultSet.getString("TABLE_TYPE");
            sb.append("TableName:");
            sb.append(tableName);
            sb.append(", TableType:");
            sb.append(tableType);
            sb.append("<br/>");
        }
        return sb.toString();
    }
}
