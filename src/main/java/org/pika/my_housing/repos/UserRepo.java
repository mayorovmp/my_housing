package org.pika.my_housing.repos;

import org.pika.my_housing.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
}
