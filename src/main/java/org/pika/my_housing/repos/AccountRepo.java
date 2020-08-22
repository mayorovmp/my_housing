package org.pika.my_housing.repos;

import org.pika.my_housing.entities.AccountEntity;
import org.pika.my_housing.entities.OrganizationEntity;
import org.pika.my_housing.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<OrganizationEntity, Integer> {
}
