package org.pika.my_housing.repos;

import org.pika.my_housing.entities.ReadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepo extends JpaRepository<ReadingEntity, Integer> {

}
