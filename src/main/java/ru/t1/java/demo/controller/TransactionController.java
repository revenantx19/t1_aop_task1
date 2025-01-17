package ru.t1.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.service.TransactionService;

import java.util.List;
/**
 * Контроллер для работы с транзакциями.
 * Обрабатывает HTTP запросы, связанные с транзакциями.
 */
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    /**
     * Получает список всех транзакций.
     *
     * @return список транзакций.
     */
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.findAll();
    }
    /**
     * Получает транзакцию по ее идентификатору.
     *
     * @param id идентификатор транзакции.
     * @return объект ResponseEntity с найденной транзакцией или статус 404, если не найдено.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /**
     * Создает новую транзакцию.
     *
     * @param transaction объект транзакции для сохранения.
     * @return созданная транзакция.
     */
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }
    /**
     * Обновляет существующую транзакцию.
     *
     * @param id идентификатор транзакции, которую нужно обновить.
     * @param transaction объект транзакции с новыми данными.
     * @return объект ResponseEntity с обновленной транзакцией или статус 404, если не найдено.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.update(id, transaction)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /**
     * Удаляет транзакцию по ее идентификатору.
     *
     * @param id идентификатор транзакции для удаления.
     * @return объект ResponseEntity с кодом 200, если удаление прошло успешно,
     *         иначе код 404, если транзакция не найдена.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        if (transactionService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
