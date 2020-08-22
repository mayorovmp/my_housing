package org.pika.my_housing.repos;

import org.pika.my_housing.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    @Query("select e from UserEntity e where e.email = :email and e.pass = :pass")
    Optional<UserEntity> findByEmailAndPass(@Param("email")String email, @Param("pass")String pass);
    Optional<UserEntity> findByLoginAndPass(String login, String pass);
    Optional<UserEntity> findByLogin(String login);
}
