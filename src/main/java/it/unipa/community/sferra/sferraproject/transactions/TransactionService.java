package it.unipa.community.sferra.sferraproject.transactions;

import java.util.List;

import org.springframework.security.core.userdetails.User;

public interface TransactionService {
 
    void createTransaction(TransactionDTO transaction);

    List<TransactionDTO> findAllTransactions();

    List<TransactionDTO> findTransactionsByUser(User user);

    TransactionDTO mapToTransactionDTO(Transaction transaction);

}
