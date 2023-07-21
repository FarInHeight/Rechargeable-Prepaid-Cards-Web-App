package it.unipa.community.sferra.sferraproject.transactions;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import it.unipa.community.sferra.sferraproject.cards.CardRepository;
import it.unipa.community.sferra.sferraproject.users.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void createTransaction(TransactionDTO transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setUserId( userRepository.findByUsername( transaction.getUsername() ) );
        newTransaction.setCardId( cardRepository.findCardById( transaction.getCardId() ) );
        newTransaction.setCredit( transaction.getCredit() );
        newTransaction.setDescription( transaction.getDescription() );
        transactionRepository.save(newTransaction);
    }

    @Override
    public List<TransactionDTO> findAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map((transaction) -> mapToTransactionDTO(transaction))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findTransactionsByUser(User user) {
        String username = user.getUsername();
        return userRepository
                            .findByUsername(username)
                            .getTransactions()
                            .stream()
                            .map((transaction) -> mapToTransactionDTO(transaction))
                            .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO mapToTransactionDTO(Transaction transaction) {
        String username = transaction.getUserId().getUsername();
        Integer cardId = transaction.getCardId().getId();
        Float credit = transaction.getCredit();
        String description = transaction.getDescription();
        Date creationTimestamp = transaction.getCreationTimestamp();
        
        return new TransactionDTO(username, cardId, credit, description, creationTimestamp);
    }
}