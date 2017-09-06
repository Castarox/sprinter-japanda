package com.example.sprinter.user;

import org.springframework.data.repository.CrudRepository;

public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {
    UserDetail findByName(String name);
}
