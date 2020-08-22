package org.pika.my_housing.service;

import org.pika.my_housing.dto.reading.ReadingDto;
import org.pika.my_housing.entities.ReadingEntity;
import org.pika.my_housing.exceptions.ApiException;
import org.pika.my_housing.repos.ReadingRepo;
import org.springframework.stereotype.Service;

@Service
public class ReadingService {
    private final UserService userService;
    private final CounterService counterService;
    private final ReadingRepo readingRepo;
    public ReadingService(UserService userService, CounterService counterService, ReadingRepo readingRepo) {
        this.userService = userService;
        this.readingRepo = readingRepo;
        this.counterService = counterService;
    }

    public void addReading(String login, ReadingEntity reading) {
        var user = userService.findByLogin(login);
        var counter = user.getAccounts().stream()
                .flatMap(a->a.getCountersById().stream())
                .filter(c->c.getId() == reading.getCounter().getId()).findFirst().orElseThrow(()->new ApiException("Счетчик не найден"));
        reading.setCounter(counter);
        readingRepo.save(reading);
    }
}
