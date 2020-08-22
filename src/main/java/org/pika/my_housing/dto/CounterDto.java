package org.pika.my_housing.dto;

import org.pika.my_housing.dto.reading.ReadingDto;

import java.util.List;

public class CounterDto {
    private int id;
    private String name;
    private List<ReadingDto> readings;

    public CounterDto() {
    }

    public List<ReadingDto> getReadings() {
        return readings;
    }

    public void setReadings(List<ReadingDto> readings) {
        this.readings = readings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
