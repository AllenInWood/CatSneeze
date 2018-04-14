package com.cs122b.catsneeze.dao.Impl;

import com.cs122b.catsneeze.dao.ICustomerDao;
import com.cs122b.catsneeze.pojo.Customers;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDaoImpl implements ICustomerDao {

    public int checkEmail(String email) {
        int resultCount = 0;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("select count(1) from customers where email = ?");
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

    public Customers selectLogin(String email, String password) {
        Customers customers = new Customers();
        customers.setId(-1);
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();

            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement(" SELECT * from customers where email = ? and password = ?");
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                customers.setId(result.getInt(1));
                customers.setFirstName(result.getString(2));
                customers.setLastName(result.getString(3));
                customers.setCcId(result.getString(4));
                customers.setAddress(result.getString(5));
                customers.setEmail(result.getString(6));
            }
            result.close();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }


}
