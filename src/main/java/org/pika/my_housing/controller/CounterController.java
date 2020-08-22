package org.pika.my_housing.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.pika.my_housing.dto.BillDto;
import org.pika.my_housing.dto.CounterDto;
import org.pika.my_housing.service.BillService;
import org.pika.my_housing.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/counters")
public class CounterController {
    private final ModelMapper mapper;
    private final CounterService counterService;

    @Autowired
    public CounterController(ModelMapper mapper, CounterService counterService) {
        this.mapper = mapper;
        this.counterService = counterService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CounterDto> getCounters(@RequestParam int accountId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var login = user.getUsername();

        var result = counterService.getCounters(login, accountId);
        List<CounterDto> res = mapper.map(result, new TypeToken<List<CounterDto>>() {}.getType());
        return res;
    }
}
