package com.cs122b.catsneeze.dao;

import com.cs122b.catsneeze.common.ServerResponse;

import java.util.List;

public interface ISalesDao {

    ServerResponse<Boolean> updateSalesRecord(String movieId, int customerId);

    List<Integer> selectSaleNumByDate(String curDate);
}
