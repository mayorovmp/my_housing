package org.pika.my_housing.controller;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.pika.my_housing.dto.AccountDto;
import org.pika.my_housing.dto.OrgDto;
import org.pika.my_housing.dto.user.UserDto;
import org.pika.my_housing.entities.AccountEntity;
import org.pika.my_housing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final ModelMapper mapper;
    private final UserService userService;

    @Autowired
    public AccountController(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AccountDto> accounts() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var login = user.getUsername();
        var userEntity = this.userService.findByLogin(login);
        Set<AccountEntity> result = userEntity.getAccounts();
        return mapper.map(result, new TypeToken<List<AccountDto>>() {}.getType());
    }
}
