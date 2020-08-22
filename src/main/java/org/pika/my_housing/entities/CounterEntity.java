package org.pika.my_housing.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "counters", schema = "public", catalog = "my_housing")
public class CounterEntity {
    private int id;
    private String name;
    private AccountEntity account;
    private Collection<ReadingEntity> readings;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CounterEntity that = (CounterEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity accountsByAccountId) {
        this.account = accountsByAccountId;
    }

    @OneToMany(mappedBy = "counter")
    public Collection<ReadingEntity> getReadings() {
        return readings;
    }

    public void setReadings(Collection<ReadingEntity> readingsById) {
        this.readings = readingsById;
    }
}
