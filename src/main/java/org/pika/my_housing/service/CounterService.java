package org.pika.my_housing.service;

import org.pika.my_housing.entities.CounterEntity;
import org.pika.my_housing.exceptions.ApiException;
import org.pika.my_housing.repos.CounterRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterService {
    private final UserService userService;
    private final CounterRepo counterRepo;
    public CounterService(UserService userService, CounterRepo counterRepo) {
        this.userService = userService;
        this.counterRepo = counterRepo;
    }

    public List<CounterEntity> getCounters(String login, int accId) {
        var user = this.userService.findByLogin(login);
        var accEntity = user.getAccounts().stream()
                .filter(a->a.getId() == accId)
                .findFirst()
                .orElseThrow(()-> new ApiException("У пользователя не найден лицевой счет"));
        return counterRepo.findAllByAccount(accEntity);
    }
}
