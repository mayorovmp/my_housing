package org.pika.my_housing.entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "readings", schema = "public", catalog = "my_housing")
public class ReadingEntity {
    private int id;
    private BigInteger value;
    private Timestamp date;
    private CounterEntity counter;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value")
    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }


    @ManyToOne
    @JoinColumn(name = "counter_id", referencedColumnName = "id", nullable = false)
    public CounterEntity getCounter() {
        return counter;
    }

    public void setCounter(CounterEntity countersByCounterId) {
        this.counter = countersByCounterId;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadingEntity that = (ReadingEntity) o;
        return id == that.id &&
                Objects.equals(counter, that.counter) &&
                Objects.equals(value, that.value) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, date, counter);
    }
}
