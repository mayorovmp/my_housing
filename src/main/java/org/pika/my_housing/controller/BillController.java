package org.pika.my_housing.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.pika.my_housing.dto.AccountDto;
import org.pika.my_housing.dto.BillDto;
import org.pika.my_housing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    private  ModelMapper mapper;
    private final BillService billService;

    @Autowired
    public BillController(ModelMapper mapper, BillService billService) {
        this.mapper = mapper;
        this.billService = billService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BillDto> getLastBills(@RequestParam int accountId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var login = user.getUsername();
        var result = billService.getBills(login, accountId);
        List<BillDto> res = mapper.map(result, new TypeToken<List<BillDto>>() {}.getType());
        return res;
    }
}
