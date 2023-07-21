package it.unipa.community.sferra.sferraproject.cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unipa.community.sferra.sferraproject.transactions.TransactionDTO;
import it.unipa.community.sferra.sferraproject.transactions.TransactionService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cards")
public class CardController {
    
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/check-credit")
	public String checkCredit() {
		return "index";
	}

    @PostMapping("/check-credit")
    @ResponseBody
	public CardDTO checkCredit(@RequestParam(value = "id") Integer id, HttpServletResponse response) {
        Card card = cardRepository.findCardById(id);

        if (card == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

		return cardService.mapToCardDTO(card);
	}

    @GetMapping("/merchant/subtract-add-credit")
    public String subtractOrAddCredit() {
        return "/cards/merchant/perform-transaction";
    }

    @PostMapping("/merchant/subtract-credit")
    @ResponseBody
    public CardDTO subtractCredit(
        @RequestParam(value = "id") Integer id,
        @RequestParam(value = "amount") Float amount,
        @RequestParam(value = "description") String description,
        HttpServletResponse response,
        @AuthenticationPrincipal User user
    ) {
        Card card = cardRepository.findCardById(id);

        if (card == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        Float credit = card.getCredit();
        boolean blocked = card.isBlocked();

        // The test on amount < 0.01 is needed to check if a client is not using 
        // our front-end and he wants to perform an invalid operation.
        if ( blocked || amount < (float)0.01 ) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        } else if (credit < amount) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return null;
        }

        cardService.increaseOrDecreaseCreditById(id, - amount);

        TransactionDTO transaction = new TransactionDTO();
        transaction.setUsername( user.getUsername() );
        transaction.setCardId(id);
        transaction.setCredit(- amount);
        transaction.setDescription(description);
        transactionService.createTransaction(transaction);

        return new CardDTO(blocked, credit - amount);
    }

    @PostMapping("/merchant/add-credit")
    @ResponseBody
    public CardDTO addCredit(
        @RequestParam(value = "id") Integer id,
        @RequestParam(value = "amount") Float amount,
        @RequestParam(value = "description") String description,
        HttpServletResponse response, 
        @AuthenticationPrincipal User user
    ) {
        Card card = cardRepository.findCardById(id);

        if (card == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        Float credit = card.getCredit();
        boolean blocked = card.isBlocked();

        // The test on amount < 0.01 is needed to check if a client is not using 
        // our front-end and he wants to perform an invalid operation.
        if ( blocked || amount < (float)0.01 ) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }

        cardService.increaseOrDecreaseCreditById(id, amount);

        TransactionDTO transaction = new TransactionDTO();
        transaction.setUsername( user.getUsername() );
        transaction.setCardId(id);
        transaction.setCredit(amount);
        transaction.setDescription(description);
        transactionService.createTransaction(transaction);

        return new CardDTO(blocked, credit + amount);
    }

    @GetMapping("/admin/create-card")
    public String createCard() {
        return "/cards/admin/card-creation";
    }

    @PostMapping("/admin/create-card")
    @ResponseBody
    public Integer createCard(
        @Valid CardDTO card,
        HttpServletResponse response
    ) {
        cardService.createCard(card);
        Card newCard = cardRepository.findTopByOrderByIdDesc();

        return newCard.getId();
    }

    @GetMapping("/admin/unblock-block-card")
    public String flipCardState() {
        return "/cards/admin/unblock-block-card";
    }

    @PostMapping("/admin/unblock-block-card")
    @ResponseBody
    public CardDTO flipCardState(@RequestParam(value = "id") Integer id, HttpServletResponse response) {
        Card card = cardRepository.findCardById(id);

        if (card == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        cardService.blockOrUnblockCardById(id);

		return cardService.mapToCardDTO(card);
    }
}
