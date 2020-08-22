package org.pika.my_housing.repos;

import org.pika.my_housing.entities.AccountEntity;
import org.pika.my_housing.entities.CounterEntity;
import org.pika.my_housing.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounterRepo extends JpaRepository<CounterEntity, Integer> {
    public List<CounterEntity> findAllByAccount(AccountEntity a);
}
