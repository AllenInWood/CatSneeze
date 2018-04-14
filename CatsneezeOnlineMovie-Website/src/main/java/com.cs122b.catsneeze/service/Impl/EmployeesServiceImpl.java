package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.common.VerifyUtils;
import com.cs122b.catsneeze.dao.IEmployeesDao;
import com.cs122b.catsneeze.dao.Impl.EmployeesDaoImpl;
import com.cs122b.catsneeze.pojo.Employees;
import com.cs122b.catsneeze.service.IEmployeesService;

public class EmployeesServiceImpl implements IEmployeesService{

    private IEmployeesDao iEmployeesDao = new EmployeesDaoImpl();

    public ServerResponse<Employees> login(String email, String password) {
        int resultCount = iEmployeesDao.checkEmail(email);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("email doesn't exist");
        }

        Employees employees = iEmployeesDao.selectLogin(email, password);
        if ("NULL".equals(employees.getFullname())) {
            return ServerResponse.createByErrorMessage("password wrong");
        }
        //recaptcha validation
//        boolean valid = VerifyUtils.verify(gRecaptchaResponse);
//        if (!valid) {
//            return ServerResponse.createByErrorMessage("recaptcha wrong");
//        }
        return ServerResponse.createBySuccess("login successfully", employees);
    }
}
