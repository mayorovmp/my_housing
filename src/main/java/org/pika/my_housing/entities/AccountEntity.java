package org.pika.my_housing.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accounts", schema = "public", catalog = "my_housing")
public class AccountEntity {
    private int id;
    private String house;
    private String flat;
    private String value;
    private OrganizationEntity organizationsByOrganizationId;
    private Collection<CounterEntity> countersById;


    private Set<ServiceEntity> services = new HashSet<>();

    private Set<UserEntity> users = new HashSet<>();

    @ManyToMany(mappedBy = "accounts")
    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "account_services",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "service_id") }
    )
    public Set<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(Set<ServiceEntity> services) {
        this.services = services;
    }

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
    @Column(name = "house")
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Basic
    @Column(name = "flat")
    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id &&
                Objects.equals(house, that.house) &&
                Objects.equals(flat, that.flat) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, house, flat, value);
    }

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false)
    public OrganizationEntity getOrganizationsByOrganizationId() {
        return organizationsByOrganizationId;
    }

    public void setOrganizationsByOrganizationId(OrganizationEntity organizationsByOrganizationId) {
        this.organizationsByOrganizationId = organizationsByOrganizationId;
    }

    @OneToMany(mappedBy = "account")
    public Collection<CounterEntity> getCountersById() {
        return countersById;
    }

    public void setCountersById(Collection<CounterEntity> countersById) {
        this.countersById = countersById;
    }
}
