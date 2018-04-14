package com.cs122b.catsneeze.dao.Impl;


import com.cs122b.catsneeze.common.ResponseCode;
import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.ISalesDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SalesDaoImpl implements ISalesDao {

    public ServerResponse<Boolean> updateSalesRecord(String movieId, int customerId) {
        int row = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(new Date());
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();

            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO sales (customerId, movieId, saleDate) VALUES (" + customerId + ", \"" + movieId + "\", \"" + date + "\")");
            row = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (row == 0) {
            return ServerResponse.createBySuccess(false);
        }
        return ServerResponse.createBySuccess(true);
    }

    public List<Integer> selectSaleNumByDate(String curDate) {
        List<Integer> res = new ArrayList<Integer>();
        int nextData = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt;
            for (int i = 0; i < 7; i++) {
                pstmt = connection.prepareStatement("select count(*) from sales where saleDate = ?");
                pstmt.setString(1, curDate);
                ResultSet result = pstmt.executeQuery();
                while (result.next()) {
                    res.add(result.getInt(1));
                }
                nextData = Integer.valueOf(curDate.substring(8)) - 1;
                curDate = curDate.substring(0, 8) + nextData;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
