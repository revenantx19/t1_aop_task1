package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.repository.TransactionRepository;
import ru.t1.java.demo.service.TransactionService;

import java.util.List;
import java.util.Optional;
/**
 * Реализация сервиса для работы с транзакциями.
 * Предоставляет методы для управления транзакциями, включая поиск, создание,
 * обновление и удаление транзакций.
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    /**
     * Находит и возвращает все транзакции.
     *
     * @return список всех транзакций.
     */
    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
    /**
     * Находит транзакцию по ее идентификатору.
     *
     * @param id идентификатор транзакции.
     * @return объект Optional с найденной транзакцией, если она существует,
     *         иначе выбрасывает исключение.
     */
    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id)));
    }
    /**
     * Сохраняет новую транзакцию.
     *
     * @param transaction объект транзакции для сохранения.
     * @return сохраненная транзакция.
     */
    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    /**
     * Обновляет существующую транзакцию.
     *
     * @param id идентификатор транзакции, которую нужно обновить.
     * @param updatedTransaction объект транзакции с новыми данными.
     * @return объект Optional с обновленной транзакцией, если обновление прошло успешно,
     *         иначе выбрасывает исключение.
     */
    @Override
    public Optional<Transaction> update(Long id, Transaction updatedTransaction) {
        return Optional.ofNullable(findById(id).map(transaction -> {
            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setTransactionTime(updatedTransaction.getTransactionTime());
            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new RuntimeException("Error updating transaction with id: " + id)));
    }
    /**
     * Удаляет транзакцию по ее идентификатору.
     *
     * @param id идентификатор транзакции для удаления.
     * @return true, если удаление прошло успешно,
     *         иначе выбрасывает исключение.
     */
    @Override
    public boolean delete(Long id) {
        return findById(id).map(transaction -> {
            transactionRepository.delete(transaction);
            return true;
        }).orElseThrow((() -> new RuntimeException("Error delete transaction with id: " + id)));
    }
}
