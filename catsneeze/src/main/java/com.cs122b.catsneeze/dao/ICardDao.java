package com.cs122b.catsneeze.dao;


import com.cs122b.catsneeze.pojo.Creditcards;

public interface ICardDao {

    Creditcards selectCardById(String cardId);
}
