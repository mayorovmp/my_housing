package org.pika.my_housing.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users", schema = "public", catalog = "my_housing")
public class UserEntity {
    private int id;
    private String email;
    private String login;
    private String name;
    private String pass;

    private Set<AccountEntity> accounts = new HashSet<>();

    private Collection<ClaimEntity> claimsById;

    public UserEntity() {
        claimsById = new ArrayList<>();
    }

    public UserEntity(String login, String pass) {
        this.login = login;
        this.pass = pass;
        claimsById = new ArrayList<>();
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_accounts",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "account_id") }
    )
    public Set<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountEntity> accounts) {
        this.accounts = accounts;
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
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(email, that.email) &&
                Objects.equals(login, that.login) &&
                Objects.equals(name, that.name) &&
                Objects.equals(pass, that.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, pass, login);
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<ClaimEntity> getClaimsById() {
        return claimsById;
    }

    public void setClaimsById(Collection<ClaimEntity> claimsById) {
        this.claimsById = claimsById;
    }
}
