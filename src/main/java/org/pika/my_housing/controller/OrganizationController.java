package org.pika.my_housing.controller;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.pika.my_housing.aspect.annotaion.LogExecutionTime;
import org.pika.my_housing.dto.OrgDto;
import org.pika.my_housing.repos.OrganizationRepo;
import org.pika.my_housing.repos.UserRepo;
import org.pika.my_housing.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Org")
public class OrganizationController {
    private final UserService userService;
    private final OrganizationRepo orgRepo;
    private final UserRepo userRepo;
    private final ModelMapper mapper;

    public OrganizationController(UserRepo us, UserService userService, ModelMapper mapper, OrganizationRepo organizationRepo) {
        this.userService = userService;
        this.mapper = mapper;
        this.userRepo = us;
        this.orgRepo = organizationRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    @LogExecutionTime
    public List<OrgDto> getAll() {
        var r = userRepo.findByEmailAndPass("max@ya.ru", "1234");
        List<OrgDto>  res = mapper.map(orgRepo.findAll(), new TypeToken<List<OrgDto>>() {}.getType());
        return res;
    }
}
