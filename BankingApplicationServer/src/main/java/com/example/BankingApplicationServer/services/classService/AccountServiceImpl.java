package com.example.BankingApplicationServer.services.classService;

import com.example.BankingApplicationServer.models.Account;
import com.example.BankingApplicationServer.repositories.AccountRepository;
import com.example.BankingApplicationServer.services.interfaceServices.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findOne(int id) {
        Optional<Account> foundAccount = accountRepository.findById(id);
        return foundAccount.orElse(null);
    }

    @Override
    @Transactional
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void update(int id, Account updateAccount) {
        updateAccount.setId(id);
        accountRepository.save(updateAccount);
    }

    @Override
    @Transactional
    public void deposit(int id, int cashDelta) {
        findOne(id).setCash(findOne(id).getCash() + cashDelta);
        update(id, findOne(id));
    }

    @Override
    @Transactional
    public void withDraw(int id, int cashDelta) {
        findOne(id).setCash(findOne(id).getCash() - cashDelta);
        update(id, findOne(id));
    }

    @Override
    @Transactional
    public void transfer(int sourceId, int destinationId, int cash) {
        withDraw(sourceId, cash);
        deposit(destinationId, cash);
    }
}
