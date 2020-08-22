package org.pika.my_housing.repos;

import org.pika.my_housing.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<OrganizationEntity, Integer> {
}
