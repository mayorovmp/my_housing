package org.pika.my_housing.dto.reading;

import java.util.Date;

public class ReadingOnAddDto {
    private int counterId;
    private double value;
    private Date date;

    public ReadingOnAddDto() {
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

    public int getCounterId() {
        return counterId;
    }

    public void setCounterId(int counterId) {
        this.counterId = counterId;
    }
}
