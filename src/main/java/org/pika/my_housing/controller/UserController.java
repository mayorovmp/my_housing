package org.pika.my_housing.controller;

import org.modelmapper.ModelMapper;
import org.pika.my_housing.aspect.annotaion.LogExecutionTime;
import org.pika.my_housing.dto.user.UserDto;
import org.pika.my_housing.dto.user.UserLoginDto;
import org.pika.my_housing.dto.user.UserRegisterDto;
import org.pika.my_housing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private final ModelMapper mapper;
    private final UserService userService;
    @Autowired
    public UserController(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody UserLoginDto user) throws Exception {
        String token = userService.login(user.login, user.password);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @PostMapping("/me")
    public ResponseEntity<UserDto> me(@RequestBody UserLoginDto user) throws Exception {
        var me = userService.findByLogin(user.login);
        var dto = mapper.map(me, UserDto.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto user) throws Exception {
        userService.register(user.getLogin(), user.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}