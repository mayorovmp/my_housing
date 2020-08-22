package org.pika.my_housing.service;

import org.pika.my_housing.entities.AccountEntity;
import org.pika.my_housing.repos.SessionRepo;
import org.pika.my_housing.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final UserRepo userRepo;

    public AccountService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<AccountEntity> getAccountsByLogin(int id){
        return null;
    }
}
