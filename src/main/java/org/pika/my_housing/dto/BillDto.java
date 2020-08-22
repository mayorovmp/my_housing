package org.pika.my_housing.dto;


import java.util.Date;

public class BillDto {
    private ServiceDto service;
    private double value;
    private Date time;

    public BillDto() {
    }

    public ServiceDto getService() {
        return service;
    }

    public void setService(ServiceDto service) {
        this.service = service;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
