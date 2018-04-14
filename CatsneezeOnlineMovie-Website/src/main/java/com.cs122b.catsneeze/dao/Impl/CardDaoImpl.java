package com.cs122b.catsneeze.dao.Impl;


import com.cs122b.catsneeze.dao.ICardDao;
import com.cs122b.catsneeze.pojo.Creditcards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CardDaoImpl implements ICardDao {

    public Creditcards selectCardById(String cardId) {
        Creditcards creditcards = new Creditcards();
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/moviedb");
            Connection connection = ds.getConnection();

            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
            PreparedStatement pstmt = connection.prepareStatement("Select distinct * from creditcards where id = ?");
            pstmt.setString(1, cardId);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                creditcards.setId(result.getString(1));
                creditcards.setFirstName(result.getString(2));
                creditcards.setLastName(result.getString(3));
                creditcards.setExpiration(result.getDate(4));
            }
            result.close();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creditcards;
    }
}
