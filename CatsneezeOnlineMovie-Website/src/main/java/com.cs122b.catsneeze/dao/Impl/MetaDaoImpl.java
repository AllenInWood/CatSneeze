package com.cs122b.catsneeze.dao.Impl;

import com.cs122b.catsneeze.dao.IMetaDao;
import com.cs122b.catsneeze.vo.MetadataVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetaDaoImpl implements IMetaDao{

    public List<MetadataVo> selectMetadataByDBName(String DBName) {
        List<MetadataVo> metadataVoList = new ArrayList<MetadataVo>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb?autoReconnect=true&useSSL=false", "root", "root");
//            Statement pstmt = connection.createStatement();
//            ResultSet result = pstmt.executeQuery("select COLUMN_NAME, DATA_TYPE from information_schema.COLUMNS where table_name = '" + DBName + "'");
            PreparedStatement pstmt = connection.prepareStatement("select COLUMN_NAME, DATA_TYPE from information_schema.COLUMNS where table_name = ?");
            pstmt.setString(1, DBName);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                MetadataVo metadataVo = new MetadataVo();
                metadataVo.setAttribute(result.getString(1));
                metadataVo.setCategory(result.getString(2));
                metadataVoList.add(metadataVo);
            }
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metadataVoList;
    }
}
