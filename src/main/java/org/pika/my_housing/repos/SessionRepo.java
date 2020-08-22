package org.pika.my_housing.repos;

import org.pika.my_housing.entities.SessionEntity;
import org.pika.my_housing.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface SessionRepo extends JpaRepository<SessionEntity, Integer> {
    Optional<SessionEntity> findByToken(String token);
}
