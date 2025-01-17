package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.repository.AccountRepository;
import ru.t1.java.demo.service.AccountService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        log.info("Find account by id: {}", id);
        return Optional.ofNullable(accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id)));
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> update(Long id, Account updatedAccount) {
        log.info("Update account by id: {}", id);
        return Optional.ofNullable(findById(id).map(account -> {
            account.setAccountType(updatedAccount.getAccountType());
            account.setBalance(updatedAccount.getBalance());
            return accountRepository.save(account);
        }).orElseThrow(() -> new RuntimeException("Error updating account with id: " + id)));
    }

    @Override
    public boolean delete(Long id) {
        return findById(id).map(account -> {
            accountRepository.delete(account);
            return true;
        }).orElseThrow((() -> new RuntimeException("Error delete account with id: " + id)));
    }
}
