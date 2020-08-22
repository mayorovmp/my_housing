package org.pika.my_housing.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bills", schema = "public", catalog = "my_housing")
public class BillEntity {
    private int id;
    private BigInteger value;
    private AccountEntity account;
    private ServiceEntity service;
    private Timestamp period;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }


    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    @Basic
    @Column(name = "value")
    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    @Basic
    @Column(name = "period")
    public Timestamp getPeriod() {
        return period;
    }

    public void setPeriod(Timestamp period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillEntity that = (BillEntity) o;
        return id == that.id &&
                Objects.equals(value, that.value) &&
                Objects.equals(period, that.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, period);
    }
}
