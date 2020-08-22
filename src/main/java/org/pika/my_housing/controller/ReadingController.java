package org.pika.my_housing.controller;


import org.modelmapper.ModelMapper;
import org.pika.my_housing.dto.reading.ReadingDto;
import org.pika.my_housing.dto.reading.ReadingOnAddDto;
import org.pika.my_housing.entities.ReadingEntity;
import org.pika.my_housing.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {
    private final ModelMapper mapper;
    private final ReadingService readingService;

    @Autowired
    public ReadingController(ModelMapper mapper, ReadingService readingService) {
        this.mapper = mapper;
        this.readingService = readingService;
    }

    @PostMapping
    public ResponseEntity<?> addReading(@RequestBody ReadingOnAddDto reading, @AuthenticationPrincipal User user) {
        this.readingService.addReading(user.getUsername(), mapper.map(reading, ReadingEntity.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
