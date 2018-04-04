package com.cs122b.catsneeze.dao;


import com.cs122b.catsneeze.pojo.Customers;

public interface ICustomerDao {
    int checkEmail(String username);

    Customers selectLogin(String email, String password);
}
