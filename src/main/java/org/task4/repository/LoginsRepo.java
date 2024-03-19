package org.task4.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.task4.entity.LoginsDairy;

@Repository
public interface LoginsRepo extends CrudRepository<LoginsDairy, Integer> {

}
