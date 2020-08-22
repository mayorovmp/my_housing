package org.pika.my_housing.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "organizations", schema = "public", catalog = "my_housing")
public class OrganizationEntity {
    private int id;
    private String name;
    private Collection<AccountEntity> accountsById;
    private Collection<ClaimEntity> claimsById;
    private Collection<ServiceEntity> servicesById;

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
        OrganizationEntity that = (OrganizationEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "organizationsByOrganizationId")
    public Collection<AccountEntity> getAccountsById() {
        return accountsById;
    }

    public void setAccountsById(Collection<AccountEntity> accountsById) {
        this.accountsById = accountsById;
    }

    @OneToMany(mappedBy = "organizationsByOrganizationId")
    public Collection<ClaimEntity> getClaimsById() {
        return claimsById;
    }

    public void setClaimsById(Collection<ClaimEntity> claimsById) {
        this.claimsById = claimsById;
    }

    @OneToMany(mappedBy = "organizationsByOrganizationId")
    public Collection<ServiceEntity> getServicesById() {
        return servicesById;
    }

    public void setServicesById(Collection<ServiceEntity> servicesById) {
        this.servicesById = servicesById;
    }
}
