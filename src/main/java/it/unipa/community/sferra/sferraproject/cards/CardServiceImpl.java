package it.unipa.community.sferra.sferraproject.cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    
    @Autowired
    private CardRepository cardRepository;

    @Override
    public void createCard(CardDTO card) {
        Card newCard = new Card();
        newCard.setBlocked( card.isBlocked() );
        newCard.setCredit( card.getCredit() );
        cardRepository.save(newCard);
    }

    @Override
    public void increaseOrDecreaseCreditById(Integer id, Float amount) {
        Card card = cardRepository.findCardById(id);
        Float currentCredit = card.getCredit();
        card.setCredit( currentCredit + amount );
        cardRepository.save(card);
    }

    @Override
    public void blockOrUnblockCardById(Integer id) {
        Card card = cardRepository.findCardById(id);
        boolean currentState = card.isBlocked();
        card.setBlocked( !currentState );
        cardRepository.save(card);
    }

    @Override
    public CardDTO mapToCardDTO(Card card) {
        boolean blocked = card.isBlocked();
        Float credit = card.getCredit();

		return new CardDTO(blocked, credit);
    }
}
