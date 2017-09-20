package com.example.sprinter.new_user;

import org.springframework.data.repository.CrudRepository;

public interface NewUserRepository extends CrudRepository<NewUser, Long> {
    NewUser findByEmail(String email);
    NewUser findByLink(String link);
}
