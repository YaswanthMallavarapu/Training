package com.service;

import com.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired

    private AccountRepository accountRepository;

    @Transactional
    public void TransferMoneyFromABCToXYZ(String from, String to, int amount) {
        accountRepository.debit(from,amount);
        System.out.println("Money debited....");
//        if(true)throw new RuntimeException("something went wrong!");
        accountRepository.credit(to,amount);

    }
}
