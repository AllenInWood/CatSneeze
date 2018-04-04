package com.cs122b.catsneeze.service.Impl;


import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.dao.ICardDao;
import com.cs122b.catsneeze.dao.Impl.CardDaoImpl;
import com.cs122b.catsneeze.pojo.Creditcards;
import com.cs122b.catsneeze.service.ICardService;

public class CardServiceImpl implements ICardService {

    private ICardDao iCardDao = new CardDaoImpl();

    public ServerResponse<Creditcards> selectCard(String cardId) {
        return ServerResponse.createBySuccess(iCardDao.selectCardById(cardId));
    }
}
