package org.jeecg.modules.jcy.sms.service.impl.tdengine;

import com.taosdata.jdbc.TSDBDriver;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

public class Test_TDEngine {

    Connection conn = null;

    String jdbcUrl = "jdbc:TAOS://ubuntu-develop:6030?user=root&password=taosdata";

    String jdbcRestUrl = "jdbc:TAOS-RS://ubuntu-develop:6041?user=root&password=taosdata";

    public Connection getConn(String jdbcUrl) throws Exception{
        Class.forName("com.taosdata.jdbc.TSDBDriver");
        //String jdbcUrl = "jdbc:TAOS://taosdemo.com:6030/test?user=root&password=taosdata";
        Properties connProps = new Properties();
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_CHARSET, "UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_LOCALE, "en_US.UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_TIME_ZONE, "UTC-8");
        connProps.setProperty("debugFlag", "135");
        connProps.setProperty("maxSQLLength", "1048576");
        conn = DriverManager.getConnection(jdbcUrl);
        return conn;
    }

    public Connection getRestConn(String jdbcUrl) throws Exception{
        Class.forName("com.taosdata.jdbc.rs.RestfulDriver");
        //String jdbcUrl = "jdbc:TAOS-RS://taosdemo.com:6041/test?user=root&password=taosdata";
        Properties connProps = new Properties();
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_BATCH_LOAD, "true");
        //conn = DriverManager.getConnection(jdbcUrl, connProps);
        conn = DriverManager.getConnection(jdbcUrl);
        return conn;
    }

    public void printRow(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            String value = rs.getString(i);
            System.out.print(value);
            System.out.print("\t");
        }
        System.out.println();
    }

    public void printColName(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            String colLabel = meta.getColumnLabel(i);
            System.out.print(colLabel);
            System.out.print("\t");
        }
        System.out.println();
    }
    public void processResult(ResultSet rs) throws SQLException {
        printColName(rs);
        while (rs.next()) {
            printRow(rs);
        }
    }

    @Test
    public void test_query() throws Exception {

        try (Connection conn = getConn(jdbcRestUrl)) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM demo.t");
                processResult(rs);
            }
        }
    }

    @Test
    public void test_insert() throws Exception {

        //System.load("C://TDengine//driver//taos.dll");

        //conn = getConn(jdbcUrl);
        conn = getRestConn(jdbcRestUrl);

        System.out.println("Connected");

        Statement stmt = conn.createStatement();

        // create database
        stmt.executeUpdate("create database if not exists demo");

        // use database
        //stmt.executeUpdate("use demo");

        // create table
        stmt.executeUpdate("create table if not exists demo.t (ts timestamp, speed int)");

        int affectedRows = stmt.executeUpdate("insert into demo.t values(now, 23) (now + 1s, 20)");

        System.out.println("insert " + affectedRows + " rows.");


        conn.close();
    }

}
