package com.cs122b.catsneeze.service.Impl;


import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.common.VerifyUtils;
import com.cs122b.catsneeze.dao.ICustomerDao;
import com.cs122b.catsneeze.dao.Impl.CustomerDaoImpl;
import com.cs122b.catsneeze.pojo.Customers;
import com.cs122b.catsneeze.service.ICustomerService;
import com.cs122b.catsneeze.vo.CustomerVo;

public class CustomerServiceImpl implements ICustomerService {

    private ICustomerDao iCustomerDao = new CustomerDaoImpl();

    public ServerResponse<CustomerVo> login(String email, String password, String gRecaptchaResponse) {
        int resultCount = iCustomerDao.checkEmail(email);
        CustomerVo customerVo = new CustomerVo();
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("email doesn't exist");
        }

        Customers customers = iCustomerDao.selectLogin(email, password);
        if (customers.getId() == -1) {
            return ServerResponse.createByErrorMessage("password wrong");
        }
        //recaptcha validation
        boolean valid = VerifyUtils.verify(gRecaptchaResponse);
        if (!valid) {
            return ServerResponse.createByErrorMessage("recaptcha wrong");
        }
        //assemble
        customerAssemble(customerVo, customers);
        return ServerResponse.createBySuccess("login successfully", customerVo);
    }

    //mobile login (no recaptcha)
    public ServerResponse<CustomerVo> mobileLogin(String email, String password) {
        int resultCount = iCustomerDao.checkEmail(email);
        CustomerVo customerVo = new CustomerVo();
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("email doesn't exist");
        }

        Customers customers = iCustomerDao.selectLogin(email, password);
        if (customers.getId() == -1) {
            return ServerResponse.createByErrorMessage("password wrong");
        }
        //assemble
        customerAssemble(customerVo, customers);
        return ServerResponse.createBySuccess("login successfully", customerVo);
    }


    private void customerAssemble(CustomerVo customerVo, Customers customers) {
        customerVo.setId(customers.getId());
        customerVo.setFirstName(customers.getFirstName());
        customerVo.setLastName(customers.getLastName());
        customerVo.setAddress(customers.getAddress());
        customerVo.setEmail(customers.getEmail());
    }
}
