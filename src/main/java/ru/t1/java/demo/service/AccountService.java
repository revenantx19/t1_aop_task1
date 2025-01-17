package ru.t1.java.demo.service;

import ru.t1.java.demo.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> findAll();
    Optional<Account> findById(Long id);
    Account save(Account account);
    Optional<Account> update(Long id, Account account);
    boolean delete(Long id);

}
