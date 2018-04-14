package com.cs122b.catsneeze.service;


import com.cs122b.catsneeze.common.ServerResponse;
import com.cs122b.catsneeze.pojo.Creditcards;

public interface ICardService {

    ServerResponse<Creditcards> selectCard(String cardId);
}
