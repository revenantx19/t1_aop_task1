package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.repository.TransactionRepository;
import ru.t1.java.demo.service.TransactionService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id)));
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> update(Long id, Transaction updatedTransaction) {
        return Optional.ofNullable(findById(id).map(transaction -> {
            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setTransactionTime(updatedTransaction.getTransactionTime());
            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new RuntimeException("Error updating transaction with id: " + id)));
    }

    @Override
    public boolean delete(Long id) {
        return findById(id).map(transaction -> {
            transactionRepository.delete(transaction);
            return true;
        }).orElseThrow((() -> new RuntimeException("Error delete transaction with id: " + id)));
    }
}
