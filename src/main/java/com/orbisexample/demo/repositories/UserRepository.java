package com.orbisexample.demo.repositories;

import com.orbisexample.demo.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAll();
    Optional<User> findByUserName(String username);
}
