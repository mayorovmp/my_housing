package org.pika.my_housing.service;

import org.pika.my_housing.entities.AccountEntity;
import org.pika.my_housing.entities.BillEntity;
import org.pika.my_housing.entities.ServiceEntity;
import org.pika.my_housing.exceptions.ApiException;
import org.pika.my_housing.repos.BillRepo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {
    private final BillRepo billRepo;
    private final UserService userService;

    public BillService(BillRepo billRepo, UserService userService) {
        this.billRepo = billRepo;
        this.userService = userService;
    }

    public List<BillEntity> getBills(String login, int accountId){
        var user = this.userService.findByLogin(login);
        var account = user.getAccounts().stream()
                .filter(a->a.getId() == accountId).findFirst()
                .orElseThrow(()->new ApiException("Лицевой счет не найден"));
        var services = account.getServices();

        var bills = services.stream()
                .flatMap(s->billRepo.findFirstByServiceOrderByPeriodDesc(s).stream())
                .collect(Collectors.toList());
        return bills;
    }

}
