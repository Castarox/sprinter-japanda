package com.example.sprinter.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    User findByName(String name);

    User findByEmail(String email);

    User findByNameAndPassword(String name, String password);
}
