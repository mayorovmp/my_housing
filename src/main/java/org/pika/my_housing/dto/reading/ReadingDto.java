package org.pika.my_housing.dto.reading;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReadingDto {
    private int id;
    private double value;
    private Date date;

    public ReadingDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
