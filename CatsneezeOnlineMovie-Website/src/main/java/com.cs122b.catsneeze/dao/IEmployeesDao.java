package com.cs122b.catsneeze.dao;

import com.cs122b.catsneeze.pojo.Employees;

public interface IEmployeesDao {

    int checkEmail(String email);

    Employees selectLogin(String email, String password);
}
