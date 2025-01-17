package ru.t1.java.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.service.AccountService;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для работы с аккаунтами.
 * Обрабатывает HTTP запросы, связанные с аккаунтами.
 */
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;
    /**
     * Получает список всех аккаунтов.
     *
     * @return список аккаунтов.
     */
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }
    /**
     * Получает аккаунт по его идентификатору.
     *
     * @param id идентификатор аккаунта.
     * @return аккаунт, если найден, иначе пустое значение.
     */
    @GetMapping("/{id}")
    public Optional<Account> getAccountById(@PathVariable Long id) {
        return accountService.findById(id);
    }
    /**
     * Создает новый аккаунт.
     *
     * @param account объект аккаунта для сохранения.
     * @return созданный аккаунт.
     */
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }
    /**
     * Обновляет существующий аккаунт.
     *
     * @param id идентификатор аккаунта, который нужно обновить.
     * @param account объект аккаунта с новыми данными.
     * @return объект ResponseEntity с обновленным аккаунтом или статус 404 если не найден.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id,
                                                 @RequestBody Account account) {
        return accountService.update(id, account)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    /**
     * Удаляет аккаунт по его идентификатору.
     *
     * @param id идентификатор аккаунта для удаления.
     * @return объект ResponseEntity с кодом 200, если удаление прошло успешно,
     *         иначе код 404, если аккаунт не найден.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        if (accountService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
