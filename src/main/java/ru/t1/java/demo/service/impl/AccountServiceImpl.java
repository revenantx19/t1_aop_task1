package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.repository.AccountRepository;
import ru.t1.java.demo.service.AccountService;

import java.util.List;
import java.util.Optional;
/**
 * Реализация сервиса для работы с аккаунтами.
 * Предоставляет методы для управления аккаунтами, включая поиск, создание,
 * обновление и удаление аккаунтов.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    /**
     * Находит и возвращает все аккаунты.
     *
     * @return список всех аккаунтов.
     */
    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    /**
     * Находит аккаунт по его идентификатору.
     *
     * @param id идентификатор аккаунта.
     * @return объект Optional с найденным аккаунтом, если он существует,
     *         иначе выбрасывает исключение.
     */
    @Override
    public Optional<Account> findById(Long id) {
        log.info("Find account by id: {}", id);
        return Optional.ofNullable(accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id)));
    }
    /**
     * Сохраняет новый аккаунт.
     *
     * @param account объект аккаунта для сохранения.
     * @return сохраненный аккаунт.
     */
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
    /**
     * Обновляет существующий аккаунт.
     *
     * @param id идентификатор аккаунта, который нужно обновить.
     * @param updatedAccount объект аккаунта с новыми данными.
     * @return объект Optional с обновленным аккаунтом, если обновление прошло успешно,
     *         иначе выбрасывает исключение.
     */
    @Override
    public Optional<Account> update(Long id, Account updatedAccount) {
        log.info("Update account by id: {}", id);
        return Optional.ofNullable(findById(id).map(account -> {
            account.setAccountType(updatedAccount.getAccountType());
            account.setBalance(updatedAccount.getBalance());
            return accountRepository.save(account);
        }).orElseThrow(() -> new RuntimeException("Error updating account with id: " + id)));
    }
    /**
     * Удаляет аккаунт по его идентификатору.
     *
     * @param id идентификатор аккаунта для удаления.
     * @return true, если удаление прошло успешно,
     *         иначе выбрасывает исключение.
     */
    @Override
    public boolean delete(Long id) {
        return findById(id).map(account -> {
            accountRepository.delete(account);
            return true;
        }).orElseThrow((() -> new RuntimeException("Error delete account with id: " + id)));
    }
}
