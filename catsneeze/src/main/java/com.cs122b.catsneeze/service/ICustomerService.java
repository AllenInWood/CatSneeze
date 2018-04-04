package com.cs122b.catsneeze.service;


import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.vo.CustomerVo;

public interface ICustomerService {
    ServerResponse<CustomerVo> login(String username, String password, String gRecaptchaResponse);

    ServerResponse<CustomerVo> mobileLogin(String email, String password);
}
