package com.bmc.cards.service;

import com.bmc.cards.dto.CardsDto;

public interface ICardsService {

    void createCard(String mobileNumber) ;

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);
}
