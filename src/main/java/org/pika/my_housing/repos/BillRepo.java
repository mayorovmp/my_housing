package org.pika.my_housing.repos;

import org.pika.my_housing.entities.BillEntity;
import org.pika.my_housing.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepo extends JpaRepository<BillEntity, Integer> {
    Optional<BillEntity> findFirstByServiceOrderByPeriodDesc(ServiceEntity s);
}
