package org.pika.my_housing.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "claims", schema = "public", catalog = "my_housing")
public class ClaimEntity {
    private int id;
    private String contact;
    private String text;
    private UserEntity usersByUserId;
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
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClaimEntity that = (ClaimEntity) o;
        return id == that.id &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contact, text);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UserEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
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
