package org.pika.my_housing.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "services", schema = "public", catalog = "my_housing")
public class ServiceEntity {
    private int id;
    private String name;
    private OrganizationEntity organizationsByOrganizationId;

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
        ServiceEntity that = (ServiceEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false)
    public OrganizationEntity getOrganizationsByOrganizationId() {
        return organizationsByOrganizationId;
    }

    public void setOrganizationsByOrganizationId(OrganizationEntity organizationsByOrganizationId) {
        this.organizationsByOrganizationId = organizationsByOrganizationId;
    }
}
