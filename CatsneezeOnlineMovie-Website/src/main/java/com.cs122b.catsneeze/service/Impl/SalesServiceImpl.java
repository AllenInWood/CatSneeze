package com.cs122b.catsneeze.service.Impl;

import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.ISalesDao;
import com.cs122b.catsneeze.dao.Impl.SalesDaoImpl;
import com.cs122b.catsneeze.service.ISalesService;

import java.util.List;

public class SalesServiceImpl implements ISalesService {

    private ISalesDao iSalesDao = new SalesDaoImpl();

    public ServerResponse<Boolean> updateSales(String movieId, int customerId) {

        return iSalesDao.updateSalesRecord(movieId, customerId);

    }

    public ServerResponse<List<Integer>> countSaleNum(String curDate) {

        return ServerResponse.createBySuccess(iSalesDao.selectSaleNumByDate(curDate));
    }
}
