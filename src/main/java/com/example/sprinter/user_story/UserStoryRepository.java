package com.example.sprinter.user_story;


import org.springframework.data.repository.CrudRepository;

public interface UserStoryRepository extends CrudRepository<UserStory, Long> {
}
