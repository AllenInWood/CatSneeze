package com.cs122b.catsneeze.dao.Impl;
import com.cs122b.catsneeze.dao.IEmployeesDao;
import com.cs122b.catsneeze.pojo.Employees;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class EmployeesDaoImpl implements IEmployeesDao {

    public int checkEmail(String email) {
        int resultCount = 0;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select count(1) from employees where email = ?");
            pstmt.setString(1, email);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                resultCount = result.getInt(1);
            }
            result.close();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultCount;
    }

    public Employees selectLogin(String email, String password) {
        Employees employees = new Employees();
        employees.setFullname("NULL");
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement(" SELECT * from employees where email = ? and password = ?");
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                employees.setEmail(result.getString(1));
                employees.setFullname(result.getString(3));
                employees.setPassword("XXXX");
            }
            result.close();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}
