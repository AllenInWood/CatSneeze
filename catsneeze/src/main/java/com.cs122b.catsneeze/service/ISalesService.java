package com.cs122b.catsneeze.service;

import com.cs122b.catsneeze.common.ServerResponse;

import java.util.List;

public interface ISalesService {

    ServerResponse<Boolean> updateSales(String movieId, int customerId);

    ServerResponse<List<Integer>> countSaleNum(String curDate);
}
