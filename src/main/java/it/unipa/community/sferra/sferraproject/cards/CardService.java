package it.unipa.community.sferra.sferraproject.cards;

public interface CardService {

    void createCard(CardDTO card);
    
    void increaseOrDecreaseCreditById(Integer id, Float amount);

    void blockOrUnblockCardById(Integer id);

    CardDTO mapToCardDTO(Card card);

}
