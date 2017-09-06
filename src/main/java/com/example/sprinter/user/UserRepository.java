package com.example.sprinter.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends CrudRepository<UserDetails, Long> {
    UserDetails findByEmailAndPassword(String email, String password);
    UserDetails findByName(String name);
    UserDetails findByEmail(String email);
    UserDetails findByNameAndPassword(String name, String password);
}
