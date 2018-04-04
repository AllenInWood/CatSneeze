package com.cs122b.catsneeze.service;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.pojo.Employees;

public interface IEmployeesService {
    ServerResponse<Employees> login(String email, String password);
}
