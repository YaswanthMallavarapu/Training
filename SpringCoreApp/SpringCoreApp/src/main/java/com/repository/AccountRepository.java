package com.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void debit(String from, int amount) {
        String sql="update account set balance=balance - ? where account_number= ?";
        jdbcTemplate.update(sql,amount,from);
    }

    public void credit(String to, int amount) {
        String sql="update account set balance=balance + ? where account_number= ?";
        jdbcTemplate.update(sql,amount,to);
    }
}
