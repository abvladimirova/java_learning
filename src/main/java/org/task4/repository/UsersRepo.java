package org.task4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.task4.entity.User;
import java.util.List;

@Repository
@Component
public interface UsersRepo extends CrudRepository<User, Integer> {
    List<User> findByUserName(String userName);
}
